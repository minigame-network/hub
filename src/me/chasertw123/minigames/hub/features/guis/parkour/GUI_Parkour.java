package me.chasertw123.minigames.hub.features.guis.parkour;

import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.guis.GUI_ServerSelector;
import me.chasertw123.minigames.hub.features.parkour.UserCourseCompletionData;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.parkour.Course;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;

import java.util.Arrays;

public class GUI_Parkour extends AbstractGui {

    private static final int[] BORDER_FILLER_SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 38, 39, 40, 41, 42, 44, 45, 46, 47, 48, 50, 51, 52, 53};
    private static final int[] INNER_AREAS = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 18, 29, 30, 31, 32, 33, 34, 37, 43};

    /*
    00 01 02 03 04 05 06 07 08
    09 10 11 12 13 14 15 16 17
    18 19 20 21 22 23 24 25 26
    27 28 29 30 31 32 33 34 35
    36 37 38 39 40 41 42 43 44
    45 46 47 48 49 50 51 52 53
     */
    private User pp;

    public GUI_Parkour(User pp) {
        super(6, "Parkour", pp.getCoreUser());

        this.pp = pp;

        int i = 0;
        for(Course course : Main.getCourseManager().getActiveCourses()) {
            setItem(createParkourItem(course), INNER_AREAS[i++], (s, c, p) -> {
                UserCourseCompletionData data = pp.getCourseData(course.getCourseId());

                if(!data.hasFinished() || c == ClickType.LEFT)
                    pp.getPlayer().teleport(course.getStart());
                else if (c == ClickType.RIGHT)
                    pp.getPlayer().teleport(course.getFinish());
            });
        }

        Arrays.stream(BORDER_FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 2, " "), slot, (s, c, p) -> {}));

        setItem(new cItemStack(Material.DIODE, ChatColor.RED + "Back"), 49, (s, c, p) -> new GUI_ServerSelector(pp.getCoreUser()));
    }

    private cItemStack createParkourItem(Course course) {
        UserCourseCompletionData data = pp.getCourseData(course.getCourseId());

        cItemStack itemStack = new cItemStack(Material.PAPER, ChatColor.AQUA + "" + ChatColor.BOLD + course.getName())
                .addLore(ChatColor.DARK_GRAY + course.getDifficulty().toString(), " ");

        Arrays.stream(course.getDescription().split("!n!")).forEach(i -> itemStack.addLore(ChatColor.GRAY + i));

        itemStack.addLore(" ");

        if(data.hasFinished()) {
            itemStack.addLore(ChatColor.GOLD + "Completed!");
            itemStack.addEnchant(Enchantment.LUCK, 1);
            itemStack.addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        } else if (data.hasStarted()) {
            itemStack.addLore(ChatColor.GRAY + "Record: " + ChatColor.GOLD + data.getPercentageCompleted() + ChatColor.GRAY + "%");
            itemStack.addLore(ChatColor.GRAY + "Checkpoint " + ChatColor.GOLD + data.getCurrentCheckpoint() + ChatColor.GRAY + "/"
                    + ChatColor.GOLD + course.getTotalCheckPoints());
        } else {
            itemStack.addLore(ChatColor.RED + "Never played.");
        }

        itemStack.addLore(" ", ChatColor.GREEN + "➤ " + (data.hasFinished() ? "Left-" : "") +"Click to TP to Start");
        if(data.hasFinished())
            itemStack.addLore(ChatColor.AQUA + "➤ Right-Click to TP to Finish");

        return itemStack;
    }

}
