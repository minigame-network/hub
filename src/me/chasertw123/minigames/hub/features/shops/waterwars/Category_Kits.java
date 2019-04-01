package me.chasertw123.minigames.hub.features.shops.waterwars;

import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.shops.ShopCategory;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.wws.unlocks.kits.Kit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Chase on 1/5/2018.
 */
public class Category_Kits extends ShopCategory {

    public Category_Kits() {
        super("Kits", new ItemStack(Material.ARMOR_STAND), Sound.HORSE_ARMOR);
    }

    @Override
    public void buildShopCategory(Player player, AbstractGui abstractGui) {

        User user = Main.getUserManager().get(player.getUniqueId());

        int slot = 18;
        for (Kit kit : Kit.values())
            abstractGui.setItem(kit == user.getWaterWarsData().getSelectedKit() ? kit.craftItemStack(user.getWaterWarsData()).addEnchant(Enchantment.LUCK, 1)
                    .addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS) : kit.craftItemStack(user.getWaterWarsData()).addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS) , slot++, (s, c, p) -> {});
    }
}