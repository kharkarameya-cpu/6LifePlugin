package org.succeptivitydev.xlifesmp.Listeners;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.succeptivitydev.xlifesmp.ThirdLifePlugin;
import org.succeptivitydev.xlifesmp.model.PlayerData;

public class LoginListener implements Listener {

    private final ThirdLifePlugin plugin;

    public LoginListener(ThirdLifePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {

        PlayerData data = plugin.getLifeManager().getOrCreate(event.getUniqueId());

        if (data.getLives() <= 0) {

            String message = plugin.getMessagesConfig().getString(
                    "messages.login-denied",
                    "You have been eliminated! Wait until someone revives you."
            );

            event.disallow(
                    AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                    message
            );
        }
    }
}