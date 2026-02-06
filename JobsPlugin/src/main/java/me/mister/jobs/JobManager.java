package me.mister.jobs;

import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class JobManager {

    private final Map<Player, Job> playerJobs = new HashMap<>();
    private final JobsPlugin plugin;

    public JobManager(JobsPlugin plugin) {
        this.plugin = plugin;
    }

    public void setJob(Player player, Job job) {
        playerJobs.put(player, job);
        player.sendMessage("§aTu es maintenant " + job.getName());
    }

    public Job getJob(Player player) {
        return playerJobs.get(player);
    }
}