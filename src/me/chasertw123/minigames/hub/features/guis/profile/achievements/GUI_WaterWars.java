package me.chasertw123.minigames.hub.features.guis.profile.achievements;

import me.chasertw123.minigames.core.user.data.achievements.Achievement;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.shared.framework.ServerGameType;

/**
 * Created by Chase on 7/22/2017.
 */
public class GUI_WaterWars extends GUI_Achievement {

    private static final Achievement[]

            SOLO_KILLS_SET = {Achievement.WATER_WARS_SOLO_KILLS_I, Achievement.WATER_WARS_SOLO_KILLS_II, Achievement.WATER_WARS_SOLO_KILLS_III,
                    Achievement.WATER_WARS_SOLO_KILLS_IV, Achievement.WATER_WARS_SOLO_KILLS_V, Achievement.WATER_WARS_SOLO_KILLS_VI},

            SOLO_WINS_SET = {Achievement.WATER_WARS_SOLO_WINS_I, Achievement.WATER_WARS_SOLO_WINS_II, Achievement.WATER_WARS_SOLO_WINS_III, Achievement.WATER_WARS_SOLO_WINS_IV,
                    Achievement.WATER_WARS_SOLO_WINS_V, Achievement.WATER_WARS_SOLO_WINS_VI},

            SOLO_GAMES_PLAYED = {Achievement.WATER_WARS_SOLO_GAMES_PLAYED_I, Achievement.WATER_WARS_SOLO_GAMES_PLAYED_II, Achievement.WATER_WARS_SOLO_GAMES_PLAYED_III,
                    Achievement.WATER_WARS_SOLO_GAMES_PLAYED_IV, Achievement.WATER_WARS_SOLO_GAMES_PLAYED_V, Achievement.WATER_WARS_SOLO_GAMES_PLAYED_VI},

            TEAM_KILLS_SET = {Achievement.WATER_WARS_TEAM_KILLS_I, Achievement.WATER_WARS_TEAM_KILLS_II, Achievement.WATER_WARS_TEAM_KILLS_III,
                    Achievement.WATER_WARS_TEAM_KILLS_IV, Achievement.WATER_WARS_TEAM_KILLS_V, Achievement.WATER_WARS_TEAM_KILLS_VI},

            TEAM_WINS_SET = {Achievement.WATER_WARS_TEAM_WINS_I, Achievement.WATER_WARS_TEAM_WINS_II, Achievement.WATER_WARS_TEAM_WINS_III, Achievement.WATER_WARS_TEAM_WINS_IV,
                    Achievement.WATER_WARS_TEAM_WINS_V, Achievement.WATER_WARS_TEAM_WINS_VI},

            TEAM_GAMES_PLAYED = {Achievement.WATER_WARS_TEAM_GAMES_PLAYED_I, Achievement.WATER_WARS_TEAM_GAMES_PLAYED_II, Achievement.WATER_WARS_TEAM_GAMES_PLAYED_III,
                    Achievement.WATER_WARS_TEAM_GAMES_PLAYED_IV, Achievement.WATER_WARS_TEAM_GAMES_PLAYED_V, Achievement.WATER_WARS_TEAM_GAMES_PLAYED_VI},

            CHESTS_LOOTED = {Achievement.WATER_WARS_LOOT_CHEST_I, Achievement.WATER_WARS_LOOT_CHEST_II, Achievement.WATER_WARS_LOOT_CHEST_III,
                    Achievement.WATER_WARS_LOOT_CHEST_IV, Achievement.WATER_WARS_LOOT_CHEST_V, Achievement.WATER_WARS_LOOT_CHEST_VI},

            ITEMS_ENCHANTED = {Achievement.WATER_WARS_ENCHANTED_I, Achievement.WATER_WARS_ENCHANTED_II, Achievement.WATER_WARS_ENCHANTED_III,
                    Achievement.WATER_WARS_ENCHANTED_IV, Achievement.WATER_WARS_ENCHANTED_V, Achievement.WATER_WARS_ENCHANTED_VI};

    public GUI_WaterWars(User user) {
        super(user, ServerGameType.WATER_WARS);

        setItem(craftItemStack(SOLO_KILLS_SET), 10, (s, c, p) -> {});
        setItem(craftItemStack(SOLO_WINS_SET), 11, (s, c, p) -> {});
        setItem(craftItemStack(SOLO_GAMES_PLAYED), 12, (s, c, p) -> {});

        setItem(craftItemStack(TEAM_KILLS_SET), 14, (s, c, p) -> {});
        setItem(craftItemStack(TEAM_WINS_SET), 15, (s, c, p) -> {});
        setItem(craftItemStack(TEAM_GAMES_PLAYED), 16, (s, c, p) -> {});

        setItem(craftItemStack(CHESTS_LOOTED), 29, (s, c, p) -> {});
        setItem(craftItemStack(ITEMS_ENCHANTED), 30, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.WATER_WARS_WATERY_GRAVE),32, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.WATER_WARS_ONE_PUNCH), 33, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.WATER_WARS_DOMINATION), 38, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.WATER_WARS_BULLSEYE), 39, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.WATER_WARS_CAGE), 40, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.WATER_WARS_KIT), 41, (s, c, p) -> {});
        setItem(craftItemStack(Achievement.WATER_WARS_PERK), 42, (s, c, p) -> {});
    }
}
