package me.mister.jobs.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class JobAdminMenu {

    public Inventory getMenu(Player p) {

        Inventory inv = Bukkit.createInventory(null, 36, "§b§lAdministration des Métiers");

        // Fond moderne bleu/cyan
        ItemStack glass = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
        ItemMeta gMeta = glass.getItemMeta();
        gMeta.setDisplayName(" ");
        glass.setItemMeta(gMeta);

        for (int i = 0; i < 36; i++) {
            inv.setItem(i, glass);
        }

        // MINEUR
        ItemStack mineur = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta mMeta = mineur.getItemMeta();
        mMeta.setDisplayName("§bÉditer Mineur");
        mMeta.setLore(Arrays.asList(
                "§7Configurer les blocs du métier",
                "§7et l'XP qu'ils donnent.",
                "",
                "§bClique pour ouvrir"
        ));
        mineur.setItemMeta(mMeta);
        inv.setItem(10, mineur);

        // BUCHERON
        ItemStack bucheron = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta bMeta = bucheron.getItemMeta();
        bMeta.setDisplayName("§bÉditer Bûcheron");
        bMeta.setLore(Arrays.asList(
                "§7Configurer les blocs du métier",
                "§7et l'XP qu'ils donnent.",
                "",
                "§bClique pour ouvrir"
        ));
        bucheron.setItemMeta(bMeta);
        inv.setItem(12, bucheron);

        // FERMIER
        ItemStack fermier = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta fMeta = fermier.getItemMeta();
        fMeta.setDisplayName("§bÉditer Fermier");
        fMeta.setLore(Arrays.asList(
                "§7Configurer les blocs du métier",
                "§7et l'XP qu'ils donnent.",
                "",
                "§bClique pour ouvrir"
        ));
        fermier.setItemMeta(fMeta);
        inv.setItem(14, fermier);

        // Bouton retour
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName("§cRetour");
        back.setItemMeta(backMeta);
        inv.setItem(31, back);

        return inv;
    }
}
