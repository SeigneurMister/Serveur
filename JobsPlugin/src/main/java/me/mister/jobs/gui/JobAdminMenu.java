package me.mister.jobs.gui;

import me.mister.jobs.Job;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class JobAdminMenu {

    public Inventory getMenu(Player p) {

        Inventory inv = Bukkit.createInventory(null, 45, "§b§lAdministration des Métiers");

        ItemStack glass = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
        ItemMeta gMeta = glass.getItemMeta();
        gMeta.setDisplayName(" ");
        glass.setItemMeta(gMeta);

        for (int i = 0; i < 45; i++) inv.setItem(i, glass);

        inv.setItem(20, create(Material.DIAMOND_PICKAXE, "§eÉditer Mineur"));
        inv.setItem(22, create(Material.DIAMOND_AXE, "§eÉditer Bûcheron"));
        inv.setItem(24, create(Material.DIAMOND_HOE, "§eÉditer Fermier"));

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§cRetour");
        back.setItemMeta(backMeta);
        inv.setItem(40, back);

        return inv;
    }

    private ItemStack create(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(List.of(
                "§7Configurer les blocs du métier",
                "§7et l'XP qu'ils donnent.",
                "",
                "§bClique pour ouvrir l'éditeur"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }
}
