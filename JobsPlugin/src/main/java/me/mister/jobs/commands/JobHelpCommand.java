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

        p.sendMessage("§6===== §eAide des métiers §6=====");
        p.sendMessage("§e/job §7→ Ouvre le menu des métiers");
        p.sendMessage("§e/jobstats §7→ Affiche ton XP et ton niveau");
        p.sendMessage("§e/job help §7→ Affiche cette aide");

        return true;
    }
}
