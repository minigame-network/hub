package me.chasertw123.minigames.hub.features.bossbar;

import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.hub.Main;
import org.bukkit.scheduler.BukkitRunnable;
import us.myles.ViaVersion.api.Via;
import us.myles.ViaVersion.api.ViaAPI;
import us.myles.ViaVersion.api.boss.BossBar;
import us.myles.ViaVersion.api.boss.BossColor;
import us.myles.ViaVersion.api.boss.BossStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Chase on 7/30/2017.
 */
public class Loop_AnimatedBossBar extends BukkitRunnable {

    private static Loop_AnimatedBossBar bossBarLoop = null;

    private Random random = new Random();
    private ViaAPI via = Via.getAPI();

    private BossBar bossBar = null;
    private AnimatedBossBar bar = null;
    private int interval = 0;

    private List<AnimatedBossBar> animatedBossBars;

    public Loop_AnimatedBossBar() {
        this.animatedBossBars = new ArrayList<>();

        HashMap<String, String[]> bossBarMessages = CoreAPI.getServerConfiguration().getBossBarMessages();
        for (String messageId : bossBarMessages.keySet())
            animatedBossBars.add(new AnimatedBossBar(bossBarMessages.get(messageId)));

        if (animatedBossBars.size() > 0) {
            this.bar = animatedBossBars.get(random.nextInt(animatedBossBars.size()));
            this.bossBar = via.createBossBar(bar.getAnimations()[interval++], 1F, BossColor.WHITE, BossStyle.SOLID);
        }

        this.runTaskTimer(Main.getInstance(), 10L, 10L);
    }

    @Override
    public void run() {

        if (bar == null)
            this.bar = animatedBossBars.get(random.nextInt(animatedBossBars.size()));

        if (bossBar == null)
            this.bossBar = via.createBossBar(bar.getAnimations()[interval++], 0F, BossColor.WHITE, BossStyle.SOLID);

        if (interval > (bar.getAnimations().length - 1)) {
            interval = 0;
            this.bar = animatedBossBars.get(random.nextInt(animatedBossBars.size()));
        }

        bossBar.setTitle(bar.getAnimations()[interval++]);
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public static Loop_AnimatedBossBar getInstance() {

        if (bossBarLoop == null)
            bossBarLoop = new Loop_AnimatedBossBar();

        return bossBarLoop;
    }
}
