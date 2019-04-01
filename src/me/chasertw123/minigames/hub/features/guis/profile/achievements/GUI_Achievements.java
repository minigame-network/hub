package me.chasertw123.minigames.hub.features.guis.profile.achievements;

import me.chasertw123.minigames.core.api.CustomHead;
import me.chasertw123.minigames.core.features.messages.Messages;
import me.chasertw123.minigames.core.user.data.achievements.Achievement;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.guis.profile.GUI_Profile;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bukkit.ChatColor;
import org.bukkit.Material;

/**
 * Created by Chase on 7/20/2017.
 */
public class GUI_Achievements extends AbstractGui {

    private static final int FILLER_SLOTS[] = {0, 1, 2, 3, 5, 6, 7, 8, 36, 37, 38, 39, 41, 42, 43, 44};
    private me.chasertw123.minigames.core.user.User pp;

    public GUI_Achievements(User user) {
        super(5, "Your Achievements", user.getCoreUser());
        this.pp = user.getCoreUser();

        setItem(craftItemStack(new cItemStack(Material.BOOK), null, "on almost any and every server."), 20, (s, c, p) -> new GUI_General(user));
        setItem(craftItemStack(new cItemStack(Material.WATER_LILY), ServerGameType.WATER_WARS, "by playing water wars solo or with friends."), 23, (s, c, p) -> new GUI_WaterWars(user));
        setItem(craftItemStack(new cItemStack(Material.CAKE), ServerGameType.MCPARTY, "by playing mcparty."), 24, (s, c, p) -> {});
        setItem(craftItemStack(new cItemStack(Material.DRAGON_EGG), ServerGameType.EVOLUTION, "by playing evolution."), 25, (s, c, p) -> {});

        setItem(pp.toItemStack(), 4, (s, c, p) -> {});
        setItem(new cItemStack(Material.DIODE, ChatColor.GREEN + "Return to profile"), 40, (s, c, p) -> new GUI_Profile(user));

        for (int slot : FILLER_SLOTS)
            setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, " "), slot, (s, c, p) -> {});

        setItem(CustomHead.QUESTION_MARK.craftSkull().setDisplayName(ChatColor.YELLOW + "Confused about achievements?").addFancyLore("Click here to learn about what achievements are, the different " +
                "difficulties, and rewards you get from completing them.", ChatColor.GRAY.toString()), 44, (s, c, p) -> {

            Messages.ACHIEVEMENTS_HELP.send(pp);
            pp.getPlayer().closeInventory();
        });
    }

    private cItemStack craftItemStack(cItemStack item, ServerGameType serverGameType, String shortenDescription) {

        item.setDisplayName(ChatColor.GREEN + (serverGameType == null ? "General" : serverGameType.getDisplay()) + " Achievements");
        item.addFancyLore("Achievements that can be unlocked " + shortenDescription, ChatColor.GRAY.toString());

        int total = 0, unlocked = 0;
        for (Achievement achievement : Achievement.values())
            if (achievement.getServerGameType() == serverGameType) {
                total++;

                if (pp.hasAchievement(achievement))
                    unlocked++;
            }

        item.addLore("", ChatColor.GRAY + "Unlocked: " + ChatColor.RED + unlocked + "/" + total + ChatColor.DARK_GRAY + " (" + (int) ((unlocked * 100.0f) / total) + "%)", "",
                ChatColor.YELLOW + "Click to view " + (serverGameType == null ? "general" : serverGameType.getDisplay().toLowerCase()) + " achievements!");

        return item;
    }
}
