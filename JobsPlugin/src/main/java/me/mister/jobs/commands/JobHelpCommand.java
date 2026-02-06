package me.mister.jobs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobHelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("Commande réservée aux joueurs.");
            return true;
        }

        p.sendMessage("§b§l===== AIDE DES MÉTIERS =====");
        p.sendMessage("§e/job §7→ Ouvre le menu des métiers");
        p.sendMessage("§e/jobstats §7→ Affiche tes niveaux et XP");
        p.sendMessage("§e/jobreset <métier> §7→ Reset un métier");
        p.sendMessage("§b==============================");

        return true;
    }
}
