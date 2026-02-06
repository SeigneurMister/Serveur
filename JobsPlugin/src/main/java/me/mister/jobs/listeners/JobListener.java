package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Player;

public class JobListener implements Listener {

    private final JobManager jobManager;

    public JobListener(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Job job = jobManager.getJob(player);

        if (job == null) return;

        switch (job.getName()) {

            case "Mineur" -> {
                if (event.getBlock().getType() == Material.STONE) {
                    player.giveExp(3);
                }
            }

            case "Bûcheron" -> {
                if (event.getBlock().getType().name().contains("LOG")) {
                    player.giveExp(3);
                }
            }

            case "Fermier" -> {
                if (event.getBlock().getType() == Material.WHEAT) {
                    player.giveExp(3);
                }
            }
        }
    }
}