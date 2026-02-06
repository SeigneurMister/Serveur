package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

public class JobListener implements Listener {

    private final NamespacedKey placedKey = new NamespacedKey(JobsPlugin.getInstance(), "player_placed");

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Block block = e.getBlockPlaced();
        BlockState state = block.getState();

        if (state instanceof TileState tile) {
            tile.getPersistentDataContainer().set(placedKey, PersistentDataType.BYTE, (byte) 1);
            tile.update(true);
        } else {
            block.setMetadata("player_placed", new FixedMetadataValue(JobsPlugin.getInstance(), true));
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Block block = e.getBlock();
        BlockState state = block.getState();

        boolean placed = false;

        if (state instanceof TileState tile) {
            placed = tile.getPersistentDataContainer().has(placedKey, PersistentDataType.BYTE);
        } else if (block.hasMetadata("player_placed")) {
            placed = true;
        }

        if (placed) {
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
