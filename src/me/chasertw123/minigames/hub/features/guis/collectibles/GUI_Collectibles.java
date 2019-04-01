package me.chasertw123.minigames.hub.features.guis.collectibles;

import me.chasertw123.minigames.core.collectibles.Collectible;
import me.chasertw123.minigames.core.collectibles.CollectibleType;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.features.guis.collectibles.banners.GUI_Banners;
import me.chasertw123.minigames.hub.features.guis.collectibles.gadgets.GUI_Gadgets;
import me.chasertw123.minigames.hub.features.guis.collectibles.joinmessages.GUI_JoinMessages;
import me.chasertw123.minigames.hub.features.guis.collectibles.joinmessages.LoginMessage;
import me.chasertw123.minigames.hub.features.guis.collectibles.morphs.GUI_Morphs;
import me.chasertw123.minigames.hub.features.guis.collectibles.particles.GUI_Particles;
import me.chasertw123.minigames.hub.features.guis.collectibles.pets.GUI_Pets;
import me.chasertw123.minigames.hub.user.User;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;

/**
 * Created by Chase on 1/14/2018.
 */
public class GUI_Collectibles extends AbstractGui {

    private static final int[] BORDER_FILLER_SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
    private static final int[] INNER_FILLER_SLOTS = {10, 11, 12, 13, 14, 15, 16, 19, 25, 28, 29, 33, 34, 37, 38, 39, 40, 41, 42, 43};

    // Cosmetic Item Displays
    private static final String JOIN_MESSAGES_DISPLAY = ChatColor.GOLD + "" + ChatColor.BOLD  + "Hub Join Messages";
    private static final String MORPHS_DISPLAY = ChatColor.RED + "" + ChatColor.BOLD + "Morphs";
    private static final String PETS_DISPLAY = ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Pets";
    private static final String PARTICLES_DISPLAY = ChatColor.AQUA + "" + ChatColor.BOLD + "Particles";
    private static final String HATS_DISPLAY = ChatColor.YELLOW + "" + ChatColor.BOLD + "Hats";
    private static final String GADGETS_DISPLAY = ChatColor.WHITE + "" + ChatColor.BOLD + "Gadgets";
    private static final String COSTUMES_DISPLAY = ChatColor.BLUE + "" + ChatColor.BOLD + "Costumes";
    private static final String BANNERS_DISPLAY = ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Banners";

    // Cosmetic Item Descriptions
    private static final String JOIN_MESSAGES_DESCRIPTION = "Change up the announcement in chat when you join the server! (WIP)";
    private static final String MORPHS_DESCRIPTION = "Something about turning into some kinda of cool monster. (WIP)";
    private static final String PETS_DESCRIPTION = "Something about having a cute or big pet follow you around, and you can interact and ride it. (WIP)";
    private static final String PARTICLES_DESCRIPTION = "Something about having cool effects around you. (WIP)";
    private static final String HATS_DESCRIPTION = "Something about cool hats and being able to wear them. (WIP)";
    private static final String GADGETS_DESCRIPTION = "Something about being able to wield something that does some cool stuffs. (WIP)";
    private static final String COSTUMES_DESCRIPTION = "Something about being able to equip and costume and run around in it.(WIP)";
    private static final String BANNERS_DESCRIPTION = "Something about flags, and other cool banner stuff. (WIP)";

    // Cosmetic Item Additional Information
    private static final String[] JOIN_MESSAGE_ADDITIONAL = {ChatColor.GRAY + "Only available to " + ChatColor.GOLD + "DELUXE " + ChatColor.GRAY + " players!"};

    public GUI_Collectibles(User user) {
        super(6, "Arcade Prizes", user.getCoreUser());

        setItem(buildItemStack(new ItemStack(Material.EXPLOSIVE_MINECART), GADGETS_DISPLAY, GADGETS_DESCRIPTION, getCollectibleTypeAmount(user, CollectibleType.GADGET),
                me.chasertw123.minigames.core.Main.getCollectibleManager().getCollectibleTypeAmount(CollectibleType.GADGET)), 20, (s, c, p) -> new GUI_Gadgets(user));

        setItem(buildItemStack(new ItemStack(Material.GOLD_HELMET), HATS_DISPLAY, HATS_DESCRIPTION, getCollectibleTypeAmount(user, CollectibleType.HAT),
                me.chasertw123.minigames.core.Main.getCollectibleManager().getCollectibleTypeAmount(CollectibleType.HAT)), 21, (s, c, p) -> {});

        ItemStack costumeItem = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta im = (LeatherArmorMeta) costumeItem.getItemMeta();

        im.setColor(Color.fromRGB(255, 77, 166));
        costumeItem.setItemMeta(im);

        setItem(buildItemStack(new cItemStack(costumeItem), COSTUMES_DISPLAY, COSTUMES_DESCRIPTION, getCollectibleTypeAmount(user, CollectibleType.COSTUME),
                me.chasertw123.minigames.core.Main.getCollectibleManager().getCollectibleTypeAmount(CollectibleType.COSTUME)), 22, (s, c, p) -> {});

        setItem(buildItemStack(new ItemStack(Material.SKULL_ITEM, 1, (short) 4), MORPHS_DISPLAY, MORPHS_DESCRIPTION, getCollectibleTypeAmount(user, CollectibleType.MORPH),
                me.chasertw123.minigames.core.Main.getCollectibleManager().getCollectibleTypeAmount(CollectibleType.COSTUME)), 23, (s, c, p) -> new GUI_Morphs(user));

        setItem(buildItemStack(new ItemStack(Material.BANNER, 1, (short) 1), BANNERS_DISPLAY, BANNERS_DESCRIPTION, getCollectibleTypeAmount(user, CollectibleType.BANNER),
                me.chasertw123.minigames.core.Main.getCollectibleManager().getCollectibleTypeAmount(CollectibleType.BANNER)), 24, (s, c, p) -> new GUI_Banners(user));

        setItem(buildItemStack(new ItemStack(Material.PRISMARINE_CRYSTALS), PARTICLES_DISPLAY, PARTICLES_DESCRIPTION, getCollectibleTypeAmount(user, CollectibleType.PARTICLE),
                me.chasertw123.minigames.core.Main.getCollectibleManager().getCollectibleTypeAmount(CollectibleType.PARTICLE)), 30, (s, c, p) -> new GUI_Particles(user));

        setItem(buildItemStack(new ItemStack(Material.LEASH), PETS_DISPLAY, PETS_DESCRIPTION, getCollectibleTypeAmount(user, CollectibleType.PET),
                me.chasertw123.minigames.core.Main.getCollectibleManager().getCollectibleTypeAmount(CollectibleType.PET)), 31, (s, c, p) -> new GUI_Pets(user));

        setItem(buildItemStack(new ItemStack(Material.BOOK_AND_QUILL), JOIN_MESSAGES_DISPLAY, JOIN_MESSAGES_DESCRIPTION, user.getCoreUser().isDeluxe() ? LoginMessage.values().length : 0,
                LoginMessage.values().length, JOIN_MESSAGE_ADDITIONAL), 32, (s, c, p) -> new GUI_JoinMessages(user));

        Arrays.stream(BORDER_FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 4, " "), slot, (s, c, p) -> {}));
        Arrays.stream(INNER_FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 3, " "), slot, (s, c, p) -> {}));
    }

    private static ItemStack buildItemStack(ItemStack itemStack, String display, String description, int unlocked, int totalUnlocks, String... additionalInfo) {

        cItemStack item = new cItemStack(itemStack);
        item.setDisplayName(display);

        StringBuilder sb = new StringBuilder(description);

        int i = 0;
        while (i + 35 < sb.length() && (i = sb.lastIndexOf(" ", i + 35)) != -1)
            sb.replace(i, i + 1, "\n");

        String[] additionalLore = sb.toString().split("\n");

        for (String s : additionalLore)
            item.addLore(org.bukkit.ChatColor.GRAY + s);

        item.addLore(""); // Add blank line

        if (additionalInfo.length > 0) {
            for (String s : additionalInfo)
                item.addLore(s);
            item.addLore("");
        }

        item.addLore(ChatColor.GRAY + ChatColor.stripColor(display) + " Unlocked: " + ChatColor.RED + unlocked + "/" + totalUnlocks + ChatColor.DARK_GRAY + " (" + (int) ((unlocked * 100.0f) / totalUnlocks) + "%)");
        item.addLore("", ChatColor.BLUE + "Click to view " + ChatColor.stripColor(display) + "!");

        item.addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        return item;
    }

    private int getCollectibleTypeAmount(User user, CollectibleType type) {

        int amount = 0;
        for (Collectible collectible : user.getUnlockedCollectibles())
            if (collectible.getType() == type)
                amount++;

        return amount;
    }

}
