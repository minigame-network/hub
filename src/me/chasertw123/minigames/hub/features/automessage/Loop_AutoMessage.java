package me.chasertw123.minigames.hub.features.automessage;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.user.User;
import me.chasertw123.minigames.core.user.data.settings.Setting;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.automessage.broadcasts.Broadcasts;
import me.chasertw123.minigames.shared.framework.ServerType;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Created by Chase on 7/29/2017.
 */
public class Loop_AutoMessage extends BukkitRunnable {

    private Random random = new Random();

    public Loop_AutoMessage() {
        this.runTaskTimer(Main.getInstance(), 0L, 3000);
    }

    @Override
    public void run() {

        if (CoreAPI.getServerDataManager().getServerType() == ServerType.MINIGAME) {
            this.cancel();
            return;
        }

        for (User pp : CoreAPI.getOnlinePlayers()) {
            if (!pp.getSetting(Setting.AUTO_MESSAGES))
                continue;

            Broadcasts.values()[random.nextInt(Broadcasts.values().length)].send(pp.getPlayer());
            pp.getPlayer().playSound(pp.getPlayer().getLocation(), Sound.LEVEL_UP, 1.25F, 0.5F);
        }
    }
}
