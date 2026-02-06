package me.mister.jobs.commands;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobResetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("Commande réservée aux joueurs.");
            return true;
        }

        if (args.length != 1) {
            p.sendMessage("§cUsage : /jobreset <mineur|bucheron|fermier>");
            return true;
        }

        try {
            Job job = Job.valueOf(args[0].toUpperCase());
            JobsPlugin.getInstance().getJobManager().resetJob(p, job);
        } catch (Exception ex) {
            p.sendMessage("§cMétier inconnu.");
        }

        return true;
    }
}
