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

        p.sendMessage("§6===== §eTes métiers §6=====");
        p.sendMessage("§eMineur : §a" + jobManager.getXp(p, "mineur") + " XP §7| Niveau §a" + jobManager.getLevel(p, "mineur"));
        p.sendMessage("§eBûcheron : §a" + jobManager.getXp(p, "bucheron") + " XP §7| Niveau §a" + jobManager.getLevel(p, "bucheron"));
        p.sendMessage("§eFermier : §a" + jobManager.getXp(p, "fermier") + " XP §7| Niveau §a" + jobManager.getLevel(p, "fermier"));

        return true;
    }
}
