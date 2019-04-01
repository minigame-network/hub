package me.chasertw123.minigames.hub.features.books;

import me.chasertw123.minigames.core.user.User;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaBook;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import us.myles.ViaVersion.api.Via;

import java.util.List;

/**
 * Created by Chase on 8/10/2017.
 */
public class Book_Vote implements Book {

    @Override
    @SuppressWarnings("unchecked")
    public ItemStack craftBookItemStack(User pp) {

        int version = Via.getAPI().getPlayerVersion(pp.getPlayer());
        cItemStack book = new cItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        List<IChatBaseComponent> pages;

        try {
            pages = (List<IChatBaseComponent>) CraftMetaBook.class.getDeclaredField("pages").get(bookMeta);
        } catch (ReflectiveOperationException ex) {
            ex.printStackTrace();
            return book;
        }

        TextComponent tc1 = new TextComponent(new ComponentBuilder("   Voting Reward").bold(true).append("\n\nVisit this voting website, fill in your Minecraft username and vote for us to receive your" +
                " free reward!\n\n\n").reset().create()), tc2 = new TextComponent(new ComponentBuilder("  ").append("CLICK TO CLAIM!").bold(true).underlined(true).color(ChatColor.LIGHT_PURPLE).create());

        if (version >= 335)
            tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click this link and then click ").color(ChatColor.YELLOW).append("YES").bold(true)
                    .color(ChatColor.GREEN).append(" when the prompt appears to visit the voting site!").bold(false).color(ChatColor.YELLOW).create()));

        else
            tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click this link and then click ").color(ChatColor.YELLOW).append("YES").bold(true)
                    .color(ChatColor.GREEN).append(" when the\nprompt appears to visit the voting site!").bold(false).color(ChatColor.YELLOW).create()));

        tc2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, pp.getCurrentVoteSite().getVoteURL()));
        tc1.addExtra(tc2);

        IChatBaseComponent page = IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(tc1));
        pages.add(page);

        book.setItemMeta(bookMeta);

        return book;
    }
}
