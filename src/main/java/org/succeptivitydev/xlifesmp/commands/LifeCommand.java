package org.succeptivitydev.xlifesmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.succeptivitydev.xlifesmp.ThirdLifePlugin;
import org.succeptivitydev.xlifesmp.model.PlayerData;
import org.succeptivitydev.xlifesmp.CustomStuff.Items.Items;
import org.bukkit.OfflinePlayer;

public class LifeCommand implements CommandExecutor {

    private final ThirdLifePlugin plugin;

    public LifeCommand(ThirdLifePlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String str, @NotNull String @NotNull [] args) {
        if (cmd.getName().equalsIgnoreCase("life")) {
        if (!(s instanceof Player player)) {
            s.sendMessage("This command can only be run by a player.");
            return true;
        }
            PlayerData data = plugin
                .getLifeManager()
                .getOrCreate(player.getUniqueId());

        player.sendMessage("Lives: " + data.getLives());
        player.sendMessage("Status: " + data.getState());
        }
        if (cmd.getName().equalsIgnoreCase("setlives")) {
            if (s.hasPermission("xlifesmp.setlives")) {
                if (args.length!=2){
                    s.sendMessage("UseCase: /setlives <player> <lives>");
                    return true;
                }
                Player playername =  Bukkit.getPlayer(args[0]);
                if (playername != null) {
                    int lives = Integer.parseInt(args[1]);
                    plugin.getLifeManager().setLives(playername.getUniqueId(), lives);
                } else {
                    s.sendMessage("Player not found.");
                }


            } else {
                s.sendMessage("You do not have permission to use this command.");
            }
        }
        if(cmd.getName().equalsIgnoreCase("revive")){
            // Two ways to revive: players with permission can revive freely
            // or a player can consume a Revive Token item to revive someone.
            if (args.length != 1) {
                s.sendMessage("UseCase: /revive <player>");
                return true;
            }

            // target may be offline; use OfflinePlayer to resolve UUID
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (target == null || target.getUniqueId() == null) {
                s.sendMessage("Player not found.");
                return true;
            }

            // If sender has permission, allow revive without consuming an item
            if (s.hasPermission("xlifesmp.revive")) {
                plugin.getLifeManager().setLives(target.getUniqueId(), 6);
                s.sendMessage("Player revived.");
                return true;
            }

            // Otherwise, if sender is a player, check for Revive Token in their inventory
            if (s instanceof Player executor) {
                boolean consumed = false;
                var inv = executor.getInventory();
                for (int i = 0; i < inv.getSize(); i++) {
                    var item = inv.getItem(i);
                    if (item != null && Items.isReviveItem(item, plugin)) {
                        // consume one
                        if (item.getAmount() > 1) {
                            item.setAmount(item.getAmount() - 1);
                            inv.setItem(i, item);
                        } else {
                            inv.setItem(i, null);
                        }
                        consumed = true;
                        break;
                    }
                }

                if (consumed) {
                    plugin.getLifeManager().setLives(target.getUniqueId(), 6);
                    executor.sendMessage("You used a Revive Token and revived " + args[0]);
                } else {
                    executor.sendMessage("You need a Revive Token to revive players.");
                }
                return true;
            }
        }
        return true;
    }
        return false;
}}
