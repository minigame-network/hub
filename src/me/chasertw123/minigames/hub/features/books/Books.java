package me.chasertw123.minigames.hub.features.books;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.chasertw123.minigames.core.user.User;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Chase on 8/10/2017.
 */
public enum Books {

    FIRST_JOIN(new Book_Welcome()),
    VOTE(new Book_Vote());

    private Book book;

    Books(Book book) {
        this.book = book;
    }

    /**
     * Opens a book for {@link me.chasertw123.minigames.core.user.User} with data specific to them
     * @param pp {@link me.chasertw123.minigames.core.user.User} to craft and open the book for
     */
    public void openBook(User pp) {

        int slot = pp.getPlayer().getInventory().getHeldItemSlot();
        ItemStack old = pp.getPlayer().getInventory().getItem(slot);

        pp.getPlayer().getInventory().setItem(slot, book.craftBookItemStack(pp));

        ByteBuf buf = Unpooled.buffer(256);
        buf.setByte(0, (byte)0);
        buf.writerIndex(1);

        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(buf));

        ((CraftPlayer) pp.getPlayer()).getHandle().playerConnection.sendPacket(packet);
        pp.getPlayer().getInventory().setItem(slot, old);
    }

}
