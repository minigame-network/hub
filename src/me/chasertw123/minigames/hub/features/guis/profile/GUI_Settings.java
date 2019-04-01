package me.chasertw123.minigames.hub.features.guis.profile;

import me.chasertw123.minigames.core.user.data.settings.Setting;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.bossbar.Loop_AnimatedBossBar;
import me.chasertw123.minigames.hub.features.scoreboards.SB_Hub;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.shared.rank.RankType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.Arrays;

/**
 * Created by Chase on 8/6/2017.
 */
public class GUI_Settings extends AbstractGui {

    private static final int FILLER_SLOTS[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 45, 46, 47, 48, 50, 51, 52, 53};

    private me.chasertw123.minigames.core.user.User pp;
    private int slot = 18;

    public GUI_Settings(User user) {
        super(6, "Your Settings", user.getCoreUser());
        this.pp = user.getCoreUser();

        // Player Visibility Setting
        setItem(Setting.PLAYER_VISIBILITY.toItemStack(pp), 18, (s, c, p) -> {});
        setItem(craftButton(Setting.PLAYER_VISIBILITY), 27, (s, c, p) -> {

            boolean value = !pp.getSetting(Setting.PLAYER_VISIBILITY);

            pp.setSetting(Setting.PLAYER_VISIBILITY, value);
            pp.togglePlayerVisibility(value);
            user.updatePlayerVisibility();
            new GUI_Settings(user);
        });

        // Chat Visibility Setting
        setItem(Setting.CHAT_VISIBILITY.toItemStack(pp), 19, (s, c, p) -> {});
        setItem(craftButton(Setting.CHAT_VISIBILITY), 28, (s, c, p) -> {
            if (pp.getSetting(Setting.CHAT_VISIBILITY)) {

                if (pp.getRank().getRankType() == RankType.STAFF) {
                    pp.sendPrefixedMessage(ChatColor.RED + "Staff is not allowed to ignore the chat!");
                    return;
                }

                pp.setSetting(Setting.CHAT_VISIBILITY, false);
                new GUI_Settings(user);
                return;
            }

            pp.setSetting(Setting.CHAT_VISIBILITY, true);
            new GUI_Settings(user);
        });

        // Private Messages Setting
        setItem(Setting.PRIVATE_MESSAGES.toItemStack(pp), 20, (s, c, p) -> {});
        setItem(craftButton(Setting.PRIVATE_MESSAGES), 29, (s, c, p) -> {
            if (pp.getSetting(Setting.PRIVATE_MESSAGES)) {
                pp.setSetting(Setting.PRIVATE_MESSAGES, false);
                new GUI_Settings(user);
                return;
            }

            pp.setSetting(Setting.PRIVATE_MESSAGES, true);
            new GUI_Settings(user);
        });

        // Friend Requests Setting
        setItem(Setting.FRIEND_REQUESTS.toItemStack(pp), 21, (s, c, p) -> {});
        setItem(craftButton(Setting.FRIEND_REQUESTS), 30, (s, c, p) -> {
            if (pp.getSetting(Setting.FRIEND_REQUESTS)) {
                pp.setSetting(Setting.FRIEND_REQUESTS, false);
                new GUI_Settings(user);
                return;
            }

            pp.setSetting(Setting.FRIEND_REQUESTS, true);
            new GUI_Settings(user);
        });

        // Party Requests Setting
        setItem(Setting.PARTY_REQUESTS.toItemStack(pp), 22, (s, c, p) -> {});
        setItem(craftButton(Setting.PARTY_REQUESTS), 31, (s, c, p) -> {
            if (pp.getSetting(Setting.PARTY_REQUESTS)) {
                pp.setSetting(Setting.PARTY_REQUESTS, false);
                new GUI_Settings(user);
                return;
            }

            pp.setSetting(Setting.PARTY_REQUESTS, true);
            new GUI_Settings(user);
        });

        // Auto Message Setting
        setItem(Setting.AUTO_MESSAGES.toItemStack(pp), 23, (s, c, p) -> {});
        setItem(craftButton(Setting.AUTO_MESSAGES), 32, (s, c, p) -> {
            if (pp.getSetting(Setting.AUTO_MESSAGES)) {

                if (pp.getRank().getRankType() == RankType.MEMBER && !pp.isDeluxe()) {
                    pp.sendPrefixedMessage(ChatColor.RED + "You need to have " + ChatColor.GOLD + "DELUXE" + ChatColor.RED + " to disable auto messages!");
                    pp.getPlayer().playSound(pp.getPlayer().getLocation(), Sound.CHICKEN_EGG_POP, 1F, 1F);
                    pp.getPlayer().closeInventory();
                    return;
                }

                pp.setSetting(Setting.AUTO_MESSAGES, false);
                new GUI_Settings(user);
                return;
            }

            pp.setSetting(Setting.AUTO_MESSAGES, true);
            new GUI_Settings(user);
        });

        // Scoreboard Setting
        setItem(Setting.SCOREBOARD.toItemStack(pp), 24, (s, c, p) -> {});
        setItem(craftButton(Setting.SCOREBOARD), 33, (s, c, p) -> {
            if (pp.getSetting(Setting.SCOREBOARD)) {

                if (pp.getRank().getRankType() == RankType.MEMBER && !pp.isDeluxe()) {
                    pp.sendPrefixedMessage(ChatColor.RED + "You need to have " + ChatColor.GOLD + "DELUXE" + ChatColor.RED + " to disable the scoreboard!");
                    pp.getPlayer().playSound(pp.getPlayer().getLocation(), Sound.CHICKEN_EGG_POP, 1F, 1F);
                    pp.getPlayer().closeInventory();
                    return;
                }

                pp.removeScoreboard();
                pp.setSetting(Setting.SCOREBOARD, false);
                new GUI_Settings(user);
                return;
            }

            pp.setScoreboard(new SB_Hub(user));
            pp.setSetting(Setting.SCOREBOARD, true);
            new GUI_Settings(user);
        });

        // Boss Bar Setting
        setItem(Setting.BOSS_BAR.toItemStack(pp), 25, (s, c, p) -> {});
        setItem(craftButton(Setting.BOSS_BAR), 34, (s, c, p) -> {
            if (pp.getSetting(Setting.BOSS_BAR)) {

                if (pp.getRank().getRankType() == RankType.MEMBER && !pp.isDeluxe()) {
                    pp.sendPrefixedMessage(ChatColor.RED + "You need to have " + ChatColor.GOLD + "DELUXE" + ChatColor.RED +  " to disable the boss bar!");
                    pp.getPlayer().playSound(pp.getPlayer().getLocation(), Sound.CHICKEN_EGG_POP, 1F, 1F);
                    pp.getPlayer().closeInventory();
                    return;
                }

                Loop_AnimatedBossBar.getInstance().getBossBar().removePlayer(pp.getUUID());
                pp.setSetting(Setting.BOSS_BAR, false);
                new GUI_Settings(user);
                return;
            }

            Loop_AnimatedBossBar.getInstance().getBossBar().addPlayer(pp.getUUID());
            pp.setSetting(Setting.BOSS_BAR, true);
            new GUI_Settings(user);
        });

        // Auto Rejoin Setting
        setItem(Setting.AUTO_REJOIN.toItemStack(pp), 26, (s, c, p) -> {});
        setItem(craftButton(Setting.AUTO_REJOIN), 35, (s, c, p) -> {
            if (pp.getSetting(Setting.PARTY_REQUESTS)) {
                pp.setSetting(Setting.AUTO_REJOIN, false);
                new GUI_Settings(user);
                return;
            }

            pp.setSetting(Setting.AUTO_REJOIN, true);
            new GUI_Settings(user);
        });

        Arrays.stream(FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, " "), slot, (s, c, p) -> {}));
        setItem(pp.toItemStack(), 4, (s, c, p) -> {});
        setItem(new cItemStack(Material.DIODE, ChatColor.GREEN + "Return to profile"), 49, (s, c, p) -> new GUI_Profile(user));
    }

    private cItemStack craftButton(Setting setting) {
        if (pp.getSetting(setting))
            return new cItemStack(Material.INK_SACK, 1, (short) 10, ChatColor.GREEN + "Enabled").addLore(ChatColor.GRAY + "Click to disable!");

        return new cItemStack(Material.INK_SACK, 1, (short) 8, ChatColor.RED + "Disabled").addLore(ChatColor.GRAY + "Click to enable!");
    }
}
