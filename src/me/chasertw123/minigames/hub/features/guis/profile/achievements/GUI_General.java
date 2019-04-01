package me.chasertw123.minigames.hub.features.guis.profile.achievements;

import me.chasertw123.minigames.core.user.data.achievements.Achievement;
import me.chasertw123.minigames.hub.user.User;

/**
 * Created by Chase on 7/22/2017.
 */
public class GUI_General extends GUI_Achievement {

    private static final Achievement[] TOTAL_COINS_SET = {Achievement.TOTAL_COINS_I, Achievement.TOTAL_COINS_II, Achievement.TOTAL_COINS_III,
            Achievement.TOTAL_COINS_IV, Achievement.TOTAL_COINS_V, Achievement.TOTAL_COINS_VI};

    public GUI_General(User user) {
        super(user, null);

        setItem(craftItemStack(Achievement.HELLO_WORLD), 10, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.PLAY_SOMETHING), 11, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.FIND_A_FRIEND), 12, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.PARTY_TIME), 13, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.PERSONAL_BOOSTER), 14, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.PERSONAL_BANKER), 15, (s, c, p) -> {});
        setItem(craftItemStack(TOTAL_COINS_SET), 16, (s, c, p) -> {});
    }
}
