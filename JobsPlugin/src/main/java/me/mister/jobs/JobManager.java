package me.mister.jobs;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JobManager {

    private final Map<UUID, Job> activeJob = new HashMap<>();

    private final Map<UUID, Integer> xpMineur = new HashMap<>();
    private final Map<UUID, Integer> lvlMineur = new HashMap<>();

    private final Map<UUID, Integer> xpBucheron = new HashMap<>();
    private final Map<UUID, Integer> lvlBucheron = new HashMap<>();

    private final Map<UUID, Integer> xpFermier = new HashMap<>();
    private final Map<UUID, Integer> lvlFermier = new HashMap<>();

    public void setJob(Player p, Job job) {
        activeJob.put(p.getUniqueId(), job);
        p.sendMessage("§aTu es maintenant §e" + job.name());
    }

    public Job getJob(Player p) {
        return activeJob.getOrDefault(p.getUniqueId(), null);
    }

    public int getXp(Player p, Job job) {
        return switch (job) {
            case MINEUR -> xpMineur.getOrDefault(p.getUniqueId(), 0);
            case BUCHERON -> xpBucheron.getOrDefault(p.getUniqueId(), 0);
            case FERMIER -> xpFermier.getOrDefault(p.getUniqueId(), 0);
        };
    }

    public int getLevel(Player p, Job job) {
        return switch (job) {
            case MINEUR -> lvlMineur.getOrDefault(p.getUniqueId(), 1);
            case BUCHERON -> lvlBucheron.getOrDefault(p.getUniqueId(), 1);
            case FERMIER -> lvlFermier.getOrDefault(p.getUniqueId(), 1);
        };
    }

    public void addXp(Player p, int amount) {
        Job job = getJob(p);
        if (job == null) return;

        UUID id = p.getUniqueId();

        switch (job) {

            case MINEUR -> {
                int xp = xpMineur.getOrDefault(id, 0) + amount;
                int lvl = lvlMineur.getOrDefault(id, 1);
                int needed = lvl * 100;

                if (xp >= needed) {
                    xp -= needed;
                    lvl++;
                    p.sendMessage("§aTon métier Mineur passe niveau §e" + lvl);
                }

                xpMineur.put(id, xp);
                lvlMineur.put(id, lvl);
            }

            case BUCHERON -> {
                int xp = xpBucheron.getOrDefault(id, 0) + amount;
                int lvl = lvlBucheron.getOrDefault(id, 1);
                int needed = lvl * 100;

                if (xp >= needed) {
                    xp -= needed;
                    lvl++;
                    p.sendMessage("§aTon métier Bûcheron passe niveau §e" + lvl);
                }

                xpBucheron.put(id, xp);
                lvlBucheron.put(id, lvl);
            }

            case FERMIER -> {
                int xp = xpFermier.getOrDefault(id, 0) + amount;
                int lvl = lvlFermier.getOrDefault(id, 1);
                int needed = lvl * 100;

                if (xp >= needed) {
                    xp -= needed;
                    lvl++;
                    p.sendMessage("§aTon métier Fermier passe niveau §e" + lvl);
                }

                xpFermier.put(id, xp);
                lvlFermier.put(id, lvl);
            }
        }
    }

    public void resetJob(Player p, Job job) {
        UUID id = p.getUniqueId();

        switch (job) {
            case MINEUR -> {
                xpMineur.put(id, 0);
                lvlMineur.put(id, 1);
            }
            case BUCHERON -> {
                xpBucheron.put(id, 0);
                lvlBucheron.put(id, 1);
            }
            case FERMIER -> {
                xpFermier.put(id, 0);
                lvlFermier.put(id, 1);
            }
        }

        p.sendMessage("§cTon métier §e" + job.name() + " §ca été réinitialisé !");
    }
}
