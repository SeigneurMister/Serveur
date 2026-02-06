package me.mister.jobs.config;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BlockConfigManager {

    private final JobsPlugin plugin;
    private File file;
    private FileConfiguration config;

    public BlockConfigManager(JobsPlugin plugin) {
        this.plugin = plugin;
        createFile();
    }

    private void createFile() {
        file = new File(plugin.getDataFolder(), "blocks.yml");

        if (!file.exists()) {
            plugin.saveResource("blocks.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getXp(Job job, Material block) {
        String path = job.name().toLowerCase() + "." + block.name();
        return config.getInt(path, 0);
    }

    public boolean hasBlock(Job job, Material block) {
        String path = job.name().toLowerCase() + "." + block.name();
        return config.contains(path);
    }

    public void setXp(Job job, Material block, int xp) {
        String path = job.name().toLowerCase() + "." + block.name();
        config.set(path, xp);
        save();
    }

    public void removeBlock(Job job, Material block) {
        String path = job.name().toLowerCase() + "." + block.name();
        config.set(path, null);
        save();
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
