package me.mister.jobs.gui;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class JobEditorMenu {

    public Inventory getMenu(Player p, Job job) {

        Inventory inv = Bukkit.createInventory(null, 54, "§bÉditeur : " + job.name());

        ItemStack glass = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
        ItemMeta gMeta = glass.getItemMeta();
        gMeta.setDisplayName(" ");
        glass.setItemMeta(gMeta);

        for (int i = 0; i < 54; i++) inv.setItem(i, glass);

        List<String> blocks = JobsPlugin.getInstance().getBlockConfigManager().getBlocks(job);

        int slot = 10;

        for (String blockName : blocks) {

            Material mat = Material.matchMaterial(blockName);

            if (mat == null || !mat.isItem()) continue;

            int xp = JobsPlugin.getInstance().getBlockConfigManager().getXp(job, mat);

            ItemStack item = new ItemStack(mat);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e" + blockName);
            meta.setLore(List.of(
                    "§7XP donné : §b" + xp,
                    "",
                    "§aClic gauche : augmenter XP",
                    "§cClic droit : diminuer XP",
                    "§4Shift + clic : supprimer"
            ));
            item.setItemMeta(meta);

            inv.setItem(slot, item);

            slot++;
            if (slot == 17 || slot == 26 || slot == 35 || slot == 44) slot += 2;
        }

        ItemStack add = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta addMeta = add.getItemMeta();
        addMeta.setDisplayName("§aAjouter un bloc");
        add.setItemMeta(addMeta);
        inv.setItem(49, add);

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§cRetour");
        back.setItemMeta(backMeta);
        inv.setItem(45, back);

        return inv;
    }
}
