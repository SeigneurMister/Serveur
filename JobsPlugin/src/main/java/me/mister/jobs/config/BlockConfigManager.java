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

    // Mode ajout via le chat
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

        // ⭐ TEST DEBUG : affiche les sections trouvées
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

    // Récupère la liste des blocs d’un métier
    public List<String> getBlocks(Job job) {
        String key = getJobKey(job);
        ConfigurationSection section = config.getConfigurationSection(key);
        if (section == null) return new ArrayList<>();
        return new ArrayList<>(section.getKeys(false));
    }

    // Récupère l’XP d’un bloc
    public int getXp(Job job, Material mat) {
        if (job == null || mat == null) return 0;
        String path = getJobKey(job) + "." + mat.name();
        return config.getInt(path, 0);
    }

    // Ajoute un bloc
    public void addBlock(Job job, Material mat, int xp) {
        if (job == null || mat == null) return;
        String path = getJobKey(job) + "." + mat.name();
        config.set(path, xp);
        save();
    }

    // Supprime un bloc
    public void removeBlock(Job job, Material mat) {
        if (job == null || mat == null) return;
        String path = getJobKey(job) + "." + mat.name();
        config.set(path, null);
        save();
    }

    // Modifie l’XP d’un bloc
    public void addXp(Job job, Material mat, int amount) {
        if (job == null || mat == null) return;
        int current = getXp(job, mat);
        int newXp = Math.max(0, current + amount);
        String path = getJobKey(job) + "." + mat.name();
        config.set(path, newXp);
        save();
    }

    // Mode ajout via le chat
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
