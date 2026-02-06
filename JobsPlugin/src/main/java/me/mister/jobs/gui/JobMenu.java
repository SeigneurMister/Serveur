package me.mister.jobs.gui;

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

        Inventory inv = Bukkit.createInventory(null, 27, "§6Métiers");

        Material held = p.getInventory().getItemInMainHand().getType();

        // Mineur
        ItemStack mineur = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta mMeta = mineur.getItemMeta();
        mMeta.setDisplayName("§eMineur");

        if (held.toString().contains("PICKAXE")) {
            mMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            mMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        mineur.setItemMeta(mMeta);
        inv.setItem(11, mineur);

        // Bucheron
        ItemStack bucheron = new ItemStack(Material.IRON_AXE);
        ItemMeta bMeta = bucheron.getItemMeta();
        bMeta.setDisplayName("§eBûcheron");

        if (held.toString().contains("AXE")) {
            bMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            bMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        bucheron.setItemMeta(bMeta);
        inv.setItem(13, bucheron);

        // Fermier
        ItemStack fermier = new ItemStack(Material.IRON_HOE);
        ItemMeta fMeta = fermier.getItemMeta();
        fMeta.setDisplayName("§eFermier");

        if (held.toString().contains("HOE")) {
            fMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            fMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        fermier.setItemMeta(fMeta);
        inv.setItem(15, fermier);

        return inv;
    }
}
