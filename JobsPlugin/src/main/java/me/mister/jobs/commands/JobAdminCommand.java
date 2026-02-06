package me.mister.jobs.commands;

import me.mister.jobs.gui.JobAdminMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobAdminCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("Commande réservée aux joueurs.");
            return true;
        }

        if (!p.hasPermission("jobs.admin")) {
            p.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        p.openInventory(new JobAdminMenu().getMenu(p));
        return true;
    }
}
