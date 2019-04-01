package me.chasertw123.minigames.hub.features.guis.collectibles.gadgets;

import me.chasertw123.minigames.core.collectibles.Collectible;
import me.chasertw123.minigames.core.collectibles.CollectibleType;
import me.chasertw123.minigames.core.collectibles.gadgets.GadgetCollectible;
import me.chasertw123.minigames.core.user.data.stats.Stat;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.guis.collectibles.GUI_Collectibles;
import me.chasertw123.minigames.hub.user.User;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class GUI_Gadgets extends AbstractGui {

    private static final int[] BORDER_FILLER_SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] BOTTOM_FILLER_SLOTS = {45, 46, 47, 48, 50, 51, 52, 53};
    private static final int[] ITEM_SLOTS = {11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};

    public GUI_Gadgets(User user) {
        super(6, "Gadgets", user.getCoreUser());

        cItemStack item = new cItemStack(Material.BARRIER);

        item.setDisplayName(ChatColor.GREEN + "No Gadget");
        item.addFancyLore("Use this to disable your current gadget.", ChatColor.GRAY.toString());

        if (user.getActiveParticle() == null) {
            item.addLore("", ChatColor.YELLOW + "" + ChatColor.BOLD + "Selected!");
            item.addEnchant(Enchantment.LUCK, 1);
            item.addFlags(ItemFlag.HIDE_ENCHANTS);
        }

        else
            item.addLore("", ChatColor.YELLOW + "Click to select!");

        setItem(item, 10, (s, c, p) -> {

            if (user.getActiveGadget() == null)
                return;

            user.disableGadget();
            user.getCoreUser().getPlayer().closeInventory();
            user.getCoreUser().sendMessage(ChatColor.GREEN + "Disabled your current gadget!");
        });

        int slot = 0;
        for (Class<? extends Collectible> clazz : me.chasertw123.minigames.core.Main.getCollectibleManager().getCollectiblesByType(CollectibleType.GADGET)) {

            GadgetCollectible collectible;
            try {
                collectible = (GadgetCollectible) clazz.getConstructor(me.chasertw123.minigames.core.user.User.class).newInstance(user.getCoreUser());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            setItem(craftGadgetItem(collectible, user), ITEM_SLOTS[slot++], (s, c, p) -> {

                if (user.getActiveParticle() != null && user.getActiveParticle().getClass().getName().equals(clazz.getName()))
                    return;

                boolean owns = user.ownsCollectible(clazz);
                if (owns) {

                    user.getCoreUser().getPlayer().closeInventory();

                    if (collectible.needsDeluxe() && !user.getCoreUser().isDeluxe()) {
                        user.getPlayer().sendMessage(ChatColor.RED + "You need " + ChatColor.GOLD + "DELUXE" + ChatColor.RED + " to equip: " + collectible.getRarity().getColor() + collectible.getDisplay());
                        return;
                    }

                    user.activateGadget(collectible);
                    user.getPlayer().sendMessage(ChatColor.GREEN + "Activated gadget: " + collectible.getRarity().getColor() + collectible.getDisplay());
                }

                else {

                    int leftOver = user.getCoreUser().getStat(Stat.CRYSTALS) - collectible.getRarity().getPurchaseValue();
                    if (leftOver < 0) {
                        user.getCoreUser().sendMessage(ChatColor.RED + "You need " + Math.abs(leftOver) + " crystals to purchase: " + collectible.getRarity().getColor() + collectible.getDisplay());
                        user.getPlayer().playSound(user.getPlayer().getLocation(), Sound.VILLAGER_NO, 1F, 1F);
                        user.getPlayer().closeInventory();
                        return;
                    }

                    user.getPlayer().closeInventory();
                    user.unlockCollectible(collectible);
                    user.activateGadget(collectible);
                    user.getCoreUser().sendMessage(ChatColor.GREEN + "You unlocked and activated gadget: " + collectible.getRarity().getColor() + collectible.getDisplay());
                    user.getCoreUser().decrementStat(Stat.CRYSTALS, collectible.getRarity().getPurchaseValue());
                }
            });
        }

        Arrays.stream(BORDER_FILLER_SLOTS).forEach(i -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 4, " "), i, (s, c, p) -> {}));
        Arrays.stream(BOTTOM_FILLER_SLOTS).forEach(i -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 3, " "), i, (s, c, p) -> {}));
        setItem(new cItemStack(Material.REDSTONE_COMPARATOR, ChatColor.RED + "" + ChatColor.BOLD + "Back to Arcade Prizes"), 49, (s, c, p) -> new GUI_Collectibles(user));
    }

    private ItemStack craftGadgetItem(GadgetCollectible collectible, User user) {

        cItemStack item =  collectible.getItemStack();
        item.setDisplayName(collectible.getRarity().getColor() + collectible.getDisplay());

        StringBuilder sb = new StringBuilder(collectible.getDescription());

        int i = 0;
        while (i + 35 < sb.length() && (i = sb.lastIndexOf(" ", i + 35)) != -1)
            sb.replace(i, i + 1, "\n");

        String[] additionalLore = sb.toString().split("\n");

        for (String s : additionalLore)
            item.addLore(org.bukkit.ChatColor.GRAY + s);

        item.addLore("");

        if (collectible.needsDeluxe())
            item.addLore(ChatColor.GRAY + "Requires " + ChatColor.GOLD + "DELUXE" + ChatColor.GRAY + " to equip!", "");

        if (user.getActiveParticle() != null && user.getActiveParticle().getClass().getName().equals(collectible.getClass().getName())) {
            item.addLore(ChatColor.YELLOW + "" + ChatColor.BOLD + "Selected!");
            item.addEnchant(Enchantment.LUCK, 1);
        }

        else
            item.addLore(ChatColor.YELLOW + (user.ownsCollectible(collectible.getClass()) ? "Click to select!" : "Purchase with " + collectible.getRarity().getPurchaseValue() + " Crystals!"));

        item.addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        return item;
    }
}
