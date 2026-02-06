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

        // Fond cyan moderne
        ItemStack glass = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
        ItemMeta gMeta = glass.getItemMeta();
        gMeta.setDisplayName(" ");
        glass.setItemMeta(gMeta);

        for (int i = 0; i < 45; i++) {
            inv.setItem(i, glass);
        }

        // MINEUR
        ItemStack mineur = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta mMeta = mineur.getItemMeta();
        mMeta.setDisplayName("§eÉditer Mineur");
        mMeta.setLore(List.of(
                "§7Configurer les blocs du métier",
                "§7et l'XP qu'ils donnent.",
                "",
                "§bClique pour ouvrir l'éditeur"
        ));
        mMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES); // ✔ IMPORTANT
        mineur.setItemMeta(mMeta);
        inv.setItem(20, mineur);

        // BUCHERON
        ItemStack bucheron = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta bMeta = bucheron.getItemMeta();
        bMeta.setDisplayName("§eÉditer Bûcheron");
        bMeta.setLore(List.of(
                "§7Configurer les blocs du métier",
                "§7et l'XP qu'ils donnent.",
                "",
                "§bClique pour ouvrir l'éditeur"
        ));
        bMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES); // ✔ IMPORTANT
        bucheron.setItemMeta(bMeta);
        inv.setItem(22, bucheron);

        // FERMIER
        // FERMIER
        ItemStack fermier = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta fMeta = fermier.getItemMeta();
        fMeta.setDisplayName("§eÉditer Fermier");
        fMeta.setLore(List.of(
                "§7Configurer les blocs du métier",
                "§7et l'XP qu'ils donnent.",
                "",
                "§bClique pour ouvrir l'éditeur"
        ));
        fMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES); // ✔ IMPORTANT
        fermier.setItemMeta(fMeta);
        inv.setItem(24, fermier);


        // Bouton retour
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§cRetour");
        back.setItemMeta(backMeta);
        inv.setItem(40, back);

        return inv;
    }
}
