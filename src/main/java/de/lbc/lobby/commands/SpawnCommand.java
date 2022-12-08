package de.lbc.lobby.commands;

import de.lbc.lobby.Main;
import de.lbc.lobby.listeners.JoinQuitListener;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player plr = (Player) sender;
            plr.setGameMode(GameMode.SURVIVAL);
            plr.teleport(JoinQuitListener.getSpawn());
            plr.playSound(plr.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,1,1);
        }

        return false;
    }
}
