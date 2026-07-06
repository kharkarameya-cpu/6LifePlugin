package org.succeptivitydev.xlifesmp.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.succeptivitydev.xlifesmp.ThirdLifePlugin;
import org.succeptivitydev.xlifesmp.model.PlayerData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class YamlStorage implements Storage {

    private final ThirdLifePlugin plugin;
    private File dataFile;
    private FileConfiguration dataConfig;

    public YamlStorage(ThirdLifePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
        dataFile = new File(plugin.getDataFolder(), "data.yml");
        try {
            if (!dataFile.exists()) dataFile.createNewFile();
        } catch (IOException e) {
            plugin.getLogger().severe("Could not create data.yml: " + e.getMessage());
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    @Override
    public Map<UUID, PlayerData> loadAll() {
        Map<UUID, PlayerData> out = new HashMap<>();
        if (dataConfig == null) return out;
        for (String key : dataConfig.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(key);
                int lives = dataConfig.getInt(key, 6);
                out.put(uuid, new PlayerData(uuid, lives));
            } catch (IllegalArgumentException ignored) {
            }
        }
        return out;
    }

    @Override
    public PlayerData loadPlayer(UUID uuid) {
        if (dataConfig == null) return null;
        if (!dataConfig.contains(uuid.toString())) return null;
        int lives = dataConfig.getInt(uuid.toString(), 6);
        return new PlayerData(uuid, lives);
    }

    @Override
    public void savePlayer(PlayerData data) {
        if (dataConfig == null) return;
        dataConfig.set(data.getUuid().toString(), data.getLives());
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save data.yml: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        // nothing to close for YAML
    }
}

