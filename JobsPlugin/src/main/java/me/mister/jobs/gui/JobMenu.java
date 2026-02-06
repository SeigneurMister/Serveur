package me.mister.jobs.gui;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class JobMenu {

    public Inventory getMenu(Player p) {

        Inventory inv = Bukkit.createInventory(null, 36, "§b§lMétiers disponibles");

        Job active = JobsPlugin.getInstance().getJobManager().getJob(p);

        // Fond moderne bleu/cyan
        ItemStack glass = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta gMeta = glass.getItemMeta();
        gMeta.setDisplayName(" ");
        glass.setItemMeta(gMeta);

        for (int i = 0; i < 36; i++) {
            inv.setItem(i, glass);
        }

        // Livre du créateur (slot 4)
        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta bMeta = book.getItemMeta();
        bMeta.setDisplayName("§b§lÀ propos du plugin");
        bMeta.setLore(Arrays.asList(
                "§7Créé par : §3§lTMD_Mister",
                "",
                "§fPlugin de métiers moderne",
                "§favec XP, niveaux et GUI stylé.",
                "",
                "§bMerci d'utiliser ce plugin !"
        ));
        book.setItemMeta(bMeta);
        inv.setItem(4, book);

        // On remet les slots des métiers à vide pour éviter le bug
        inv.setItem(11, null);
        inv.setItem(13, null);
        inv.setItem(15, null);

        // MINEUR
        ItemStack mineur = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta mMeta = mineur.getItemMeta();
        mMeta.setDisplayName("§bMineur");
        mMeta.setLore(Arrays.asList(
                "§7Casse des minerais et pierres",
                "§7pour gagner de l'XP.",
                "",
                active == Job.MINEUR ? "§a§lMétier actif" : "§7Clique pour choisir"
        ));

        if (active == Job.MINEUR) {
            mMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
            mMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        mineur.setItemMeta(mMeta);
        inv.setItem(11, mineur);

        // BUCHERON
        ItemStack bucheron = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta buMeta = bucheron.getItemMeta();
        buMeta.setDisplayName("§bBûcheron");
        buMeta.setLore(Arrays.asList(
                "§7Coupe des arbres",
                "§7pour gagner de l'XP.",
                "",
                active == Job.BUCHERON ? "§a§lMétier actif" : "§7Clique pour choisir"
        ));

        if (active == Job.BUCHERON) {
            buMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
            buMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        bucheron.setItemMeta(buMeta);
        inv.setItem(13, bucheron);

        // FERMIER
        ItemStack fermier = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta fMeta = fermier.getItemMeta();
        fMeta.setDisplayName("§bFermier");
        fMeta.setLore(Arrays.asList(
                "§7Récolte des cultures",
                "§7pour gagner de l'XP.",
                "",
                active == Job.FERMIER ? "§a§lMétier actif" : "§7Clique pour choisir"
        ));

        if (active == Job.FERMIER) {
            fMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
            fMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        fermier.setItemMeta(fMeta);
        inv.setItem(15, fermier);

        return inv;
    }
}
