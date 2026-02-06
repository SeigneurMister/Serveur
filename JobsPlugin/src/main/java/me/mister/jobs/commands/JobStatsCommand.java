package me.mister.jobs.commands;

import me.mister.jobs.JobManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobStatsCommand implements CommandExecutor {

    private final JobManager jobManager;

    public JobStatsCommand(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("Commande réservée aux joueurs.");
            return true;
        }

        p.sendMessage("§6===== §eTes stats §6=====");
        p.sendMessage("§eMétier actif : §a" + jobManager.getJob(p));
        p.sendMessage("§eXP : §a" + jobManager.getXp(p));
        p.sendMessage("§eNiveau : §a" + jobManager.getLevel(p));

        return true;
    }
}
