package me.mister.jobs.gui;

import me.mister.jobs.JobManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JobMenu {

    public static void open(Player player, JobManager jobManager) {
        Inventory inv = Bukkit.createInventory(null, 27, "Choisis ton métier");

        inv.setItem(11, createItem(Material.IRON_PICKAXE, "§bMineur"));
        inv.setItem(13, createItem(Material.OAK_LOG, "§6Bûcheron"));
        inv.setItem(15, createItem(Material.WHEAT, "§eFermier"));

        player.openInventory(inv);
    }

    private static ItemStack createItem(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}