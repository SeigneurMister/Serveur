package me.mister.jobs;

import me.mister.jobs.commands.JobHelpCommand;
import me.mister.jobs.commands.JobStatsCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class JobsPlugin extends JavaPlugin {

    private static JobsPlugin instance;
    private JobManager jobManager;

    @Override
    public void onEnable() {
        instance = this;
        jobManager = new JobManager();

        // Commandes
        getCommand("job").setExecutor(new JobCommand());
        getCommand("jobstats").setExecutor(new JobStatsCommand(jobManager));
        getCommand("jobhelp").setExecutor(new JobHelpCommand());

        getLogger().info("JobsPlugin activé !");
    }

    public static JobsPlugin getInstance() {
        return instance;
    }

    public JobManager getJobManager() {
        return jobManager;
    }
}
