package me.mister.jobs;

import me.mister.jobs.commands.JobCommand;
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

        // Gestionnaire des métiers
        jobManager = new JobManager();

        // Gestionnaire des blocs (blocks.yml)
        blockConfigManager = new BlockConfigManager(this);

        // Enregistrement des listeners
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new JobListener(), this);

        // Commande /job
        getCommand("job").setExecutor(new JobCommand());

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
