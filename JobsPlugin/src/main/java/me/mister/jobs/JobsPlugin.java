package me.mister.jobs;

import me.mister.jobs.commands.JobCommand;
import me.mister.jobs.listeners.JobListener;
import me.mister.jobs.listeners.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

public class JobsPlugin extends JavaPlugin {
    private JobManager jobManager;

    @Override
    public void onEnable() {
        jobManager = new JobManager(this);

        getCommand("job").setExecutor(new JobCommand(jobManager));

        getServer().getPluginManager().registerEvents(new JobListener(jobManager), this);
        getServer().getPluginManager().registerEvents(new MenuListener(jobManager), this);

        getLogger().info("JobsPlugin activé !");
    }

    public JobManager getJobManager() {
        return jobManager;
    }
}