package de.lbc.lobby.commands;

import de.lbc.lobby.Main;
import de.lbc.lobby.listeners.JoinQuitListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player plr = (Player) sender;
            JoinQuitListener.setSpawn(plr.getLocation());
            plr.sendMessage(Main.PREFIX + "§aDu hast den Spawnpunkt erfolgreich gesetzt.");
        }
        return false;
    }
}
