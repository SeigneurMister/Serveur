package me.mister.jobs;

import me.mister.jobs.commands.JobCommand;
import me.mister.jobs.commands.JobHelpCommand;
import me.mister.jobs.commands.JobStatsCommand;
import me.mister.jobs.commands.JobResetCommand;
import me.mister.jobs.config.BlockConfigManager;
import me.mister.jobs.listeners.JobListener;
import me.mister.jobs.listeners.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

public class JobsPlugin extends JavaPlugin {

    private static JobsPlugin instance;
    private JobManager jobManager;
    private BlockConfigManager blockConfigManager;

    @Override
    public void onEnable() {
        instance = this;

        jobManager = new JobManager();
        blockConfigManager = new BlockConfigManager(this);

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new JobListener(), this);

        // Commandes
        getCommand("job").setExecutor(new JobCommand());
        getCommand("jobhelp").setExecutor(new JobHelpCommand());
        getCommand("jobstats").setExecutor(new JobStatsCommand());
        getCommand("jobreset").setExecutor(new JobResetCommand());

        getLogger().info("JobsPlugin activé avec succès !");
    }

    @Override
    public void onDisable() {
        getLogger().info("JobsPlugin désactivé.");
    }

    public static JobsPlugin getInstance() {
        return instance;
    }

    public JobManager getJobManager() {
        return jobManager;
    }

    public BlockConfigManager getBlockConfigManager() {
        return blockConfigManager;
    }
}
