package me.chasertw123.minigames.hub.features.guis.profile;

import me.chasertw123.minigames.core.user.data.stats.Stat;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.shared.utils.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;

/**
 * Created by Chase on 7/21/2017.
 */
public class GUI_Stats extends AbstractGui {

    private static final int FILLER_SLOTS[] = {0, 1, 2, 3, 5, 6, 7, 8, 36, 37, 38, 39, 41, 42, 43, 44};

    /**
     * Opens a player's stats
     *
     * @param user {@link User} viewing the stats
     */
    public GUI_Stats(User user) {
        super(5, "Your Stats", user.getCoreUser());
        me.chasertw123.minigames.core.user.User pp = user.getCoreUser();

        setItem(craftTotalStatsItemStack(pp), 19, (s, c, p) -> {});
        setItem(craftWaterWarsStatsItemStack(pp), 22, (s, c, p) -> {});
        setItem(craftSpleggStatsItemStack(pp), 23, (s, c, p) -> {});
        setItem(craftMCPartyStatsItemStack(pp), 24, (s, c, p) -> {});
        setItem(craftEvolutionStatsItemStack(pp), 25, (s, c, p) -> {});

        for (int slot : FILLER_SLOTS)
            setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, " "), slot, (s, c, p) -> {});

        setItem(pp.toItemStack(), 4, (s, c, p) -> {});
        setItem(new cItemStack(Material.DIODE, ChatColor.GREEN + "Return to profile"), 40, (s, c, p) -> new GUI_Profile(user));
    }

    private cItemStack craftTotalStatsItemStack(me.chasertw123.minigames.core.user.User pp) {
        return new cItemStack(Material.BOOK, ChatColor.GREEN + "Total Stats").addLore(TotalStat.PLAYTIME.toLore(pp), "", TotalStat.KILLS.toLore(pp), TotalStat.WINS.toLore(pp),
                TotalStat.GAMES_PLAYED.toLore(pp), "", Stat.COINS.toLore(pp), Stat.TOTAL_COINS.toLore(pp));
    }

    private cItemStack craftSpleggStatsItemStack(me.chasertw123.minigames.core.user.User pp) {
        return new cItemStack(Material.EGG, ChatColor.GREEN + "Splegg Stats").addLore(
                Stat.SPLEGG_PLAYTIME.toLore(pp),
                "",
                Stat.SPLEGG_EGGS_SHOT.toLore(pp),
                Stat.SPLEGG_BLOCKS_BROKEN.toLore(pp),
                Stat.SPLEGG_GAMES_WON.toLore(pp),
                Stat.SPLEGG_GAMES_PLAYED.toLore(pp),
                Stat.SPLEGG_POWERUPS_COLLECTED.toLore(pp)
        );
    }

    private cItemStack craftWaterWarsStatsItemStack(me.chasertw123.minigames.core.user.User pp) {
        return new cItemStack(Material.WATER_LILY, ChatColor.GREEN + "Water Wars Stats").addLore(
                Stat.WATER_WARS_PLAYTIME.toLore(pp),
                "",
                Stat.WATER_WARS_SOLO_KILLS.toLore(pp),
                Stat.WATER_WARS_SOLO_GAMES_WON.toLore(pp),
                Stat.WATER_WARS_SOLO_GAMES_PLAYED.toLore(pp),
                Stat.WATER_WARS_SOLO_MOST_KILLS.toLore(pp),
                "",
//                Stat.WATER_WARS_TEAM_KILLS.toLore(pp),
//                Stat.WATER_WARS_TEAM_GAMES_WON.toLore(pp),
//                Stat.WATER_WARS_TEAM_GAMES_PLAYED.toLore(pp),
//                Stat.WATER_WARS_TEAM_MOST_KILLS.toLore(pp),
//                "",
                Stat.WATER_WARS_CHESTS_OPENED.toLore(pp),
                Stat.WATER_WARS_ITEMS_ENCHANTED.toLore(pp));
    }

    private cItemStack craftEvolutionStatsItemStack(me.chasertw123.minigames.core.user.User pp) {
        return new cItemStack(Material.DRAGON_EGG, ChatColor.GREEN + "Evolution Stats").addLore(
                Stat.EVOLUTION_PLAYTIME.toLore(pp),
                "",
                Stat.EVOLUTION_GAMES_WON.toLore(pp),
                Stat.EVOLUTION_GAMES_PLAYED.toLore(pp),
                "",
                Stat.EVOLUTION_KILLS.toLore(pp),
                Stat.EVOLUTION_EVOLVES.toLore(pp),
                Stat.EVOLUTION_ABILITIES_USED.toLore(pp)
//                Stat.EVOLUTION_POWERUPS_COLLECTED.toLore(pp),
//                "",
//                Stat.EVOLUTION_MOST_KILLS.toLore(pp),
//                Stat.EVOLUTION_MOST_ABILITIES_USED.toLore(pp),
//                Stat.EVOLUTION_MOST_POWERUPS_COLLECTED.toLore(pp));
        );
    }

    private cItemStack craftMCPartyStatsItemStack(me.chasertw123.minigames.core.user.User pp) {
        return new cItemStack(Material.CAKE, ChatColor.GREEN + "MCParty Stats").addLore(
                Stat.MCPARTY_PLAYTIME.toLore(pp),
                "",
                Stat.MCPARTY_GAMES_WON.toLore(pp),
                Stat.MCPARTY_GAMES_PLAYED.toLore(pp),
                "",
                Stat.MCPARTY_TOKENS_COLLECTED.toLore(pp),
                Stat.MCPARTY_STARS_COLLECTED.toLore(pp),
                Stat.MCPARTY_CANDY_COLLECTED.toLore(pp),
                "",
                Stat.MCPARTY_SPACES_TRAVELED.toLore(pp),
                Stat.MCPARTY_BLUE_SPACE_LANDED_ON.toLore(pp),
                Stat.MCPARTY_RED_SPACES_LANDED_ON.toLore(pp),
                Stat.MCPARTY_GREEN_SPACES_LANDED_ON.toLore(pp),
                Stat.MCPARTY_LUCKY_SPACES_LANDED_ON.toLore(pp),
                Stat.MCPARTY_DONKEY_KONG_SPACES_LANDED_ON.toLore(pp),
                Stat.MCPARTY_BOWSER_SPACES_LANDED_ON.toLore(pp)); // TODO: Add more stats
    }

    private enum TotalStat {

        KILLS("Kills", Stat.WATER_WARS_SOLO_KILLS, Stat.WATER_WARS_TEAM_KILLS),
        GAMES_PLAYED("Games Played", Stat.WATER_WARS_SOLO_GAMES_PLAYED, Stat.WATER_WARS_TEAM_GAMES_PLAYED, Stat.SPLEGG_GAMES_PLAYED),
        WINS("Wins", Stat.WATER_WARS_SOLO_GAMES_WON, Stat.WATER_WARS_TEAM_GAMES_WON, Stat.SPLEGG_GAMES_WON),
        PLAYTIME("Playtime", Stat.WATER_WARS_PLAYTIME, Stat.SPLEGG_PLAYTIME);

        private String display;
        private Stat[] stats;

        TotalStat(String display, Stat... stats) {
            this.display = display;
            this.stats = stats;
        }

        private String toLore(me.chasertw123.minigames.core.user.User pp) {

            int i = 0;
            if (toString().contains("PLAYTIME")) {
                for (Stat stat : TotalStat.PLAYTIME.stats)
                    i += pp.getStat(stat);

                return ChatColor.WHITE + display + ChatColor.GRAY + ": " + StringUtil.toFriendlyTimeFormat(i);
            }

            for (Stat stat : stats)
                i += pp.getStat(stat);

            return ChatColor.WHITE + display + ChatColor.GRAY + ": " + i;
        }
    }

}
