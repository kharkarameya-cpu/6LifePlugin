package org.succeptivitydev.xlifesmp.manager;

import org.succeptivitydev.xlifesmp.model.PlayerData;

import java.util.Map;
import java.util.UUID;

public interface Storage {
    void init();
    Map<UUID, PlayerData> loadAll();
    PlayerData loadPlayer(UUID uuid);
    void savePlayer(PlayerData data);
    void close();
}

