package org.succeptivitydev.xlifesmp.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.succeptivitydev.xlifesmp.ThirdLifePlugin;
import org.succeptivitydev.xlifesmp.CustomStuff.Items.Items;
import org.succeptivitydev.xlifesmp.model.LifeState;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class CraftingListener implements Listener {

    private final ThirdLifePlugin plugin;

    public CraftingListener(ThirdLifePlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Enforce crafting restrictions based on player life state:
     * - GREEN items: only GREEN players
     * - YELLOW items: only YELLOW players
     * - RED items: only RED players
     * - Legendary items: anyone
     */
    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack recipe = event.getRecipe().getResult();
        Player player = (Player) event.getWhoClicked();
        var playerData = plugin.getLifeManager().getOrCreate(player.getUniqueId());
        LifeState state = playerData.getState();

        // GREEN ITEMS (only GREEN players)
        if (isGreenItem(recipe)) {
            if (state != LifeState.GREEN) {
                event.setCancelled(true);
                player.sendMessage(Component.text("Only GREEN players can craft this item!").color(TextColor.color(0x00FF00)));
            }
            return;
        }

        // YELLOW ITEMS (only YELLOW players)
        if (isYellowItem(recipe)) {
            if (state != LifeState.YELLOW) {
                event.setCancelled(true);
                player.sendMessage(Component.text("Only YELLOW players can craft this item!").color(TextColor.color(0xFFFF00)));
            }
            return;
        }

        // RED ITEMS (only RED players)
        if (isRedItem(recipe)) {
            if (state != LifeState.RED) {
                event.setCancelled(true);
                player.sendMessage(Component.text("Only RED players can craft this item!").color(TextColor.color(0xFF0000)));
            }
            return;
        }

        // LEGENDARY ITEMS (anyone can craft)
        // No restrictions - all players can craft
    }

    private boolean isGreenItem(ItemStack item) {
        return Items.isBackpack(item, plugin)
            || Items.isLumberAxe(item, plugin)
            || Items.isFarmerCharm(item, plugin)
            || Items.isWindBoots(item, plugin)
            || Items.isEmergencyTotem(item, plugin)
            || Items.isLifeOrb(item, plugin);
    }

    private boolean isYellowItem(ItemStack item) {
        return Items.isTrackingCompass(item, plugin)
            || Items.isSmokeBomb(item, plugin)
            || Items.isSnareTrap(item, plugin)
            || Items.isHunterCloak(item, plugin)
            || Items.isScoutBow(item, plugin);
    }

    private boolean isRedItem(ItemStack item) {
        return Items.isBloodSword(item, plugin)
            || Items.isFrenzyPendant(item, plugin)
            || Items.isLastStandShield(item, plugin)
            || Items.isChaosPearl(item, plugin)
            || Items.isBloodLustSword(item, plugin);
    }
}
