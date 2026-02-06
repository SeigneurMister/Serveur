package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class JobListener implements Listener {

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
            p.sendMessage("§a+" + xpGain + " XP");
        }
    }
}
