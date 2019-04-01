package me.chasertw123.minigames.hub.features.guis.collectibles.joinmessages;

import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.guis.collectibles.GUI_Collectibles;
import me.chasertw123.minigames.hub.user.User;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;

/**
 * Created by Chase on 1/16/2018.
 */
public class GUI_JoinMessages extends AbstractGui {

    private static final int[] BORDER_FILLER_SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] BOTTOM_FILLER_SLOTS = {45, 46, 47, 48, 50, 51, 52, 53};
    private static final int[] ITEM_SLOTS = {11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};

    public GUI_JoinMessages(User user) {
        super(6, "Hub Login Messages", user.getCoreUser());

        // TODO: Add option to select no login message (will be in item slot 10)

        int slot = 0;
        for (LoginMessage loginMessage : LoginMessage.values())
            setItem(loginMessage.createItemStack(user.getCoreUser()), ITEM_SLOTS[slot++], (s, c, p) -> {

                // TODO: Make it so player's can select login message

            });

        Arrays.stream(BORDER_FILLER_SLOTS).forEach(i -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 4, " "), i, (s, c, p) -> {}));
        Arrays.stream(BOTTOM_FILLER_SLOTS).forEach(i -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 3, " "), i, (s, c, p) -> {}));
        setItem(new cItemStack(Material.REDSTONE_COMPARATOR, ChatColor.RED + "" + ChatColor.BOLD + "Back to Arcade Prizes"), 49, (s, c, p) -> new GUI_Collectibles(user));
    }

}
