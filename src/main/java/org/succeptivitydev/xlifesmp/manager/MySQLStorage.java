package org.succeptivitydev.xlifesmp.manager;

import org.succeptivitydev.xlifesmp.ThirdLifePlugin;
import org.succeptivitydev.xlifesmp.model.PlayerData;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MySQLStorage implements Storage {

    private final ThirdLifePlugin plugin;
    private Connection conn;
    private String tableName;

    public MySQLStorage(ThirdLifePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        String host = plugin.getConfig().getString("storage.mysql.host", "localhost");
        int port = plugin.getConfig().getInt("storage.mysql.port", 3306);
        String database = plugin.getConfig().getString("storage.mysql.database", "minecraft");
        String user = plugin.getConfig().getString("storage.mysql.username", "root");
        String pass = plugin.getConfig().getString("storage.mysql.password", "");
        tableName = plugin.getConfig().getString("storage.mysql.table", "xlifesmp_players");

        String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&autoReconnect=true", host, port, database);
        try {
            conn = DriverManager.getConnection(url, user, pass);
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("CREATE TABLE IF NOT EXISTS `" + tableName + "` (uuid VARCHAR(36) PRIMARY KEY, lives INT NOT NULL)");
            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Could not initialize MySQL storage: " + e.getMessage());
        }
    }

    @Override
    public Map<UUID, PlayerData> loadAll() {
        Map<UUID, PlayerData> out = new HashMap<>();
        if (conn == null) return out;
        String sql = "SELECT uuid, lives FROM `" + tableName + "`";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String uuidStr = rs.getString("uuid");
                    int lives = rs.getInt("lives");
                    try {
                        UUID uuid = UUID.fromString(uuidStr);
                        out.put(uuid, new PlayerData(uuid, lives));
                    } catch (IllegalArgumentException ignored) {}
                }
            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Could not load players from MySQL: " + e.getMessage());
        }
        return out;
    }

    @Override
    public PlayerData loadPlayer(UUID uuid) {
        if (conn == null) return null;
        String sql = "SELECT lives FROM `" + tableName + "` WHERE uuid = ? LIMIT 1";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, uuid.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int lives = rs.getInt("lives");
                    return new PlayerData(uuid, lives);
                }
            }
        } catch (SQLException e) {
            plugin.getLogger().severe("Could not load player from MySQL: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void savePlayer(PlayerData data) {
        if (conn == null) return;
        String sql = "INSERT INTO `" + tableName + "` (uuid, lives) VALUES (?, ?) ON DUPLICATE KEY UPDATE lives = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, data.getUuid().toString());
            ps.setInt(2, data.getLives());
            ps.setInt(3, data.getLives());
            ps.executeUpdate();
        } catch (SQLException e) {
            plugin.getLogger().severe("Could not save player to MySQL: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                plugin.getLogger().severe("Failed to close MySQL connection: " + e.getMessage());
            }
        }
    }
}

