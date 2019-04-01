package me.chasertw123.minigames.hub.listeners;

import me.chasertw123.minigames.core.event.UserChatEvent;
import me.chasertw123.minigames.core.user.User;
import me.chasertw123.minigames.core.user.data.stats.Stat;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Event_PlayerChatEvent implements Listener {

    private static final ChatColor[] LEVEL_COLORS = {ChatColor.GRAY, ChatColor.WHITE, ChatColor.GOLD, ChatColor.AQUA, ChatColor.GREEN};

    @EventHandler
    public void onPlayerChat(UserChatEvent event) {

        User pp = event.getUser();
        ChatColor rankColor = pp.getRank().getRankColor(), chatColor = pp.getRank().getChatColor(), levelColor = LEVEL_COLORS[(pp.getLevel() - 1) / 100];

        if (!pp.getRank().isStaff() && pp.isDeluxe()) {
            rankColor = ChatColor.GOLD;
            chatColor = ChatColor.WHITE;
        }

        TextComponent recipientMessage = new TextComponent(new ComponentBuilder(pp.getLevel() + " ").color(levelColor).create());
        TextComponent username = new TextComponent(new ComponentBuilder(pp.getUsername()).color(rankColor).create());

        username.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/profile " + pp.getUsername()));
        username.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Player Information\n").color(ChatColor.YELLOW)
                .append("Rank: ").color(ChatColor.WHITE).append(pp.getRank().toString() + "\n\n").color(pp.getRank().getRankColor())
                .append("Level: ").color(ChatColor.WHITE).append(pp.getLevel() + "\n").color(ChatColor.GREEN)
                .append("Experience Left: ").color(ChatColor.WHITE).append((pp.getExperienceToNextLevel() <= 0 ? "MAX LEVEL" : pp.getExperienceToNextLevel()) + "\n\n").color(ChatColor.GREEN)
                .append("Friends: ").color(ChatColor.WHITE).append(pp.getFriends().size() + "\n\n").color(ChatColor.GREEN)
                .append("Achievements Complete: ").color(ChatColor.WHITE).append(pp.getAchievements().size() + "\n").color(ChatColor.GREEN)
                .append("Chests Opened: ").color(ChatColor.WHITE).append(pp.getStat(Stat.CHESTS_OPENED) + "\n\n").color(ChatColor.GREEN)
                .append("Click to open " + pp.getUsername() + "'s profile!").color(ChatColor.YELLOW).create()));

        recipientMessage.addExtra(username);
        recipientMessage.addExtra(new TextComponent(new ComponentBuilder(" » ").color(ChatColor.DARK_GRAY).append(event.getMessage()).color(chatColor).create()));

        event.setRecipientMessage(recipientMessage);
        event.setSenderMessage(new TextComponent(new ComponentBuilder(pp.getLevel() + " ").color(levelColor).append(pp.getUsername()).color(rankColor)
                .append(" » ").color(ChatColor.DARK_GRAY).append(event.getMessage()).color(chatColor).create()));
    }
}
