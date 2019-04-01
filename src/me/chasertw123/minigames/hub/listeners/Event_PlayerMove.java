package me.chasertw123.minigames.hub.listeners;

import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.parkour.Course;
import me.chasertw123.minigames.hub.user.User;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Map;

public class Event_PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        User user = Main.getUserManager().get(e.getPlayer().getUniqueId());
        if (user.getActiveMorph() != null)
            user.getActiveMorph().updateEntityLocation();

        // Check if this is a checkpoint for parkour
        for(Course c : Main.getCourseManager().getActiveCourses()) {
            for(Map.Entry<Integer, Location> checkPointData : c.getCheckpoints().entrySet()) {
                Location checkPoint = checkPointData.getValue();

                if(checkPoint.getBlockX() == e.getPlayer().getLocation().getBlockX()
                        && checkPoint.getBlockY() == e.getPlayer().getLocation().getBlockY()
                        && checkPoint.getBlockZ() == e.getPlayer().getLocation().getBlockZ()
                        && user.getCourseData(c.getCourseId()).getCurrentCheckpoint() < checkPointData.getKey()
                        && user.getCourseData(c.getCourseId()).getCurrentCheckpoint() == checkPointData.getKey() - 1) {

                    user.getPlayer().sendMessage(ChatColor.GRAY + "Completed course "
                            + ChatColor.AQUA + c.getName() + ChatColor.GRAY + " checkpoint #" + ChatColor.AQUA + checkPointData.getKey()
                            + ChatColor.GRAY + "!");
                    user.getParkourCourseData().put(c.getCourseId() + "", checkPointData.getKey());

                    if(user.getCourseData(c.getCourseId()).hasFinished()) {
                        user.getPlayer().sendMessage(ChatColor.GOLD + "Nice work! You finished the parkour " + ChatColor.AQUA + c.getName() + ChatColor.GOLD + "!");
                    }
                }
            }
        }
    }
}
