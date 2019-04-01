package me.chasertw123.minigames.hub.features.guis.mysteryman;

import me.chasertw123.minigames.core.api.CustomHead;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.user.User;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;

public class GUI_MysteryMan extends AbstractGui {

    private static final int[] BORDER_FILLER_SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] INNER_FILLER_SLOTS = {10, 11, 12, 13, 14, 15, 16, 19, 21, 22, 23, 25, 28, 29, 30, 31, 32, 33, 34};

    public GUI_MysteryMan(User user) {
        super(5, "What are you looking for?", user.getCoreUser());

        setItem(new cItemStack(Material.EMPTY_MAP, ChatColor.LIGHT_PURPLE + "Quests for the Void"), 20, (s, c, p) -> {});
        setItem(CustomHead.VOID_ORB.craftSkull().setDisplayName(ChatColor.LIGHT_PURPLE + "Gifts of the Void"), 24, (s, c, p) -> {});

        Arrays.stream(BORDER_FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15, " "), slot, (s, c, p) -> {}));
        Arrays.stream(INNER_FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 10, " "), slot, (s, c, p) -> {}));
    }

}
