package me.mister.jobs.commands;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobStatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("Commande réservée aux joueurs.");
            return true;
        }

        p.sendMessage("§b§l===== TES MÉTIERS =====");

        for (Job job : Job.values()) {

            int xp = JobsPlugin.getInstance().getJobManager().getXp(p, job);
            int lvl = JobsPlugin.getInstance().getJobManager().getLevel(p, job);
            int needed = lvl * 100;

            boolean actif = JobsPlugin.getInstance().getJobManager().getJob(p) == job;

            p.sendMessage(
                    (actif ? "§a§l[ACTIF] " : "§7") +
                    "§e" + job.name() +
                    " §7| Niveau : §b" + lvl +
                    " §7| XP : §b" + xp + "§7/§b" + needed
            );
        }

        p.sendMessage("§b========================");

        return true;
    }
}
