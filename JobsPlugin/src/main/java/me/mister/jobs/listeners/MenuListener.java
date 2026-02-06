package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import me.mister.jobs.gui.JobAdminMenu;
import me.mister.jobs.gui.JobMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player p)) return;

        if (!e.getView().getTitle().equals("§b§lMenu des Métiers")) return;

        e.setCancelled(true);

        if (e.getCurrentItem() == null) return;

        switch (e.getRawSlot()) {

            case 10 -> JobsPlugin.getInstance().getJobManager().setJob(p, Job.MINEUR);
            case 12 -> JobsPlugin.getInstance().getJobManager().setJob(p, Job.BUCHERON);
            case 14 -> JobsPlugin.getInstance().getJobManager().setJob(p, Job.FERMIER);

            case 16 -> p.performCommand("jobstats");

            case 22 -> {
                if (p.isOp()) p.openInventory(new JobAdminMenu().getMenu(p));
            }
        }

        p.openInventory(new JobMenu().getMenu(p));
    }
}
