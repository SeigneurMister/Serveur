package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class JobListener implements Listener {

    private final Map<Player, Long> lastXpTime = new HashMap<>();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Job job = JobsPlugin.getInstance().getJobManager().getJob(p);

        if (job == null) return;

        e.setExpToDrop(0);

        // Type du bloc cassé
        Material blockType = e.getBlock().getType();

        // Récupération dynamique de l’XP depuis blocks.yml
        int xpGain = JobsPlugin.getInstance().getBlockConfigManager().getXp(job, blockType);

        if (xpGain > 0) {

            JobsPlugin.getInstance().getJobManager().addXp(p, xpGain);

            // Actionbar +XP
            p.sendActionBar("§6§l+" + xpGain + " XP");

            // Enregistre le moment du gain d’XP
            lastXpTime.put(p, System.currentTimeMillis());

            // Timer pour effacer l’actionbar après 5 secondes
            new BukkitRunnable() {
                @Override
                public void run() {
                    long last = lastXpTime.getOrDefault(p, 0L);

                    if (System.currentTimeMillis() - last >= 5000) {
                        p.sendActionBar("");
                    }
                }
            }.runTaskLater(JobsPlugin.getInstance(), 20 * 5);
        }
    }
}
