package me.chasertw123.minigames.hub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Event_WeatherChange implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if(!e.toWeatherState())
            return;

        e.setCancelled(true);
        e.getWorld().setWeatherDuration(0);
        e.getWorld().setThundering(false);
    }
}
