package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;

public class JobListener implements Listener {

    private final NamespacedKey placedKey = new NamespacedKey(JobsPlugin.getInstance(), "player_placed");

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        BlockState state = e.getBlockPlaced().getState();
        state.getPersistentDataContainer().set(placedKey, PersistentDataType.BYTE, (byte) 1);
        state.update(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Block block = e.getBlock();
        BlockState state = block.getState();

        if (state.getPersistentDataContainer().has(placedKey, PersistentDataType.BYTE)) {
            return;
        }

        for (Job job : Job.values()) {
            int xp = JobsPlugin.getInstance().getBlockConfigManager().getXp(job, block.getType());
            if (xp > 0) {
                p.sendMessage("§a+" + xp + " XP (" + job.name() + ")");
            }
        }
    }
}
