package me.mister.jobs.commands;

import me.mister.jobs.gui.JobMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("Commande réservée aux joueurs.");
            return true;
        }

        // /job help
        if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
            p.performCommand("jobhelp");
            return true;
        }

        // /job (ouvrir le menu)
        JobMenu menu = new JobMenu();
        p.openInventory(menu.getMenu(p));
        return true;
    }
}
