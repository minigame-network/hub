package me.chasertw123.minigames.hub.features.guis.profile.achievements;

import me.chasertw123.minigames.core.user.data.achievements.Achievement;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by Chase on 7/22/2017.
 */
public class GUI_Achievement extends AbstractGui {

    private static final int FILLER_SLOTS[] = {0, 1, 2, 3, 5, 6, 7, 8, 45, 46, 47, 48, 50, 51, 52, 53};
    private me.chasertw123.minigames.core.user.User pp;

    public GUI_Achievement(User user, ServerGameType serverGameType) {
        super(6, (serverGameType == null ? "General" : serverGameType.getDisplay()) + " Achievements", user.getCoreUser());
        this.pp = user.getCoreUser();

        Arrays.stream(FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, " "), slot, (s, c, p) -> {}));
        setItem(new cItemStack(Material.DIODE, ChatColor.GREEN + "Return to achievements"), 49, (s, c, p) -> new GUI_Achievements(user));
        setItem(pp.toItemStack(), 4, (s, c, p) -> {});
    }

    /**
     * Creates an ItemStack to represent an achievement or a set of
     * achievements. Displays if completed via a strike-through, rarity
     * by color, name, and description.
     *
     * @param achievements {@link Achievement} or set of being made into item
     * @return cItemStack of with lore and display of achievements
     */
    protected cItemStack craftItemStack(Achievement... achievements) {

        if (achievements.length == 1) {

            boolean hasAchievement = pp.hasAchievement(achievements[0]);
            cItemStack i = new cItemStack(Material.INK_SACK, 1, (hasAchievement ? achievements[0].getAchievementDifficulty().getItemStackData() : 8));

            i.setDisplayName(achievements[0].getAchievementDifficulty().getChatColor() + (hasAchievement ? "" + ChatColor.STRIKETHROUGH : "") + achievements[0].getDisplay());
            i.addFancyLore(achievements[0].getDescription(), ChatColor.WHITE.toString());

            return i;
        }

        Achievement highestDifficulty = null;
        for (Achievement achievement : achievements)
            if ((pp.hasAchievement(achievement) && highestDifficulty == null) || (pp.hasAchievement(achievement) && achievement.getAchievementDifficulty().getDifficulty() > highestDifficulty.getAchievementDifficulty().getDifficulty()))
                highestDifficulty = achievement;

        short dyeColor = 8;
        if (highestDifficulty != null)
            dyeColor = highestDifficulty.getAchievementDifficulty().getItemStackData();

        cItemStack i = new cItemStack(Material.INK_SACK, 1, dyeColor, " ");

        for (Achievement a : achievements) {
            i.addLore(a.getAchievementDifficulty().getChatColor() + (pp.hasAchievement(a) ? "" + ChatColor.STRIKETHROUGH : "") + a.getDisplay());
            i.addFancyLore(a.getDescription(), ChatColor.WHITE.toString());
            i.addLore("");
        }

        return i;
    }
}
