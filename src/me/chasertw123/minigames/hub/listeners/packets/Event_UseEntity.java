package me.chasertw123.minigames.hub.listeners.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.npcs.NPC;
import me.chasertw123.minigames.hub.features.npcs.NPCType;
import org.bukkit.Bukkit;

public class Event_UseEntity {

    public Event_UseEntity() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(Main.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Client.USE_ENTITY) {
            @Override
            public void onPacketReceiving(PacketEvent event) {

                NPCType npcType = null;
                for (NPC npc : Main.getNPCManager().getNpcs().keySet())
                    if (event.getPacket().getIntegers().read(0) == npc.getEntityID()) {
                        npcType = npc.getNpcType();
                        break;
                    }

                if (npcType == null)
                    return;

                if(event.getPacket().getEntityUseActions().read(0) != EnumWrappers.EntityUseAction.ATTACK)
                    return;

                final NPCType type = npcType;
                Bukkit.getScheduler().runTask(Main.getInstance(), () -> type.getNPCInteraction().onInteract(Main.getUserManager().get(event.getPlayer().getUniqueId())));

                event.setCancelled(true);
            }
        });
    }

}
