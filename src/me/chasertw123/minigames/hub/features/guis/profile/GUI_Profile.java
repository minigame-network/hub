package me.chasertw123.minigames.hub.features.guis.profile;

import me.chasertw123.minigames.core.api.CustomHead;
import me.chasertw123.minigames.core.user.data.achievements.Achievement;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.guis.profile.achievements.GUI_Achievements;
import me.chasertw123.minigames.hub.user.User;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by Chase on 7/20/2017.
 */
public class GUI_Profile extends AbstractGui {

    // TODO: Re organize and add additional GUIs

    private static final int FILLER_SLOTS[] = {0, 1, 2, 3, 5, 6, 7, 8, 45, 46, 47, 48, 50, 51, 52, 53};

    public GUI_Profile(User user) {
        super(6, "My Profile", user.getCoreUser());
        me.chasertw123.minigames.core.user.User pp = user.getCoreUser();
        fill(pp, pp);

//        setItem(CustomHead.LETTERCUBE_F.craftSkull().setDisplayName(ChatColor.GREEN + "Friends").addFancyLore("View and manage your PVPCentral friends, and check out other friends' profiles.",
//                ChatColor.GRAY.toString()).addLore("", ChatColor.YELLOW + "Click to view your friends list!"), 20, (s, c, p) -> {});
//
//        setItem(CustomHead.LETTERCUBE_P.craftSkull().setDisplayName(ChatColor.GREEN + "Party").addFancyLore("View and manage who you're going to be teaming up with in your games.", ChatColor.GRAY.toString())
//                .addLore("", ChatColor.YELLOW + "Click to view your party!"), 29, (s, c, p) -> {});

        /*
        OLD:
        F: 20,
        P: 29,

        S: 23
        A: 24
        Pref: 25
        B: 32
        T: 34
         */

        setItem(new cItemStack(Material.PAPER,  ChatColor.GREEN + "Stats Viewer").addFancyLore("Showcases your stats for each gamemode and an overview of all.", ChatColor.GRAY.toString())
                .addLore("", ChatColor.YELLOW + "Click to view your stats!"), 21, (s, c, p) -> new GUI_Stats(user));

        int unlocked = pp.getAchievements().size(), total = Achievement.values().length;
        setItem(new cItemStack(Material.DIAMOND, ChatColor.GREEN + "Achievements").addFancyLore("Track your progress as you unlock Achievements and earn awesome rewards!", ChatColor.GRAY.toString())
                .addLore("", ChatColor.GRAY + "Unlocked Achievements: " + ChatColor.RED + unlocked + "/" + total + ChatColor.DARK_GRAY + " (" + (int) ((unlocked * 100.0f) / total) + "%)", "",
                        ChatColor.YELLOW + "Click to view your achievements!"), 22, (s, c, p) -> new GUI_Achievements(user));

        setItem(new cItemStack(Material.REDSTONE_COMPARATOR, ChatColor.GREEN + "Preferences").addFancyLore("Allows you to edit and control various personal settings.", ChatColor.GRAY.toString())
                .addLore("", ChatColor.YELLOW + "Click to edit your preferences!"), 23, (s, c, p) -> new GUI_Settings(user));

        int boosters = 0;
        for (int i : pp.getBoosters().values())
            boosters += i;

        setItem(new cItemStack(Material.DOUBLE_PLANT, ChatColor.GREEN + "Boosters").addFancyLore("Activate your personal and network boosters for more coins.", ChatColor.GRAY.toString())
                .addLore("", ChatColor.GRAY + "Unused Boosters: " + ChatColor.RED + boosters, "", ChatColor.YELLOW + "Click to activate boosters!"), 30, (s, c, p) -> new GUI_GameBoosters(user));

//        long currentTime = System.currentTimeMillis();
//        int unopenedPackages = 0;
//        for (TimeReward timeReward : TimeReward.values())
//            if (pp.getMailboxRewards().get(timeReward) + timeReward.getRefreshTime() <= currentTime)
//                unopenedPackages++;
//
//        // TODO: Check when last voted
//
//        setItem(CustomHead.MAIL_PACKAGE.craftSkull().setDisplayName(ChatColor.GREEN + "Mailbox").addFancyLore("View and collect daily and monthly rewards. Grab yourself some free experience and loot!", ChatColor.GRAY.toString())
//                .addLore("", ChatColor.GRAY + "Unopened Mail: " + ChatColor.RED + unopenedPackages, "", ChatColor.YELLOW + "Click to open your mailbox!"), 33, (s, c, p) -> new Gui_Mailbox(pp));

        setItem(CustomHead.TREASURE_CHEST_BROWN.craftSkull().setDisplayName(ChatColor.GREEN + "Treasure Chests").addFancyLore("Want some awesome collectibles? Open these treasure chests" +
                " and you might just get lucky.", ChatColor.GRAY.toString()).addLore("", ChatColor.GRAY + "Unopened Chests: " + ChatColor.RED + pp.getTotalChests(), "",
                ChatColor.YELLOW + "Click to view your treasure chests!"), 32, (s, c, p) -> {});
    }

    public GUI_Profile(me.chasertw123.minigames.core.user.User pp, me.chasertw123.minigames.core.user.OfflineUser opp) {
        super(6, opp.getUsername(), pp);
        fill(pp, opp);

        // TODO: Create profile menu
    }

    private void fill(me.chasertw123.minigames.core.user.User pp, me.chasertw123.minigames.core.user.OfflineUser opp) {
        setItem(opp.toItemStack(), 4, (s, c, p) -> {});
        setItem(new cItemStack(Material.DIODE, ChatColor.GREEN + "Close menu"), 49, (s, c, p) -> pp.getPlayer().closeInventory());
        Arrays.stream(FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, " "), slot, (s, c, p) -> {}));
    }

}
