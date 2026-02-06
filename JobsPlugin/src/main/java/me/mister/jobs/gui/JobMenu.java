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

        Inventory inv = Bukkit.createInventory(null, 27, "§6§lMenu des Métiers");

        Job active = JobsPlugin.getInstance().getJobManager().getJob(p);

        // Fond décoratif
        ItemStack glass = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta gMeta = glass.getItemMeta();
        gMeta.setDisplayName(" ");
        glass.setItemMeta(gMeta);

        for (int i = 0; i < 27; i++) {
            inv.setItem(i, glass);
        }

        // Livre du créateur
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta bMeta = book.getItemMeta();
        bMeta.setDisplayName("§e§lÀ propos du plugin");
        bMeta.setLore(Arrays.asList(
                "§7Créé par : §6§lTMD_Mister",
                " ",
                "§fPlugin de métiers complet",
                "§favec niveaux, XP et bonus.",
                " ",
                "§eMerci d'utiliser ce plugin !"
        ));
        book.setItemMeta(bMeta);

        inv.setItem(4, book); // Centre en haut

        // MINEUR
        ItemStack mineur = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta mMeta = mineur.getItemMeta();
        mMeta.setDisplayName("§eMineur");
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
        ItemStack bucheron = new ItemStack(Material.IRON_AXE);
        ItemMeta buMeta = bucheron.getItemMeta();
        buMeta.setDisplayName("§eBûcheron");
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
        ItemStack fermier = new ItemStack(Material.IRON_HOE);
        ItemMeta fMeta = fermier.getItemMeta();
        fMeta.setDisplayName("§eFermier");
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
