package me.chasertw123.minigames.hub.features.shops;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.shops.waterwars.Category_ArrowTrails;
import me.chasertw123.minigames.hub.features.shops.waterwars.Category_Cages;
import me.chasertw123.minigames.hub.features.shops.waterwars.Category_Kits;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

/**
 * Created by Chase on 1/5/2018.
 */
public enum Shop {

    WATER_WARS(ServerGameType.WATER_WARS, new Category_Kits(), new Category_Cages(), new Category_ArrowTrails());

    private static final int[] FILLER_SLOTS = {9, 10, 11, 12, 13, 14, 15, 16, 17};
    private static final ChatColor[] CATEGORY_COLORS = {ChatColor.YELLOW, ChatColor.AQUA, ChatColor.LIGHT_PURPLE, ChatColor.GREEN, ChatColor.BLUE, ChatColor.GOLD, ChatColor.DARK_AQUA};

    private final ServerGameType serverGameType;
    private final ShopCategory[] shopCategories;

    Shop(ServerGameType serverGameType, ShopCategory... shopCategories) {
        this.serverGameType = serverGameType;
        this.shopCategories = shopCategories;
    }

    public void openShop(Player player, int category) {

        if (category > shopCategories.length)
            category = 1;

        ShopCategory shopCategory = shopCategories[category];
        AbstractGui abstractGui = new AbstractGui(6, serverGameType.getDisplay() + " Shop [" + shopCategory.getName() + "]") {};

        shopCategory.buildShopCategory(player, abstractGui);

        int categorySlot = 0;
        for (ShopCategory sc : shopCategories) {

            cItemStack categoryItem = new cItemStack(sc.getItemStack());
            if (sc.getName().equals(shopCategory.getName()))
                categoryItem.addEnchant(Enchantment.LUCK, 1);

            categoryItem.setDisplayName(CATEGORY_COLORS[categorySlot] + "" + ChatColor.BOLD + sc.getName());
            categoryItem.addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS);

            final int finalSlot = categorySlot;

            if (shopCategory.getName().equals(sc.getName()))
                abstractGui.setItem(categoryItem, categorySlot++, (s, c, p) -> {});

            else
                abstractGui.setItem(categoryItem, categorySlot++, (s, c, p) -> openShop(player, finalSlot));
        }

        for (int slot : FILLER_SLOTS)
            abstractGui.setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15, " "), slot, (s, c, p) -> {});

        abstractGui.setItem(new cItemStack(Material.DIODE, ChatColor.RED + "" + ChatColor.BOLD + "Close Shop"), 8, (s, c, p) -> p.closeInventory());

        shopCategory.playOpenSound(player);
        abstractGui.openInventory(CoreAPI.getUser(player));
    }
}
