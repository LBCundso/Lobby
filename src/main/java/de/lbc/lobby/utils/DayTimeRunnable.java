package de.lbc.lobby.utils;

import de.lbc.lobby.listeners.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class DayTimeRunnable extends BukkitRunnable {
    @Override
    public void run() {
        if(JoinQuitListener.getSpawn() != null){
            JoinQuitListener.getSpawn().getWorld().setTime(1000);
        }
    }
}
