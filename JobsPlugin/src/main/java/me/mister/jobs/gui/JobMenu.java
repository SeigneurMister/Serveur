package me.mister.jobs.gui;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JobMenu {

    public Inventory getMenu(Player p) {

        Inventory inv = Bukkit.createInventory(null, 27, "§b§lMétiers");

        // Mineur
        ItemStack mineur = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta mMeta = mineur.getItemMeta();
        mMeta.setDisplayName("§eMineur");
        mineur.setItemMeta(mMeta);
        inv.setItem(11, mineur);

        // Bucheron
        ItemStack bucheron = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta bMeta = bucheron.getItemMeta();
        bMeta.setDisplayName("§eBûcheron");
        bucheron.setItemMeta(bMeta);
        inv.setItem(13, bucheron);

        // Fermier
        ItemStack fermier = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta fMeta = fermier.getItemMeta();
        fMeta.setDisplayName("§eFermier");
        fermier.setItemMeta(fMeta);
        inv.setItem(15, fermier);

        return inv;
    }
}
