package me.mister.jobs.config;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BlockConfigManager {

    private final JobsPlugin plugin;
    private File file;
    private FileConfiguration config;

    private final Map<UUID, Job> pendingAdd = new HashMap<>();

    public BlockConfigManager(JobsPlugin plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {
        file = new File(plugin.getDataFolder(), "blocks.yml");

        if (!file.exists()) {
            plugin.saveResource("blocks.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);

        plugin.getLogger().info("Clés trouvées dans blocks.yml : " + config.getKeys(false));
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getJobKey(Job job) {
        return job.name().toLowerCase();
    }

    public List<String> getBlocks(Job job) {
        ConfigurationSection section = config.getConfigurationSection(getJobKey(job));
        if (section == null) return new ArrayList<>();
        return new ArrayList<>(section.getKeys(false));
    }

    public int getXp(Job job, Material mat) {
        return config.getInt(getJobKey(job) + "." + mat.name(), 0);
    }

    public void addXp(Job job, Material mat, int amount) {
        int newXp = Math.max(0, getXp(job, mat) + amount);
        config.set(getJobKey(job) + "." + mat.name(), newXp);
        save();
    }

    public void addBlock(Job job, Material mat, int xp) {
        config.set(getJobKey(job) + "." + mat.name(), xp);
        save();
    }

    public void removeBlock(Job job, Material mat) {
        config.set(getJobKey(job) + "." + mat.name(), null);
        save();
    }

    public void startAddMode(Player p, Job job) {
        pendingAdd.put(p.getUniqueId(), job);
    }

    public boolean isInAddMode(Player p) {
        return pendingAdd.containsKey(p.getUniqueId());
    }

    public Job getPendingJob(Player p) {
        return pendingAdd.get(p.getUniqueId());
    }

    public void finishAddMode(Player p) {
        pendingAdd.remove(p.getUniqueId());
    }
}
