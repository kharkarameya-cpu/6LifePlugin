package org.succeptivitydev.xlifesmp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.succeptivitydev.xlifesmp.commands.LifeCommand;
import org.succeptivitydev.xlifesmp.manager.LifeManager;
import org.succeptivitydev.xlifesmp.CustomStuff.RecipeManager;
import org.succeptivitydev.xlifesmp.util.ColorParser;
import net.kyori.adventure.text.Component;

import java.io.File;

public final class ThirdLifePlugin extends JavaPlugin {
    private LifeManager lifeManager;
    private FileConfiguration messagesConf;
    @Override
    public void onEnable() {
        getLogger().info("ThirdLifePlugin has been enabled!");
        // Ensure default config and messages are present before initializing components
        saveDefaultConfig();
        saveResource("messages.yml", false);
        File messagesFile = new File(getDataFolder(), "messages.yml");
        messagesConf = YamlConfiguration.loadConfiguration(messagesFile);

        // Initialize LifeManager with plugin so it can read config and choose a storage backend
        lifeManager = new LifeManager(this);
        getCommand("life").setExecutor(
                new LifeCommand(this)
        );
        getCommand("setlives").setExecutor(
                new LifeCommand(this)
        );
        getCommand("revive").setExecutor(
                new LifeCommand(this)
        );
        getServer().getPluginManager().registerEvents(
                new org.succeptivitydev.xlifesmp.Listeners.MainListener(this),
                this
        );
        getServer().getPluginManager().registerEvents(
                new org.succeptivitydev.xlifesmp.Listeners.LoginListener(this),
                this
        );
        getServer().getPluginManager().registerEvents(
                new org.succeptivitydev.xlifesmp.Listeners.CraftingListener(this),
                this
        );
        getServer().getPluginManager().registerEvents(
                new org.succeptivitydev.xlifesmp.Listeners.ChatListener(this),
                this
        );

        // Register custom item recipes
        new RecipeManager(this).registerRecipes();
    }
    public FileConfiguration getMessagesConfig() {
        return messagesConf;
    }

    /**
     * Get a message from the messages.yml file with color parsing applied
     * @param path the path to the message (e.g., "eliminated", "status.green")
     * @param replacements key-value pairs for replacements (e.g., "%player%", "playerName")
     * @return a Component with colors applied
     */
    public Component getMessage(String path, Object... replacements) {
        String message = messagesConf.getString(path, "");

        // Apply replacements
        for (int i = 0; i < replacements.length; i += 2) {
            if (i + 1 < replacements.length) {
                message = message.replace(replacements[i].toString(), replacements[i + 1].toString());
            }
        }

        // Parse and apply colors
        return ColorParser.applyColors(message);
    }
    public LifeManager getLifeManager() {
        return lifeManager;
    }
    @Override
    public void onDisable() {
        getLogger().info("ThirdLifePlugin has been disabled!");
        if (lifeManager != null) {
            lifeManager.close();
        }
    }
}
