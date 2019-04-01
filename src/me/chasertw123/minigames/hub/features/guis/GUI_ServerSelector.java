package me.chasertw123.minigames.hub.features.guis;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.utils.gui.AbstractGui;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.guis.parkour.GUI_Parkour;
import me.chasertw123.minigames.shared.booster.GameBooster;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by Chase on 8/2/2017.
 */
public class GUI_ServerSelector extends AbstractGui {

    private static final int[] BORDER_FILLER_SLOTS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 38, 39, 40, 41, 42, 44, 45, 46, 47, 51, 52, 53};
    private static final int[] INNER_FILLER_SLOTS = {10, 11, 12, 13, 14, 15, 16, 20, 22, 24, 28, 29, 30, 31, 32, 33, 34, 37, 43};

    // Gamemode Displays
    private static final String WATER_WARS_DISPLAY = ChatColor.AQUA + "" + ChatColor.BOLD + "Water" + ChatColor.BLUE + "" + ChatColor.BOLD + "Wars";
    private static final String MCPARTY_DISPLAY = ChatColor.WHITE + "" + ChatColor.BOLD + "MC" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Party";
    private static final String EVOLUTION_DISPLAY = ChatColor.RED + "" + ChatColor.BOLD + "Evolution";
    private static final String SPLEGG_DISPLAY = ChatColor.GOLD + "" + ChatColor.BOLD + "Splegg";

    // Gamemode Tags
    private static final GameTypeTag[] WATER_WARS_TAGS = {GameTypeTag.SOLO, GameTypeTag.TEAM, GameTypeTag.PVP, GameTypeTag.FAST_PACED};
    private static final GameTypeTag[] MCPARTY_TAGS = {GameTypeTag.BOARDGAME, GameTypeTag.MICROGAMES, GameTypeTag.PVP, GameTypeTag.PVE};
    private static final GameTypeTag[] EVOLUTION_TAGS = {GameTypeTag.PVP, GameTypeTag.MORPH, GameTypeTag.FAST_PACED};
    private static final GameTypeTag[] SPLEGG_TAGS = {GameTypeTag.PVP, GameTypeTag.FAST_PACED, GameTypeTag.MICROGAMES};

    // Gamemode Descriptions
    private static final String WATER_WARS_DESC = "Mine resources and defend your island in the water! Eliminate all other players to win, and avoid the water at all costs!";
    private static final String MCPARTY_DESC = "Take turns rolling dice and battling friends and foes to collect the most stars. Player with most stars wins!";
    private static final String EVOLUTION_DESC = "Evolve and conquer! Battle other players through Minecraft monster evolution. Player to evolve the most wins!";
    private static final String SPLEGG_DESC = "Shoot eggs at each other. Don't fall. Sounds simple enough, right? Last man standing wins!";

    // Gamemode Additional Information
    private static final String[] WATER_WARS_ADDITIONAL = {"Solo mode available now!", "Team modes coming soon."};
    private static final String[] MCPARTY_ADDITIONAL = {"Still under heavy development!"};
    private static final String[] EVOLUTION_ADDITIONAL = {"Still under heavy development!"};
    private static final String[] SPLEGG_ADDITIONAL = {"Includes other sub-gamemodes."};

    /*
    00 01 02 03 04 05 06 07 08
    09 10 11 12 13 14 15 16 17
    18 19 20 21 22 23 24 25 26
    27 28 29 30 31 32 33 34 35
    36 37 38 39 40 41 42 43 44
    45 46 47 48 49 50 51 52 53
     */
    public GUI_ServerSelector(me.chasertw123.minigames.core.user.User pp) {
        super(6, "Arcade Games", pp);

        setItem(this.buildItemStack(ServerGameType.SPLEGG, new ItemStack(Material.EGG), SPLEGG_DISPLAY, SPLEGG_TAGS, SPLEGG_DESC, SPLEGG_ADDITIONAL),
                19, (s, c, p) -> this.onMinigameItemClick(ServerGameType.SPLEGG, c, pp));

        setItem(this.buildItemStack(ServerGameType.WATER_WARS, new ItemStack(Material.WATER_LILY), WATER_WARS_DISPLAY, WATER_WARS_TAGS, WATER_WARS_DESC, WATER_WARS_ADDITIONAL),
                21, (s, c, p) -> this.onMinigameItemClick(ServerGameType.WATER_WARS, c, pp));

        setItem(this.buildItemStack(ServerGameType.MCPARTY, new ItemStack(Material.CAKE), MCPARTY_DISPLAY, MCPARTY_TAGS, MCPARTY_DESC, MCPARTY_ADDITIONAL),
                23, (s, c, p) -> {
            pp.sendPrefixedMessage(ChatColor.RED + "Coming one day, until then have your party pants ready to go!");
            pp.getPlayer().closeInventory();
            pp.getPlayer().playSound(pp.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1F, 1F);
        });

        setItem(this.buildItemStack(ServerGameType.EVOLUTION, new ItemStack(Material.DRAGON_EGG), EVOLUTION_DISPLAY, EVOLUTION_TAGS, EVOLUTION_DESC, EVOLUTION_ADDITIONAL),
                25, (s, c, p) -> this.onMinigameItemClick(ServerGameType.EVOLUTION, c, pp));

        setItem(new cItemStack(Material.BED).setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Spawn").addLore(ChatColor.GRAY + "Wander to far into some uncharted",
                ChatColor.GRAY + "lands? Use the power of the ender", ChatColor.GRAY + "world to teleport back to spawn.", "", ChatColor.GREEN + "➤ Click to warp to spawn"),
                48, (s, c, p) -> pp.getPlayer().performCommand("warp main"));

        setItem(new cItemStack(Material.CHEST).setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Arcade Prize Boxs").addLore(ChatColor.GRAY + "Want to open some of those fancy",
                ChatColor.GRAY + "prize boxes you have? Use the power", ChatColor.GRAY + "of the ender world to teleport to", ChatColor.GRAY + "our box opening machines.", "",
                ChatColor.GREEN + "➤ Click to warp to prize box openers"), 49, (s,c,p) -> pp.sendPrefixedMessage(ChatColor.RED + "Soon..."));

        setItem(new cItemStack(Material.PRISMARINE_SHARD).setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Parkour").addLore(ChatColor.GRAY + "Want to challenge yourself? Try",
                ChatColor.GRAY + "and complete all the hub parkour", ChatColor.GRAY + "courses. Each gives it's own reward!", "", ChatColor.GREEN + "➤ Click to select a course",
                ChatColor.AQUA + "➤ Right-Click for a random course"), 50, (s, c, p) -> new GUI_Parkour(Main.getUserManager().get(pp.getUUID())));

        Arrays.stream(BORDER_FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 2, " "), slot, (s, c, p) -> {}));
        Arrays.stream(INNER_FILLER_SLOTS).forEach(slot -> setItem(new cItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 3, " "), slot, (s, c, p) -> {}));
    }

    private void onMinigameItemClick(ServerGameType serverGameType, ClickType clickType, me.chasertw123.minigames.core.user.User user) {
        user.getPlayer().closeInventory();

        if (clickType.isShiftClick()) {
            user.getPlayer().performCommand("warp " + serverGameType.toString().toLowerCase());
            return;
        }

        if (clickType.isRightClick()) {
            user.getPlayer().performCommand("shop " + serverGameType.toString().toLowerCase());
            return;
        }

        user.addToQueue(serverGameType.toString().toLowerCase().replace("_", "-"));
    }

    private cItemStack buildItemStack(ServerGameType serverGameType, ItemStack itemStack, String display, GameTypeTag[] gameTypeTags, String description, String... additionalInformation) {

        cItemStack item = new cItemStack(itemStack);
        item.setDisplayName(display);

        String tags = "";
        for (GameTypeTag gameTypeTag : gameTypeTags)
            tags = tags.concat(gameTypeTag.getDisplay() + ", ");

        item.addLore(ChatColor.DARK_GRAY + tags.substring(0, tags.length() - 2), "");

        StringBuilder sb = new StringBuilder(description);

        int i = 0;
        while (i + 28 < sb.length() && (i = sb.lastIndexOf(" ", i + 28)) != -1)
            sb.replace(i, i + 1, "\n");

        String[] additionalLore = sb.toString().split("\n");

        for (String s : additionalLore)
            item.addLore(ChatColor.GRAY + s);

        item.addLore(""); // Add blank line

        for (String additional : additionalInformation)
            item.addLore(ChatColor.GRAY + additional);

        if (additionalInformation.length > 0)
            item.addLore(""); // Add blank line

        int multiplier = CoreAPI.getBoosterManager().eventBoosterActivated() ? CoreAPI.getBoosterManager().getCurrentEventBooster().getMultiplier() : 1;

        GameBooster gameBooster = null;
        for (GameBooster gb : CoreAPI.getBoosterManager().getCurrentGameBoosters()) {
            if (gb.getGameMode().equalsIgnoreCase(serverGameType.toString())) {
                gameBooster = gb;
                multiplier += 2;
                break;
            }
        }

        item.addLore(ChatColor.GRAY + "Coins Multiplier: " + (multiplier > 1 ? ChatColor.GOLD : "") + multiplier + ".0x");

        if (gameBooster != null) {
            item.addLore(ChatColor.DARK_GRAY + "➠ Booster by " + ChatColor.AQUA + gameBooster.getActivatorName());
            item.addEnchant(Enchantment.LUCK, 1);
            item.addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        }

        else
            item.addLore(ChatColor.DARK_GRAY + "➠ No Booster Active :(");

        if (CoreAPI.getBoosterManager().eventBoosterActivated()) {

            int mult = CoreAPI.getBoosterManager().getCurrentEventBooster().getMultiplier();

            String fancyNumber;
            switch (mult) {
                case 2:
                    fancyNumber = "Double";
                    break;
                case 3:
                    fancyNumber = "Triple";
                    break;
                case 4:
                    fancyNumber = "Quadruple";
                    break;
                default:
                    fancyNumber = i + ".0x";
                    break;
            }

            item.addLore(ChatColor.DARK_GRAY + "➠ " + fancyNumber + " Coins Event");
        }

        item.addLore("", ChatColor.GREEN + "➤ Click to quick Queue", ChatColor.AQUA + "➤ Right-Click to quick shop", ChatColor.YELLOW + "➤ Shift-Click to warp");

        return item;
    }


    private enum GameTypeTag {

        PVP("PvP"), PVE("PvE"), STRATEGY("Strategy"), FAST_PACED("Fast Paced"), SOLO("Solo"), TEAM("Teams"), PARKOUR("Parkour"), MICROGAMES("Microgames"), BOARDGAME("Boardgame"), MORPH("Morphs");

        private String display;

        GameTypeTag(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return display;
        }
    }
}
