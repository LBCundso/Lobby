package de.lbc.lobby.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HoloCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player plr = (Player) sender;
            Location loc = plr.getLocation();
            if (args.length >= 1) {
                if (args[0].equals("remove")) {
                    Collection<Entity> entities = plr.getWorld().getNearbyEntities(plr.getLocation(), 1, 1, 1);
                    for (Entity entity : entities) {
                        if (entity instanceof ArmorStand) {
                            entity.remove();
                            sender.sendMessage(ChatColor.RED + "Alle ArmorStands im Radius von einem Block wurden entfernt.");
                            break;
                        }
                    }
                } else {
                    ArmorStand holo = (ArmorStand) plr.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                    String name = "";
                    for (String arg : args) {
                        name += " " + arg;
                    }

                    String holoName = name.replace("&", "§");
                    holo.setCustomName(holoName);
                    holo.setCustomNameVisible(true);
                    holo.setVisible(false);
                    holo.setGravity(false);
                    plr.sendMessage(ChatColor.GREEN + "Hologramm erstellt.");
                }
            } else sender.sendMessage("/holo [TEXT/remove]");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if(args.length == 1){
            list.add("INSERTTEXT");
            list.add("remove");
        }

        return list;
    }
}
