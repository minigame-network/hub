package me.chasertw123.minigames.hub.features.shops.waterwars;

import me.chasertw123.minigames.core.user.data.stats.Stat;
import me.chasertw123.minigames.core.user.data.stats.StatClump;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.guis.GUI_Confirmation;
import me.chasertw123.minigames.hub.features.shops.Shop;
import me.chasertw123.minigames.hub.features.shops.ShopCategory;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.wws.unlocks.cages.Cage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Chase on 1/6/2018.
 */
public class Category_Cages extends ShopCategory {

    public Category_Cages() {
        super("Cages", new cItemStack(Material.GLASS), Sound.GLASS);
    }

    @Override
    public void buildShopCategory(Player player, AbstractGui abstractGui) {

        User user = Main.getUserManager().get(player.getUniqueId());

        List<Cage> cages = new ArrayList<>();
        for (Cage cage : Cage.values())
            if (cage.getRarity() != null)
                cages.add(cage);

        cages.sort(Comparator.comparing(Cage::getRarity));

        int slot = 18;
        for (Cage cage : Cage.values())
            if (cage.getRarity() == null)
                abstractGui.setItem(user.getWaterWarsData().getSelectedCage() == cage ? new cItemStack(cage.getShopItemStack(user.getWaterWarsData(),
                        this.getUserStatClump(user, cage.getStatClump()))).addEnchant(Enchantment.LUCK, 1).addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS)
                        : cage.getShopItemStack(user.getWaterWarsData(), this.getUserStatClump(user, cage.getStatClump())), slot++, (s, c, p) -> onClick(cage, user));

        for (Cage cage : cages)
            abstractGui.setItem(user.getWaterWarsData().getSelectedCage() == cage ? new cItemStack(cage.getShopItemStack(user.getWaterWarsData(), this.getUserStatClump(user, cage.getStatClump())))
                    .addEnchant(Enchantment.LUCK, 1).addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS) : cage.getShopItemStack(user.getWaterWarsData(), this.getUserStatClump(user, cage.getStatClump())),
                    slot++, (s, c, p) -> onClick(cage, user));
    }

    private void onClick(Cage cage, User user) {

        if (user.getWaterWarsData().ownsCage(cage)) {

            if (user.getWaterWarsData().getSelectedCage() == cage) {
                user.getPlayer().playSound(user.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 2F, 1F);
                return;
            }

            user.getWaterWarsData().setSelectedCage(cage);
            user.getPlayer().playSound(user.getPlayer().getLocation(), Sound.VILLAGER_YES, 2F, 1F);
            Shop.WATER_WARS.openShop(user.getPlayer(), 1);
            return;
        }

        if (cage.getStatClump() != null) {

            int userStatTotal = 0;
            for (Stat stat : cage.getStatClump().getStats())
                userStatTotal += user.getCoreUser().getStat(stat);

            if (userStatTotal >= cage.getStatAmount()) {
                user.getWaterWarsData().setSelectedCage(cage);
                user.getPlayer().playSound(user.getPlayer().getLocation(), Sound.LEVEL_UP, 2F, 1F);
                Shop.WATER_WARS.openShop(user.getPlayer(), 1);
                return;
            }

            user.getPlayer().playSound(user.getPlayer().getLocation(), Sound.VILLAGER_NO, 2F, 1F);
            return;
        }

        else {

            boolean hasNeedCoins = user.getCoreUser().getStat(Stat.COINS) >= cage.getRarity().getCost();
            if (!hasNeedCoins) {
                user.getPlayer().playSound(user.getPlayer().getLocation(), Sound.VILLAGER_NO, 2F, 1F);
                return;
            }

            new GUI_Confirmation(user.getCoreUser(), "Purchase: " + cage.getDisplay() + " Cage",
                    new cItemStack(Material.STAINED_CLAY, 1, (short) 5, ChatColor.GREEN + "Purchase " + cage.getDisplay() + " Cage."),
                    new cItemStack(Material.STAINED_CLAY, 1, (short) 14, ChatColor.RED + "Cancel " + cage.getDisplay() + " Cage Purchase."),
                    (s, c, p) -> {

                user.getCoreUser().decrementStat(Stat.COINS, cage.getRarity().getCost());
                user.getWaterWarsData().setSelectedCage(cage);
                user.getPlayer().playSound(user.getPlayer().getLocation(), Sound.LEVEL_UP, 2F, 1F);
                Shop.WATER_WARS.openShop(user.getPlayer(), 1);

            }, (s, c, p) -> Shop.WATER_WARS.openShop(user.getPlayer(), 1));
        }
    }

    private int getUserStatClump(User user, StatClump statClump) {

        int total = 0;
        if (statClump != null)
            for (Stat stat : statClump.getStats())
                total += user.getCoreUser().getStat(stat);

        return total;
    }
}
