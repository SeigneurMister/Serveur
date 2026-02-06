package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;

public class MenuListener implements Listener {

    private final JobManager jobManager;

    public MenuListener(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Choisis ton métier")) return;

        event.setCancelled(true);

        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) return;

        String name = event.getCurrentItem().getItemMeta().getDisplayName();

        switch (name) {
            case "§bMineur" -> jobManager.setJob(player, new Job("Mineur"));
            case "§6Bûcheron" -> jobManager.setJob(player, new Job("Bûcheron"));
            case "§eFermier" -> jobManager.setJob(player, new Job("Fermier"));
        }

        player.closeInventory();
    }
}
