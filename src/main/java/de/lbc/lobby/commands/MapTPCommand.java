package de.lbc.lobby.commands;

import de.lbc.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MapTPCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player plr = (Player) sender;
            if(args.length == 1){
                World wld = Bukkit.getWorld(args[0]);
                if(wld != null){
                    plr.teleport(wld.getSpawnLocation());
                } else {
                    plr.sendMessage(Main.PREFIX + "Die Karte " + args[0] + " konnte nicht gefunden werden.");
                    plr.sendMessage(Main.PREFIX + "Es gibt folgende Maps:");
                    for (World world : Bukkit.getWorlds()) plr.sendMessage("- " + world.getName());
                }
            } else plr.sendMessage(Main.PREFIX + "Nutze /" +command.getName()+ " [MAP_NAME]");
        } else sender.sendMessage("Dieser Befehl kann nur durch Spieler ausgeführt werden!");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if(args.length == 1){
            for (World world : Bukkit.getWorlds()){
                list.add(world.getName());
            }
        }
        return list;
    }
}
