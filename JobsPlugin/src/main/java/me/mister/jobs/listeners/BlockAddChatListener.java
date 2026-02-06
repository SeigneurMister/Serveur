package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import me.mister.jobs.gui.JobEditorMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class BlockAddChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();

        if (!JobsPlugin.getInstance().getBlockConfigManager().isInAddMode(p)) return;

        e.setCancelled(true);

        Job job = JobsPlugin.getInstance().getBlockConfigManager().getPendingJob(p);
        String msg = e.getMessage().toUpperCase();

        Material mat = Material.matchMaterial(msg);

        if (mat == null) {
            p.sendMessage("§cBloc inconnu. Réessaie.");
            return;
        }

        JobsPlugin.getInstance().getBlockConfigManager().addBlock(job, mat, 1);
        JobsPlugin.getInstance().getBlockConfigManager().finishAddMode(p);

        p.sendMessage("§aBloc §e" + mat.name() + " §aajouté avec 1 XP par défaut.");
        p.openInventory(new JobEditorMenu().getMenu(p, job));
    }
}
