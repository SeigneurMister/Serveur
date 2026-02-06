package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.gui.JobEditorMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player p)) return;

        if (!e.getView().getTitle().equals("§b§lAdministration des Métiers")) return;

        e.setCancelled(true);

        if (e.getCurrentItem() == null) return;

        switch (e.getRawSlot()) {

            case 10 -> { // Mineur
                p.openInventory(new JobEditorMenu().getMenu(p, Job.MINEUR));
            }

            case 12 -> { // Bûcheron
                p.openInventory(new JobEditorMenu().getMenu(p, Job.BUCHERON));
            }

            case 14 -> { // Fermier
                p.openInventory(new JobEditorMenu().getMenu(p, Job.FERMIER));
            }

            case 31 -> { // Retour
                p.closeInventory();
            }
        }
    }
}
