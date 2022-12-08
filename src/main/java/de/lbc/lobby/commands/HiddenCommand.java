package de.lbc.lobby.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HiddenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player plr = (Player) sender;
            plr.playSound(plr, Sound.MUSIC_DISC_PIGSTEP, 0.2f, 1);
        }
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "\"No matter how ridiculous the odds may seem, within us resides the power to overcome these obstacles and achieve something beautiful. And one day, we'll look back at where we started, and be amazed at how far we've come\" ~ Technoblade (* 1999, † 2022)");
        return false;
    }
}
