package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();

        if (!JobsPlugin.getInstance().getBlockConfigManager().isInAddMode(p)) return;

        e.setCancelled(true);

        Job job = JobsPlugin.getInstance().getBlockConfigManager().getPendingJob(p);

        String msg = e.getMessage().toUpperCase();
        Material mat = Material.matchMaterial(msg);

        if (mat == null || !mat.isItem()) {
            p.sendMessage("§cBloc invalide !");
            return;
        }

        JobsPlugin.getInstance().getBlockConfigManager().addBlock(job, mat, 1);
        JobsPlugin.getInstance().getBlockConfigManager().finishAddMode(p);

        p.sendMessage("§aBloc ajouté !");
    }
}
