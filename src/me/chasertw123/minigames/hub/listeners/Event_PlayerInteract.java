package me.chasertw123.minigames.hub.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Chase on 9/2/2017.
 */
public class Event_PlayerInteract implements Listener {

    private static Material[] NO_INTERACT = new Material[] {Material.FURNACE, Material.CHEST, Material.HOPPER, Material.DISPENSER, Material.DROPPER, Material.BURNING_FURNACE, Material.BREWING_STAND,
            Material.ENCHANTMENT_TABLE, Material.ENDER_CHEST, Material.ANVIL, Material.TRAPPED_CHEST, Material.WORKBENCH, Material.JUKEBOX, Material.NOTE_BLOCK, Material.TRAP_DOOR, Material.PAINTING,
            Material.ITEM_FRAME, Material.FENCE_GATE, Material.SPRUCE_FENCE_GATE, Material.ACACIA_FENCE_GATE, Material.BIRCH_FENCE_GATE, Material.DARK_OAK_FENCE_GATE, Material.JUNGLE_FENCE_GATE,
            Material.LEVER, Material.BED_BLOCK, Material.BED, Material.ACACIA_DOOR, Material.BIRCH_DOOR, Material.DARK_OAK_DOOR, Material.JUNGLE_DOOR, Material.SPRUCE_DOOR, Material.WOODEN_DOOR,
            Material.WOOD_DOOR, Material.IRON_DOOR};

    private HashMap<UUID, Long> lastInteractPlate = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.SOIL && e.getPlayer().getGameMode() != GameMode.CREATIVE)
            e.setCancelled(true);

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            for (Material mat : NO_INTERACT)
                if (e.getClickedBlock().getType() == mat)
                    e.setCancelled(true);
//
//            me.chasertw123.minigames.core.user.User paradisePlayer = CoreAPI.getAPI().getUser(e.getPlayer());
//            if (e.getClickedBlock().getType() == Material.CHEST && paradisePlayer.getRank() == Rank.DEV) // TODO: REMOVE DEBUG CODE
//                for (Chest chest : Chest.chests)
//                    if (chest.getChestSpawnpoint().equals(e.getClickedBlock().getLocation())) {
//                        chest.openChest(paradisePlayer, ChestRarity.MAGICAL);
//                        return;
//                    }
        }

        if (e.getAction() == Action.PHYSICAL && (e.getClickedBlock().getType() == Material.IRON_PLATE || e.getClickedBlock().getType() == Material.GOLD_PLATE)
                && (!lastInteractPlate.containsKey(e.getPlayer().getUniqueId()) || lastInteractPlate.get(e.getPlayer().getUniqueId()) + 500 < System.currentTimeMillis())) {

            e.setCancelled(true);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.5F);

            if (e.getClickedBlock().getType() == Material.IRON_PLATE)
                e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(4.85));

            else if (e.getClickedBlock().getType() == Material.GOLD_PLATE)
                e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(8.5));

            e.getPlayer().teleport(e.getPlayer().getLocation().clone().add(0, 0.2, 0));
            e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), 1.0D, e.getPlayer().getVelocity().getZ()));
            lastInteractPlate.put(e.getPlayer().getUniqueId(), System.currentTimeMillis());
        }
    }
}
