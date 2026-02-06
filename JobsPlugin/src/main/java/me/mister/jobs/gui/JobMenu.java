package me.mister.jobs.gui;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class JobMenu {

    public Inventory getMenu(Player p) {

        Inventory inv = Bukkit.createInventory(null, 27, "§b§lMenu des Métiers");

        // Fond bleu/cyan
        ItemStack glass = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
        ItemMeta gMeta = glass.getItemMeta();
        gMeta.setDisplayName(" ");
        glass.setItemMeta(gMeta);

        for (int i = 0; i < 27; i++) inv.setItem(i, glass);

        Job active = JobsPlugin.getInstance().getJobManager().getJob(p);

        // MINEUR
        ItemStack mineur = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta mMeta = mineur.getItemMeta();
        mMeta.setDisplayName("§eMineur");
        mMeta.setLore(Arrays.asList(
                "§7Miner des blocs pour gagner de l'XP.",
                "",
                active == Job.MINEUR ? "§a§lMÉTIER ACTIF" : "§bClique pour choisir"
        ));
        mineur.setItemMeta(mMeta);
        inv.setItem(10, mineur);

        // BUCHERON
        ItemStack bucheron = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta bMeta = bucheron.getItemMeta();
        bMeta.setDisplayName("§eBûcheron");
        bMeta.setLore(Arrays.asList(
                "§7Couper du bois pour gagner de l'XP.",
                "",
                active == Job.BUCHERON ? "§a§lMÉTIER ACTIF" : "§bClique pour choisir"
        ));
        bucheron.setItemMeta(bMeta);
        inv.setItem(12, bucheron);

        // FERMIER
        ItemStack fermier = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta fMeta = fermier.getItemMeta();
        fMeta.setDisplayName("§eFermier");
        fMeta.setLore(Arrays.asList(
                "§7Récolter des cultures pour gagner de l'XP.",
                "",
                active == Job.FERMIER ? "§a§lMÉTIER ACTIF" : "§bClique pour choisir"
        ));
        fermier.setItemMeta(fMeta);
        inv.setItem(14, fermier);

        // Stats
        ItemStack stats = new ItemStack(Material.BOOK);
        ItemMeta sMeta = stats.getItemMeta();
        sMeta.setDisplayName("§bVoir mes statistiques");
        stats.setItemMeta(sMeta);
        inv.setItem(16, stats);

        // Admin
        if (p.isOp()) {
            ItemStack admin = new ItemStack(Material.REDSTONE);
            ItemMeta aMeta = admin.getItemMeta();
            aMeta.setDisplayName("§cAdministration");
            admin.setItemMeta(aMeta);
            inv.setItem(22, admin);
        }

        return inv;
    }
}
