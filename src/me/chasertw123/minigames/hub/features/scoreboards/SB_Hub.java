package me.chasertw123.minigames.hub.features.scoreboards;

import me.chasertw123.minigames.core.Main;
import me.chasertw123.minigames.core.user.data.stats.Stat;
import me.chasertw123.minigames.core.utils.scoreboard.Entry;
import me.chasertw123.minigames.core.utils.scoreboard.EntryBuilder;
import me.chasertw123.minigames.core.utils.scoreboard.ScoreboardHandler;
import me.chasertw123.minigames.core.utils.scoreboard.SimpleScoreboard;
import me.chasertw123.minigames.core.utils.scoreboard.animate.HighlightedString;
import me.chasertw123.minigames.hub.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SB_Hub extends SimpleScoreboard {

    public SB_Hub(User user) {
        super(user.getPlayer());

        this.setUpdateInterval(5L);
        this.setHandler(new ScoreboardHandler() {

            private final HighlightedString title = new HighlightedString(Main.getServerConfiguration().getScoreboardTitle(),
                    Main.getServerConfiguration().getScoreboardNormalFormat(), Main.getServerConfiguration().getScoreboardHighligtedFormat());

            @Override
            public String getTitle(Player player) {
                return title.next();
            }

            @Override
            public List<Entry> getEntries(Player player) {

                EntryBuilder eb = new EntryBuilder();

                eb.blank();
                eb.next(ChatColor.YELLOW + "" + ChatColor.BOLD + "Coins" + ChatColor.GRAY + "" + ChatColor.GRAY + ": " + ChatColor.GREEN + user.getCoreUser().getStat(Stat.COINS));
                eb.next(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Crystals" + ChatColor.GRAY + "" + ChatColor.GRAY + ": " + ChatColor.GREEN + user.getCoreUser().getStat(Stat.CRYSTALS));
                eb.next(ChatColor.AQUA + "" + ChatColor.BOLD + "Chests" + ChatColor.GRAY + "" + ChatColor.GRAY + ": " + ChatColor.GREEN + user.getCoreUser().getTotalChests());
                eb.blank();
                eb.next(ChatColor.GREEN + "" + ChatColor.BOLD + "Recent Activity");

                int i = 5;
                TreeMap<Long, String> sortedActivity = new TreeMap<>(user.getCoreUser().getRecentActivity());
                if (sortedActivity.size() > 0)
                    for (Map.Entry<Long, String> entry : sortedActivity.descendingMap().entrySet()) {

                        if (i-- == 0)
                            break;

                        eb.next(ChatColor.WHITE + entry.getValue());
                    }

                else
                    eb.next(ChatColor.WHITE + "None :(");

                return eb.build();
            }
        });
    }
}
