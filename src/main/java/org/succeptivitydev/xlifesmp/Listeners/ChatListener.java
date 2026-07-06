package org.succeptivitydev.xlifesmp.Listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.succeptivitydev.xlifesmp.ThirdLifePlugin;
import org.succeptivitydev.xlifesmp.model.PlayerData;

public class ChatListener implements Listener {

    private final ThirdLifePlugin plugin;

    public ChatListener(ThirdLifePlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Add color prefix to chat messages based on player's life state
     */
    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = plugin.getLifeManager().getOrCreate(player.getUniqueId());

        String colorName = playerData.getState().name();
        int colorCode = switch (playerData.getState()) {
            case GREEN -> 0x00FF00;
            case YELLOW -> 0xFFFF00;
            case RED -> 0xFF0000;
            case ELIMINATED -> 0x555555; // Dark gray for eliminated
        };

        // Create the color prefix
        Component colorPrefix = Component.text("[" + colorName + "] ")
                .color(TextColor.color(colorCode));

        // Add player name with white color
        Component playerName = Component.text(player.getName())
                .color(TextColor.color(0xFFFFFF));

        // Get the original message
        Component originalMessage = event.message();

        // Combine: [COLOR] PlayerName: Message
        Component finalMessage = colorPrefix
                .append(playerName)
                .append(Component.text(": ").color(TextColor.color(0xFFFFFF)))
                .append(originalMessage);

        event.renderer((source, sourceDisplayName, message, viewer) -> finalMessage);
    }
}

