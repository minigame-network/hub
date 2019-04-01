package me.chasertw123.minigames.hub.features.guis;

import me.chasertw123.minigames.core.user.User;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by Chase on 9/8/2017.
 */
public class GUI_Confirmation extends AbstractGui {

    private static final int[] FILLER_SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 17, 18, 22, 26, 27, 31, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] YES_FILLER_SLOTS = {10, 11, 12, 19, 20, 21, 28, 29, 30};
    private static final int[] NO_FILLER_SLOTS = {14, 15, 16, 23, 24, 25, 32, 33, 34};

    public GUI_Confirmation(User user, String title, final ItemStack yesItemStack, final ItemStack noItemStack, AbstractAction yesAction, AbstractAction noAction) {
        super(5, title, user);
        Validate.notNull(yesItemStack);
        Validate.notNull(noItemStack);

        Arrays.stream(FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, " "), slot, (s, c, p) -> {}));
        Arrays.stream(YES_FILLER_SLOTS).forEach(slot -> setItem(yesItemStack, slot, yesAction));
        Arrays.stream(NO_FILLER_SLOTS).forEach(slot -> setItem(noItemStack, slot, noAction));
    }
}
