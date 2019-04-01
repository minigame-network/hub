package me.chasertw123.minigames.hub.features.npcs;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.chasertw123.minigames.hub.Main;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class NPC {

    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";

    private int entityID;
    private Location location;
    private NPCType npcType;
    private GameProfile gameProfile;

    NPC(NPCType npcType, Location location) {
        this.npcType = npcType;
        this.location = location;
        entityID = (int) Math.ceil(Math.random() * 1000) + 2000;
        gameProfile = new GameProfile(UUID.randomUUID(), ChatColor.DARK_GRAY + "[NPC] " + randomAlphaNumeric(8));
        gameProfile.getProperties().put("textures", new Property("textures", npcType.getSkinValue(), npcType.getSkinSignature()));
    }

    void show(Player... players) {

        PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn();

        setValue(packet, "a", entityID);
        setValue(packet, "b", gameProfile.getId());
        setValue(packet, "c", MathHelper.floor(location.getX() * 32.0D));
        setValue(packet, "d", MathHelper.floor(location.getY() * 32.0D));
        setValue(packet, "e", MathHelper.floor(location.getZ() * 32.0D));
        setValue(packet, "f", (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
        setValue(packet, "g", (byte) ((int) (location.getPitch() * 256.0F / 360.0F)));
        setValue(packet, "h", 0);

        DataWatcher w = new DataWatcher(null);

        w.a(6,(float)20);
        w.a(10,(byte)127);

        setValue(packet, "i", w);

        this.addToTabList(players);
        this.sendPacket(packet, players);
        this.updateHeadRotation(players);
        this.hideNameTag(players);

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> this.removeFromTabList(players), 30L);
    }

    void destroy(Player... players) {
        sendPacket(new PacketPlayOutEntityDestroy(entityID), players);
    }

    public NPCType getNpcType() {
        return npcType;
    }

    public int getEntityID() {
        return entityID;
    }

    public Location getLocation() {
        return location;
    }

    private void updateHeadRotation(Player... players) {

        PacketPlayOutEntity.PacketPlayOutEntityLook packet = new PacketPlayOutEntity.PacketPlayOutEntityLook(entityID,
                (byte) ((int) (location.getYaw() * 256.0F / 360.0F)),
                (byte) ((int) (location.getPitch() * 256.0F / 360.0F)), true);

        PacketPlayOutEntityHeadRotation packetHead = new PacketPlayOutEntityHeadRotation();

        setValue(packetHead, "a", entityID);
        setValue(packetHead, "b", (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));

        sendPacket(packet, players);
        sendPacket(packetHead, players);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    private void addToTabList(Player... players) {

        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameProfile, 1, WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameProfile.getName())[0]);

        List<PacketPlayOutPlayerInfo.PlayerInfoData> playersInfo = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
        playersInfo.add(data);

        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
        setValue(packet, "b", playersInfo);

        sendPacket(packet, players);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    private void removeFromTabList(Player... players) {

        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packet.new PlayerInfoData(gameProfile, 1, WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString(gameProfile.getName())[0]);

        List<PacketPlayOutPlayerInfo.PlayerInfoData> playersInfo = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packet, "b");
        playersInfo.add(data);

        setValue(packet, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER);
        setValue(packet, "b", playersInfo);

        sendPacket(packet, players);
    }

    private void hideNameTag(Player... players) {

        EntityArmorStand passenger = new EntityArmorStand(((CraftServer) Bukkit.getServer()).getServer().getWorldServer(0), location.getX(), location.getY(), location.getZ());
        passenger.setInvisible(true);

        PacketContainer attachEntityPacket = new PacketContainer(PacketType.Play.Server.ATTACH_ENTITY);
        attachEntityPacket.getModifier().writeDefaults();
        attachEntityPacket.getIntegers().write(0, 0);
        attachEntityPacket.getIntegers().write(1, passenger.getId());
        attachEntityPacket.getIntegers().write(2, entityID);

        Arrays.stream(players).forEach(player -> {
            try {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY_LIVING, new PacketPlayOutSpawnEntityLiving(passenger)));
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, attachEntityPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setValue(Object obj, String name, Object value){
        try {
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Object getValue(Object obj, String name){
        try {
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sendPacket(Packet<?> packet, Player... players){
        Arrays.stream(players).forEach(player -> ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet));
    }

    private static String randomAlphaNumeric(int count) {

        StringBuilder builder = new StringBuilder();
        while (count-- != 0)
            builder.append(ALPHA_NUMERIC_STRING.charAt((int) (Math.random()*ALPHA_NUMERIC_STRING.length())));

        return builder.toString();
    }
}
