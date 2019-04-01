package me.chasertw123.minigames.hub.listeners;

import me.chasertw123.minigames.core.api.misc.Title;
import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.user.data.settings.Setting;
import me.chasertw123.minigames.core.user.data.stats.Stat;
import me.chasertw123.minigames.core.utils.items.AbstractItem;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.bossbar.Loop_AnimatedBossBar;
import me.chasertw123.minigames.hub.features.guis.collectibles.GUI_Collectibles;
import me.chasertw123.minigames.hub.features.scoreboards.SB_Hub;
import me.chasertw123.minigames.hub.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Chase on 1/6/2018.
 */
public class Event_PlayerJoin implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        User user = new User(event.getPlayer().getUniqueId());
        me.chasertw123.minigames.core.user.User pp = user.getCoreUser();

        Main.getUserManager().add(user);

        if (Loop_AnimatedBossBar.getInstance().getBossBar() != null && pp.getSetting(Setting.BOSS_BAR))
            Loop_AnimatedBossBar.getInstance().getBossBar().addPlayer(pp.getUUID());

        pp.getPlayer().getInventory().clear();
        pp.getPlayer().getEquipment().clear();

        for (PotionEffect potionEffect : pp.getPlayer().getActivePotionEffects())
            pp.getPlayer().removePotionEffect(potionEffect.getType());

        if (!pp.isDeluxe())
            pp.setSetting(Setting.SCOREBOARD, true);

        if (pp.getSetting(Setting.SCOREBOARD))
            pp.setScoreboard(new SB_Hub(user));

        pp.getPlayer().getInventory().setHeldItemSlot(0);
        pp.getPlayer().setAllowFlight(pp.getSetting(Setting.AUTO_FLY) && pp.isDeluxe());
        pp.getPlayer().setGameMode(CoreAPI.getServerDataManager().getDefaultGamemode());
        pp.getPlayer().teleport(Main.getSpawnpointManager().getHubSpawnpoint());
        pp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
        pp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));

        float xp = ((float) pp.getStat(Stat.EXPERIENCE) / pp.getLevelExperience());
        pp.getPlayer().setExp(xp >= 1 ? 0.9999999F : xp);
        pp.getPlayer().setLevel(pp.getLevel());

        net.md_5.bungee.api.ChatColor nameColor = pp.getRank().getRankColor();
        if (!pp.getRank().isStaff() && pp.isDeluxe())
            nameColor = net.md_5.bungee.api.ChatColor.GOLD;

        pp.getPlayer().setPlayerListName(nameColor + pp.getUsername());
        event.setJoinMessage(pp.getRank().isStaff() || pp.isDeluxe() ? user.getSelectedLoginMessage().createMessage(pp) : null);
        Title.sendTablist(pp.getPlayer(), ChatColor.GOLD + "" + ChatColor.BOLD + "PVPCentral", ChatColor.WHITE + "store.pvpcentral.net");

        new AbstractItem(new cItemStack(Material.CHEST, ChatColor.GREEN + "Arcade Prizes").addFancyLore("Feeling lucky? Why don't you" +
                " open some treasure chests!", ChatColor.WHITE.toString()), pp, 4, (type) -> new GUI_Collectibles(user));

        new AbstractItem(new cItemStack(pp.getPlayer().getName(), ChatColor.GREEN + "My Profile").addFancyLore("View all of your statistics," +
                "friends, achievements, and more!", ChatColor.WHITE.toString()), pp, 1, (type) -> pp.getPlayer().performCommand("profile"));

        new AbstractItem(new cItemStack(Material.COMPASS, ChatColor.GREEN + "Arcade Games").addFancyLore("Quickly navigate to all of your favourite" +
                " games!", ChatColor.WHITE.toString()), pp, 0, (type) -> pp.getPlayer().performCommand("ss"));

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), user::updatePlayerVisibility, 3L);
        CoreAPI.getOnlinePlayers().stream().filter(paradisePlayer -> !paradisePlayer.getUUID().equals(pp.getUUID()))
                .filter(paradisePlayer -> paradisePlayer.getSetting(Setting.PLAYER_VISIBILITY)).forEach(paradisePlayer -> paradisePlayer.getPlayer().hidePlayer(pp.getPlayer()));

        if (pp.hasLeveled())
            Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                pp.setLeveled(false);
                pp.getPlayer().playSound(pp.getPlayer().getPlayer().getLocation(), Sound.LEVEL_UP, 1.25F, 0.5F);
                pp.sendPrefixedMessage(ChatColor.YELLOW + "You leveled up! You're now level " + ChatColor.LIGHT_PURPLE + pp.getLevel() + ChatColor.YELLOW + "!");
            }, 10);
    }

}
