package me.chasertw123.minigames.hub.features.parkour;

import me.chasertw123.minigames.hub.Main;

import java.util.UUID;

public class UserCourseCompletionData {

    private UUID courseId;
    private int currentCheckpoint;

    public UserCourseCompletionData(UUID courseId, int currentCheckpoint) {
        this.courseId = courseId;
        this.currentCheckpoint = currentCheckpoint;
    }

    public int getCurrentCheckpoint() {
        return currentCheckpoint;
    }

    public Course getCourse() {
        return Main.getCourseManager().getCourse(courseId);
    }

    public int getPercentageCompleted() {
        return (int) (((double) currentCheckpoint / (double) getCourse().getTotalCheckPoints()) * 100);
    }

    public boolean hasFinished() {
        return currentCheckpoint == getCourse().getTotalCheckPoints();
    }

    public boolean hasStarted() {
        return currentCheckpoint > 0;
    }
}
