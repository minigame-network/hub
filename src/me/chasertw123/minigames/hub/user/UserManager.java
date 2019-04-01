package me.chasertw123.minigames.hub.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Chase on 1/5/2018.
 */
public class UserManager {

    private Map<UUID, User> users = new HashMap<>();

    public User get(UUID uuid) {
        return users.get(uuid);
    }

    public boolean has(UUID uuid) {
        return users.containsKey(uuid);
    }

    public void add(User user) {
        users.put(user.getUuid(), user);
    }

    public Collection<User> toCollection(){
        return users.values();
    }

    public void remove(UUID uuid){
        users.remove(uuid);
    }

}
