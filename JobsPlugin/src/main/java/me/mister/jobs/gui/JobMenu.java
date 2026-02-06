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

public class JobMenu {

    public Inventory getMenu(Player p) {

        Inventory inv = Bukkit.createInventory(null, 45, "§b§lMétiers");

        // Fond cyan moderne
        ItemStack glass = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
        ItemMeta gMeta = glass.getItemMeta();
        gMeta.setDisplayName(" ");
        glass.setItemMeta(gMeta);

        for (int i = 0; i < 45; i++) inv.setItem(i, glass);

        Job active = JobsPlugin.getInstance().getJobManager().getJob(p);

        // Livre d'informations
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta bMeta = book.getItemMeta();
        bMeta.setDisplayName("§bInformations du plugin");
        bMeta.setLore(java.util.List.of(
                "§7Plugin créé par §eTMD_Mister",
                "§7Système de métiers personnalisé",
                "",
                "§bClique sur un métier pour le choisir"
        ));
        book.setItemMeta(bMeta);
        inv.setItem(4, book);

        // MINEUR
        ItemStack mineur = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta mMeta = mineur.getItemMeta();
        mMeta.setDisplayName("§eMineur");

        if (active == Job.MINEUR) {
            mMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
            mMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        mineur.setItemMeta(mMeta);
        inv.setItem(20, mineur);

        // BUCHERON
        ItemStack bucheron = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta buMeta = bucheron.getItemMeta();
        buMeta.setDisplayName("§eBûcheron");

        if (active == Job.BUCHERON) {
            buMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
            buMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        bucheron.setItemMeta(buMeta);
        inv.setItem(22, bucheron);

        // FERMIER
        ItemStack fermier = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta fMeta = fermier.getItemMeta();
        fMeta.setDisplayName("§eFermier");

        if (active == Job.FERMIER) {
            fMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
            fMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        fermier.setItemMeta(fMeta);
        inv.setItem(24, fermier);

        return inv;
    }
}
