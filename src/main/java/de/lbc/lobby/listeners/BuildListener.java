package de.lbc.lobby.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (JoinQuitListener.getSpawn() == null) {
            return;
        }
        if (!JoinQuitListener.getSpawn().getWorld().equals(e.getPlayer().getWorld())) {
            return;
        }
        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE) && e.getPlayer().hasPermission("lobby.build")) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (JoinQuitListener.getSpawn() == null) {
            return;
        }
        if (!JoinQuitListener.getSpawn().getWorld().equals(e.getPlayer().getWorld())) {
            return;
        }
        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE) && e.getPlayer().hasPermission("lobby.build")) {
            return;
        }
        e.setCancelled(true);
    }

}
