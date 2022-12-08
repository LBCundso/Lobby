package de.lbc.lobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerHeathListener implements Listener {
    @EventHandler
    public void onPlrDamage(EntityDamageEvent e) {
        if (JoinQuitListener.getSpawn() == null) return;
        if (!JoinQuitListener.getSpawn().getWorld().equals(e.getEntity().getWorld())) return;
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
            ((Player) e.getEntity()).setHealth(20);
        }
    }
    @EventHandler
    public void onPlrHunger(FoodLevelChangeEvent e){
        if (JoinQuitListener.getSpawn() == null) return;
        if (!JoinQuitListener.getSpawn().getWorld().equals(e.getEntity().getWorld())) return;
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
            e.getEntity().setFoodLevel(20);
        }
    }
}
