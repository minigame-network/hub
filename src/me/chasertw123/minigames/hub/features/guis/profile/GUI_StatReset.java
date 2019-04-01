package me.chasertw123.minigames.hub.features.guis.profile;

import me.chasertw123.minigames.core.features.messages.Messages;
import me.chasertw123.minigames.core.user.User;
import me.chasertw123.minigames.core.user.data.stats.Stat;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.guis.GUI_Confirmation;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.Arrays;

/**
 * Created by Chase on 9/8/2017.
 */
public class GUI_StatReset extends AbstractGui {

    private static final int FILLER_SLOTS[] = {0, 1, 2, 3, 5, 6, 7, 8, 45, 46, 47, 48, 50, 51, 52, 53};

    private User pp;

    public GUI_StatReset(User pp) {
        super(5, "Stat Reset", pp);
        this.pp = pp;

        setItem(pp.toItemStack(), 4, (s, c, p) -> {});

        // TODO: Put all gamemode stats in menu

        setItem(new cItemStack(Material.DIODE, ChatColor.GREEN + "Close menu"), 49, (s, c, p) -> pp.getPlayer().closeInventory());
        Arrays.stream(FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, " "), slot, (s, c, p) -> {}));
        // TODO: Recolor GUI. Make more appealing to the eye
    }

    private void sendConfirmationGui(ServerGameType serverGameType) {

        if (pp.getStat(Stat.STAT_RESET_TOKEN) < 1) {
            pp.getPlayer().closeInventory();
            Messages.BUY_STAT_RESET_TOKENS.send(pp);
            return;
        }

        AbstractAction noAction = (s, c, p) -> new GUI_StatReset(pp), yesAction = (s, c, p) -> {
            Arrays.stream(Stat.values()).filter(stat -> stat.getServerGameType() == serverGameType && stat.canReset()). forEach(stat -> pp.setStat(stat, stat.getDefaultValue()));
            pp.sendPrefixedMessage(ChatColor.GREEN + "Your " + serverGameType.getDisplay() + " stats have been successfully reset!");
            pp.getPlayer().playSound(pp.getPlayer().getLocation(), Sound.GHAST_DEATH, 1F, 1F);
        };

        cItemStack yesItemStack = new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5, "CONFIRM").addFancyLore("Clicking this I understand I do indeed want to reset my "
                + serverGameType.getDisplay() + " stats. I also understand that this action CANNOT be undone.", ChatColor.BLUE.toString());

        cItemStack noItemStack = new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14, "CANCEL").addFancyLore("Click this to cancel resetting your " + serverGameType.getDisplay()
                + " stats! Better to sleep on it anyway.", ChatColor.BLUE.toString());

        new GUI_Confirmation(pp, "Reset your " + serverGameType.getDisplay() + " stats?", yesItemStack, noItemStack, yesAction, noAction);
    }
}
