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

        if (!(e.getWhoClicked() instanceof Player p)) return;

        // Vérifie le bon menu
        if (!e.getView().getTitle().equals("§b§lMétiers disponibles")) return;

        e.setCancelled(true);

        if (e.getCurrentItem() == null) return;

        Material clicked = e.getCurrentItem().getType();

        switch (clicked) {

            case DIAMOND_PICKAXE -> {
                JobsPlugin.getInstance().getJobManager().setJob(p, Job.MINEUR);
                p.sendMessage("§aTu es maintenant Mineur !");
                p.closeInventory();
            }

            case DIAMOND_AXE -> {
                JobsPlugin.getInstance().getJobManager().setJob(p, Job.BUCHERON);
                p.sendMessage("§aTu es maintenant Bûcheron !");
                p.closeInventory();
            }

            case DIAMOND_HOE -> {
                JobsPlugin.getInstance().getJobManager().setJob(p, Job.FERMIER);
                p.sendMessage("§aTu es maintenant Fermier !");
                p.closeInventory();
            }
        }
    }
}
