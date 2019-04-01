package me.chasertw123.minigames.hub.features.parkour;

import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.shared.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Chase on 1/13/2018.
 */
public class CourseManager {

    private File parkourFile;
    private FileConfiguration parkourYAML;
    private List<Course> activeCourses;

    public CourseManager() {

        parkourFile = new File(Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "parkour.yml");

        if(!parkourFile.exists()) {
            try {
                new File(Main.getInstance().getDataFolder().getAbsolutePath()).mkdirs();
                parkourFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        parkourYAML = YamlConfiguration.loadConfiguration(parkourFile);
        activeCourses = new ArrayList<>();

        // Load in the courses
        for(String key : parkourYAML.getConfigurationSection("courses").getKeys(false))
            activeCourses.add(loadCourse(key));
    }

    private Course loadCourse(String cid) {
        String startId = "courses." + cid;

        ConfigurationSection thisSection = parkourYAML.getConfigurationSection(startId);

        HashMap<Integer, Location> checkpoints = new HashMap<>();
        for(String s : thisSection.getStringList("checkpoints")) {
            String[] splitUp = s.split("!");

            checkpoints.put(Integer.parseInt(splitUp[0]), locationFromString(splitUp[1]));
        }

        return new Course(UUID.fromString(cid), thisSection.getString("name"), thisSection.getString("description"),
                CourseDifficulty.valueOf(thisSection.getString("difficulty").toUpperCase()), locationFromString(thisSection.getString("start")),
                locationFromString(thisSection.getString("finish")), checkpoints);
    }

    private String locationToString(Location location) {
        return location.getWorld() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + ","
                + location.getYaw() + "," + location.getPitch();
    }

    public Course getCourse(UUID courseId) {
        for(Course c : activeCourses)
            if(c.getCourseId().equals(courseId))
                return c;

        return null;
    }

    /*
        world,x,y,z,yaw,pitch
     */
    private Location locationFromString(String string) {
        String[] parts = string.split(",");

        return new Location(Bukkit.getServer().getWorld(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3]), Float.parseFloat(parts[4]), Float.parseFloat(parts[5]));
    }

    /*
      courses:
        courseid:
          name
          description
          difficulty
          start
          finish
          checkpoints:
            - id:location
            - id:location
     */

    private void saveConfig() {
        try {
            parkourYAML.save(parkourFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getParkourFile() {
        return parkourFile;
    }

    public FileConfiguration getParkourYAML() {
        return parkourYAML;
    }

    public List<Course> getActiveCourses() {
        return activeCourses;
    }
}
