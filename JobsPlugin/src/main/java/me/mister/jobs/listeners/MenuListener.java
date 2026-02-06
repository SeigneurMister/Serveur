package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import me.mister.jobs.gui.JobMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player p)) return;

        if (!e.getView().getTitle().equals("§b§lMétiers")) return;

        e.setCancelled(true);

        if (e.getCurrentItem() == null) return;

        switch (e.getRawSlot()) {

            case 20 -> JobsPlugin.getInstance().getJobManager().setJob(p, Job.MINEUR);
            case 22 -> JobsPlugin.getInstance().getJobManager().setJob(p, Job.BUCHERON);
            case 24 -> JobsPlugin.getInstance().getJobManager().setJob(p, Job.FERMIER);
        }

        p.openInventory(new JobMenu().getMenu(p));
    }
}
