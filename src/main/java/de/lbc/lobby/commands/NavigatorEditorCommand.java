package de.lbc.lobby.commands;

import de.lbc.lobby.Main;
import de.lbc.lobby.listeners.NavListener;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NavigatorEditorCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player plr = (Player) sender;
            ItemStack item = plr.getInventory().getItemInMainHand();
            if (item.getType() == Material.AIR && args[0].equals("add")) {
                plr.sendMessage(Main.PREFIX + "Du musst dafür einen Block in der Hand halten!");
                return false;
            }
            FileConfiguration cfg = Main.getPlugin().getConfig();

            try {
                int slot = Integer.parseInt(args[1]);

                if (args[0].equals("add")) {
                    String name = args[2].replaceAll("&", "§");
                    cfg.set("navigator." + slot + ".name", name);
                    cfg.set("navigator." + slot + ".material", item.getType().name());
                    Main.setCfgLocation("navigator." + slot + ".spawn", plr.getLocation());
                    plr.sendMessage(Main.PREFIX + "Du hast erfolgreich " + item.getType().name() + " mit dem Namen " + name + " im Navigator auf den Slot " + slot + " gesetzt.");
                } else if (args[0].equals("remove")) {
                    Main.getPlugin().getConfig().set("navigator." + slot, null);
                    plr.sendMessage(Main.PREFIX + "Du hast erfolgreich das Item im Navigatorslot " + slot + " entfernt.");
                } else {
                    sender.sendMessage(Main.PREFIX + "Nutze /" + command.getName() + " [add/remove] ZAHL");
                    return false;
                }
                Main.getPlugin().saveConfig();
                NavListener.updateNav();
            } catch (Exception e) {
                sender.sendMessage(Main.PREFIX + "Nutze /" + command.getName() + " [add/remove] ZAHL");
            }

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if(args.length == 1){
            list.add("add");
            list.add("remove");
        }

        return list;
    }
}
