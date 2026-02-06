package me.mister.jobs.commands;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobResetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length != 2) {
            sender.sendMessage("§cUsage : /jobreset <joueur> <mineur|bucheron|fermier>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("§cCe joueur n'est pas connecté.");
            return true;
        }

        try {
            Job job = Job.valueOf(args[1].toUpperCase());

            JobsPlugin.getInstance().getJobManager().resetJob(target, job);

            sender.sendMessage("§aTu as reset le métier §e" + job.name() + " §ade §e" + target.getName());
            target.sendMessage("§cTon métier §e" + job.name() + " §ca été réinitialisé !");
        } catch (Exception ex) {
            sender.sendMessage("§cMétier inconnu.");
        }

        return true;
    }
}
