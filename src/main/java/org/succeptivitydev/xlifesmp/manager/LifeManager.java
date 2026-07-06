package org.succeptivitydev.xlifesmp.manager;

import org.succeptivitydev.xlifesmp.ThirdLifePlugin;
import org.succeptivitydev.xlifesmp.model.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LifeManager {

    private final ThirdLifePlugin plugin;
    private final Map<UUID, PlayerData> playerdata = new HashMap<>();
    private Storage storage;

    public LifeManager(ThirdLifePlugin plugin) {
        this.plugin = plugin;

        // Choose storage from config (default: yaml)
        String type = plugin.getConfig().getString("storage.type", "yaml");
        if ("mysql".equalsIgnoreCase(type)) {
            storage = new MySQLStorage(plugin);
        } else {
            storage = new YamlStorage(plugin);
        }
        storage.init();

        // Load existing players from storage
        playerdata.putAll(storage.loadAll());
    }

    // Backwards-compatible no-arg constructor (keeps behavior in existing code where LifeManager()
    // was used). This will use a no-op storage and won't persist data. Prefer using the
    // LifeManager(ThirdLifePlugin) constructor.
    public LifeManager() {
        this.plugin = null;
        this.storage = new Storage() {
            @Override public void init() {}
            @Override public java.util.Map<UUID, PlayerData> loadAll() { return new HashMap<>(); }
            @Override public PlayerData loadPlayer(UUID uuid) { return null; }
            @Override public void savePlayer(PlayerData data) {}
            @Override public void close() {}
        };
    }

    public PlayerData getPlayerData(UUID uuid){
        return playerdata.get(uuid);
    }

    public PlayerData getOrCreate(UUID uuid) {
        return playerdata.computeIfAbsent(
                uuid,
                id -> {
                    // try to load from storage first
                    PlayerData p = storage.loadPlayer(id);
                    if (p != null) return p;
                    return new PlayerData(id, 6);
                }
        );
    }

    public PlayerData setLives(UUID uuid, int lives) {
        PlayerData data = playerdata.computeIfAbsent(uuid, id -> new PlayerData(id, lives));
        data.setLives(lives);
        storage.savePlayer(data);
        return data;
    }

    public boolean removeLife(UUID uuid) {
        PlayerData data = getOrCreate(uuid);

        int newLives = Math.max(0, data.getLives() - 1);
        data.setLives(newLives);

        storage.savePlayer(data);

        return newLives == 0;
    }

    public void close() {
        if (storage != null) storage.close();
    }
}