package de.lbc.lobby.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (e.getWorld().equals(JoinQuitListener.getSpawn().getWorld())) {
            e.setCancelled(true);
        }
    }
}
