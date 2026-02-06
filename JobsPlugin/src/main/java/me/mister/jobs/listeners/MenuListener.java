package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (e.getView().getTitle().equals("§6Choisis ton métier")) {
            e.setCancelled(true);

            if (!(e.getWhoClicked() instanceof Player p)) return;

            Material clicked = e.getCurrentItem() == null ? null : e.getCurrentItem().getType();

            if (clicked == Material.IRON_PICKAXE) {
                JobsPlugin.getInstance().getJobManager().setJob(p, Job.MINEUR);
            }

            if (clicked == Material.IRON_AXE) {
                JobsPlugin.getInstance().getJobManager().setJob(p, Job.BUCHERON);
            }

            if (clicked == Material.IRON_HOE) {
                JobsPlugin.getInstance().getJobManager().setJob(p, Job.FERMIER);
            }
        }
    }
}
