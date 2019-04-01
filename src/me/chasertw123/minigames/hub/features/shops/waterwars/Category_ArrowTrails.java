package me.chasertw123.minigames.hub.features.shops.waterwars;

import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.shops.ShopCategory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created by Chase on 1/13/2018.
 */
public class Category_ArrowTrails extends ShopCategory {

    public Category_ArrowTrails() {
        super("Arrow Trails", new cItemStack(Material.ARROW), Sound.SHOOT_ARROW);
    }

    @Override
    public void buildShopCategory(Player player, AbstractGui abstractGui) {

    }
}
