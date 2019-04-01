package me.chasertw123.minigames.hub.features.shops;

import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Chase on 1/5/2018.
 */
public abstract class ShopCategory {

    private String name;
    private ItemStack itemStack;

    private Sound openSound = null;
    private float volume = 1.0F, pitch = 1.0F;

    public ShopCategory(String name, ItemStack itemStack) {
        this.name = name;
        this.itemStack = itemStack;
    }

    public ShopCategory(String name, ItemStack itemStack, Sound openSound) {
        this(name, itemStack);
        this.openSound = openSound;
    }

    public ShopCategory(String name, ItemStack itemStack, Sound openSound, float volume, float pitch) {
        this(name, itemStack, openSound);
        this.volume = volume;
        this.pitch = pitch;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void playOpenSound(Player player) {
        if (openSound != null)
            player.playSound(player.getLocation(), openSound, volume, pitch);
    }

    public abstract void buildShopCategory(Player player, AbstractGui abstractGui);
}
