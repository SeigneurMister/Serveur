package me.mister.jobs;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JobManager {

    private final Map<UUID, Job> activeJob = new HashMap<>();

    private final Map<UUID, Integer> xp = new HashMap<>();
    private final Map<UUID, Integer> level = new HashMap<>();

    public void setJob(Player p, Job job) {
        activeJob.put(p.getUniqueId(), job);
        p.sendMessage("§aTu es maintenant §e" + job.name());
    }

    public Job getJob(Player p) {
        return activeJob.getOrDefault(p.getUniqueId(), null);
    }

    public void addXp(Player p, int amount) {
        UUID id = p.getUniqueId();

        int currentXp = xp.getOrDefault(id, 0) + amount;
        int lvl = level.getOrDefault(id, 1);
        int needed = lvl * 100;

        if (currentXp >= needed) {
            currentXp -= needed;
            lvl++;
            p.sendMessage("§aTu montes niveau §e" + lvl + " §apour ton métier !");
        }

        xp.put(id, currentXp);
        level.put(id, lvl);
    }

    public int getXp(Player p) {
        return xp.getOrDefault(p.getUniqueId(), 0);
    }

    public int getLevel(Player p) {
        return level.getOrDefault(p.getUniqueId(), 1);
    }
}
