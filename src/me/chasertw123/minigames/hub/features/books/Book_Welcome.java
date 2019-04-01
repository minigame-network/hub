package me.chasertw123.minigames.hub.features.books;

import me.chasertw123.minigames.core.user.User;
import me.chasertw123.minigames.core.utils.items.cItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;

/**
 * Created by Chase on 8/10/2017.
 */
public class Book_Welcome implements Book {

    // ChatColor.BOLD + "    Welcome to \n" + ChatColor.GREEN + "" + ChatColor.BOLD + "    MC" + ChatColor.AQUA + "" + ChatColor.BOLD + "Paradise!"

    @Override
    public ItemStack craftBookItemStack(User pp) {

        cItemStack writtenBook = new cItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();

        bookMeta.setTitle(ChatColor.LIGHT_PURPLE + "Welcome to " + ChatColor.RED + "Tricade" + ChatColor.WHITE + "Games");
        bookMeta.setAuthor(ChatColor.RED + "Tricade" + ChatColor.WHITE + "Games");
        bookMeta.setPages(Arrays.asList(new String[] {ChatColor.LIGHT_PURPLE + " " + ChatColor.UNDERLINE + "Welcome to Paradise\n\n" + ChatColor.BLACK + "Here is where the sun never sets and the days last" +
                " forever. Join everyone and soak in the sun! Use your compass to quickly join others in our own fun and unique games!"}));

        writtenBook.setItemMeta(bookMeta);

        return writtenBook;
    }
}
