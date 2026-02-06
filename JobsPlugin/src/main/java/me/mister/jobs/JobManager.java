package me.mister.jobs;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JobManager {

    private final Map<UUID, Integer> xpMineur = new HashMap<>();
    private final Map<UUID, Integer> lvlMineur = new HashMap<>();

    private final Map<UUID, Integer> xpBucheron = new HashMap<>();
    private final Map<UUID, Integer> lvlBucheron = new HashMap<>();

    private final Map<UUID, Integer> xpFermier = new HashMap<>();
    private final Map<UUID, Integer> lvlFermier = new HashMap<>();

    public int getXp(Player p, String job) {
        return switch (job.toLowerCase()) {
            case "mineur" -> xpMineur.getOrDefault(p.getUniqueId(), 0);
            case "bucheron" -> xpBucheron.getOrDefault(p.getUniqueId(), 0);
            case "fermier" -> xpFermier.getOrDefault(p.getUniqueId(), 0);
            default -> 0;
        };
    }

    public int getLevel(Player p, String job) {
        return switch (job.toLowerCase()) {
            case "mineur" -> lvlMineur.getOrDefault(p.getUniqueId(), 1);
            case "bucheron" -> lvlBucheron.getOrDefault(p.getUniqueId(), 1);
            case "fermier" -> lvlFermier.getOrDefault(p.getUniqueId(), 1);
            default -> 1;
        };
    }

    public void addXp(Player p, String job, int amount) {
        UUID id = p.getUniqueId();

        switch (job.toLowerCase()) {

            case "mineur" -> {
                int xp = xpMineur.getOrDefault(id, 0) + amount;
                int lvl = lvlMineur.getOrDefault(id, 1);
                int needed = lvl * 100;

                if (xp >= needed) {
                    xp -= needed;
                    lvl++;
                    p.sendMessage("§aTon métier de Mineur passe niveau §e" + lvl);
                }

                xpMineur.put(id, xp);
                lvlMineur.put(id, lvl);
            }

            case "bucheron" -> {
                int xp = xpBucheron.getOrDefault(id, 0) + amount;
                int lvl = lvlBucheron.getOrDefault(id, 1);
                int needed = lvl * 100;

                if (xp >= needed) {
                    xp -= needed;
                    lvl++;
                    p.sendMessage("§aTon métier de Bûcheron passe niveau §e" + lvl);
                }

                xpBucheron.put(id, xp);
                lvlBucheron.put(id, lvl);
            }

            case "fermier" -> {
                int xp = xpFermier.getOrDefault(id, 0) + amount;
                int lvl = lvlFermier.getOrDefault(id, 1);
                int needed = lvl * 100;

                if (xp >= needed) {
                    xp -= needed;
                    lvl++;
                    p.sendMessage("§aTon métier de Fermier passe niveau §e" + lvl);
                }

                xpFermier.put(id, xp);
                lvlFermier.put(id, lvl);
            }
        }
    }
}
