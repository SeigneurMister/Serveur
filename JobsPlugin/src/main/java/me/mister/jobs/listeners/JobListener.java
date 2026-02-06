package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Bukkit;
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

        int xpGain = 0;

        switch (job) {

            case MINEUR -> {
                if (e.getBlock().getType().toString().contains("STONE") ||
                    e.getBlock().getType().toString().contains("ORE")) {
                    xpGain = 10;
                }
            }

            case BUCHERON -> {
                if (e.getBlock().getType().toString().contains("LOG")) {
                    xpGain = 10;
                }
            }

            case FERMIER -> {
                if (e.getBlock().getType() == Material.WHEAT ||
                    e.getBlock().getType() == Material.CARROTS ||
                    e.getBlock().getType() == Material.POTATOES) {
                    xpGain = 10;
                }
            }
        }

        if (xpGain > 0) {

            JobsPlugin.getInstance().getJobManager().addXp(p, xpGain);

            // Actionbar +XP
            p.sendActionBar("§6§l+" + xpGain + " XP");

            // Enregistre le moment du gain d’XP
            lastXpTime.put(p, System.currentTimeMillis());

            // Lance un timer pour effacer après 5 secondes
            new BukkitRunnable() {
                @Override
                public void run() {
                    long last = lastXpTime.getOrDefault(p, 0L);

                    // Si 5 secondes sont passées sans nouveau XP → effacer
                    if (System.currentTimeMillis() - last >= 5000) {
                        p.sendActionBar("");
                    }
                }
            }.runTaskLater(JobsPlugin.getInstance(), 20 * 5); // 5 secondes
        }
    }
}
