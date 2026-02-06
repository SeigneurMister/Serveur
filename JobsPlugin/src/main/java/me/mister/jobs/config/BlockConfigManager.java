package me.mister.jobs.config;

import me.mister.jobs.Job;
import me.mister.jobs.JobsPlugin;
import org.bukkit.Material;
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

    private void load() {
        file = new File(plugin.getDataFolder(), "blocks.yml");

        if (!file.exists()) {
            plugin.saveResource("blocks.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Récupère la liste des blocs d’un métier
    public List<String> getBlocks(Job job) {
        return new ArrayList<>(config.getConfigurationSection(job.name()).getKeys(false));
    }

    // Récupère l’XP d’un bloc
    public int getXp(Job job, Material mat) {
        return config.getInt(job.name() + "." + mat.name(), 0);
    }

    // Ajoute un bloc
    public void addBlock(Job job, Material mat, int xp) {
        config.set(job.name() + "." + mat.name(), xp);
        save();
    }

    // Supprime un bloc
    public void removeBlock(Job job, Material mat) {
        config.set(job.name() + "." + mat.name(), null);
        save();
    }

    // Modifie l’XP d’un bloc
    public void addXp(Job job, Material mat, int amount) {
        int current = getXp(job, mat);
        int newXp = Math.max(0, current + amount);
        config.set(job.name() + "." + mat.name(), newXp);
        save();
    }

    // Active le mode ajout via le chat
    public void startAddMode(Player p, Job job) {
        pendingAdd.put(p.getUniqueId(), job);
    }

    // Vérifie si un joueur est en mode ajout
    public boolean isInAddMode(Player p) {
        return pendingAdd.containsKey(p.getUniqueId());
    }

    // Récupère le métier en mode ajout
    public Job getPendingJob(Player p) {
        return pendingAdd.get(p.getUniqueId());
    }

    // Termine le mode ajout
    public void finishAddMode(Player p) {
        pendingAdd.remove(p.getUniqueId());
    }
}
