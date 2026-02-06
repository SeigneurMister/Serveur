package me.mister.jobs.listeners;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import me.mister.jobs.gui.JobAdminMenu;
import me.mister.jobs.gui.JobEditorMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class JobEditorListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player p)) return;

        String title = e.getView().getTitle();
        if (!title.startsWith("§bÉditeur : ")) return;

        e.setCancelled(true);

        Job job = Job.valueOf(title.replace("§bÉditeur : ", ""));

        if (e.getCurrentItem() == null) return;

        int slot = e.getRawSlot();

        // Retour
        if (slot == 45) {
            p.openInventory(new JobAdminMenu().getMenu(p));
            return;
        }

        // Ajouter un bloc
        if (slot == 49) {
            p.closeInventory();
            p.sendMessage("§aTape le nom du bloc à ajouter dans le chat.");
            JobsPlugin.getInstance().getBlockConfigManager().startAddMode(p, job);
            return;
        }

        Material mat = e.getCurrentItem().getType();
        if (mat == Material.CYAN_STAINED_GLASS_PANE || mat == Material.ARROW || mat == Material.EMERALD_BLOCK)
            return;

        // Clic gauche → +1 XP
        if (e.isLeftClick() && !e.isShiftClick()) {
            JobsPlugin.getInstance().getBlockConfigManager().addXp(job, mat, 1);
            p.openInventory(new JobEditorMenu().getMenu(p, job));
            return;
        }

        // Clic droit → -1 XP
        if (e.isRightClick() && !e.isShiftClick()) {
            JobsPlugin.getInstance().getBlockConfigManager().addXp(job, mat, -1);
            p.openInventory(new JobEditorMenu().getMenu(p, job));
            return;
        }

        // Shift + clic → supprimer
        if (e.isShiftClick()) {
            JobsPlugin.getInstance().getBlockConfigManager().removeBlock(job, mat);
            p.openInventory(new JobEditorMenu().getMenu(p, job));
        }
    }
}
