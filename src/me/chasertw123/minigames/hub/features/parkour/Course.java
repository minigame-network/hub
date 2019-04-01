package me.chasertw123.minigames.hub.features.parkour;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Chase on 1/13/2018.
 */
public class Course {

    private UUID courseId;
    private String name, description;
    private CourseDifficulty difficulty;
    private Location start, finish;
    private HashMap<Integer, Location> checkpoints;

    public Course(UUID courseId, String name, String description, CourseDifficulty difficulty, Location start, Location finish, HashMap<Integer, Location> checkpoints) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.start = start;
        this.finish = finish;
        this.checkpoints = checkpoints;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseDifficulty getDifficulty() {
        return difficulty;
    }

    public Location getStart() {
        return start;
    }

    public Location getFinish() {
        return finish;
    }

    public HashMap<Integer, Location> getCheckpoints() {
        return checkpoints;
    }

    public Location getCheckpoint(int checkpoint) {
        return checkpoints.get(checkpoint);
    }

    public int getTotalCheckPoints() {
        return checkpoints.size();
    }

    public void addCheckpoint(Location location) {
        checkpoints.put(this.getTotalCheckPoints() + 1, location);
    }
}
