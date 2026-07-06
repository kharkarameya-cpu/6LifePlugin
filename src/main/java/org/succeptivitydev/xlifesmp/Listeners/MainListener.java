package org.succeptivitydev.xlifesmp.Listeners;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.succeptivitydev.xlifesmp.ThirdLifePlugin;
import org.succeptivitydev.xlifesmp.model.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class MainListener implements Listener {

    private final ThirdLifePlugin plugin;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public MainListener(ThirdLifePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = plugin.getLifeManager().getOrCreate(player.getUniqueId());
        
        // Update player's display name with color prefix for tab list
        updatePlayerDisplayName(player, playerData);
    }

    /**
     * Updates the player's display name in the tab list to show their color status
     */
    private void updatePlayerDisplayName(Player player, PlayerData playerData) {
        String colorName = playerData.getState().name();
        int colorCode = switch (playerData.getState()) {
            case GREEN -> 0x00FF00;
            case YELLOW -> 0xFFFF00;
            case RED -> 0xFF0000;
            case ELIMINATED -> 0x555555; // Dark gray for eliminated
        };

        Component displayName = Component.text("[" + colorName + "] ").color(TextColor.color(colorCode))
                .append(Component.text(player.getName()).color(TextColor.color(0xFFFFFF)));
        
        player.displayName(displayName);
    }

    /**
     * Enforce PvP restrictions: Players can only damage other players when they have 3 or fewer lives
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // Check if the attacker is a player and the victim is a player
        if (!(event.getDamager() instanceof Player attacker) || !(event.getEntity() instanceof Player victim)) {
            return;
        }

        // Get the attacker's life count
        PlayerData attackerData = plugin.getLifeManager().getOrCreate(attacker.getUniqueId());
        int attackerLives = attackerData.getLives();

        // If attacker has more than 3 lives, cancel the damage
        if (attackerLives > 3) {
            event.setCancelled(true);
            attacker.sendMessage(
                    Component.text("You can only PvP when you have 3 or fewer lives! Current lives: " + attackerLives)
                            .color(TextColor.color(0xFF0000))
            );
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();
        Player killer = player.getKiller();

        if (killer != null) {

            // increment killer lives and persist via LifeManager
            int newLives = Math.min(6, plugin.getLifeManager().getOrCreate(killer.getUniqueId()).getLives() + 1);
            plugin.getLifeManager().setLives(killer.getUniqueId(), newLives);

            String gainedLife = plugin.getMessagesConfig().getString(
                    "messages.gained-life",
                    "<green>%player% has gained a life!"
            );

            gainedLife = gainedLife.replace("%player%", killer.getName());

            plugin.getServer().broadcast(
                    miniMessage.deserialize(gainedLife)
            );
        }

        boolean eliminated = plugin.getLifeManager().removeLife(player.getUniqueId());

        if (eliminated) {

            String eliminatedBroadcast = plugin.getMessagesConfig().getString(
                    "messages.eliminated-broadcast",
                    "<red>%player% has been eliminated!"
            );

            eliminatedBroadcast = eliminatedBroadcast.replace("%player%", player.getName());

            plugin.getServer().broadcast(
                    miniMessage.deserialize(eliminatedBroadcast)
            );

            String eliminatedPlayer = plugin.getMessagesConfig().getString(
                    "messages.eliminated-player",
                    "<red>You have been eliminated! Wait for someone to revive you."
            );

            player.sendMessage(
                    miniMessage.deserialize(eliminatedPlayer)
            );

            // Kick the player.
            player.kick(
                    miniMessage.deserialize(eliminatedPlayer)
            );

        } else {

            PlayerData data = plugin.getLifeManager().getOrCreate(player.getUniqueId());

            String lostLife = plugin.getMessagesConfig().getString(
                    "messages.lost-life",
                    "<red>You lost a life! <gray>Lives remaining: <yellow>%lives%"
            );

            lostLife = lostLife.replace("%lives%", String.valueOf(data.getLives()));

            player.sendMessage(
                    miniMessage.deserialize(lostLife)
            );
            
            // Update player's display name to reflect new color status
            updatePlayerDisplayName(player, data);
        }
    }
}