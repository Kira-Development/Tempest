package me.funky.hub.utils;

import me.funky.hub.Tempest;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskUtil {
    public TaskUtil() {
    }

    public static void run(Runnable runnable) {
        Tempest.getInstance().getServer().getScheduler().runTask(Tempest.getInstance(), runnable);
    }

    public static void runTimer(Runnable runnable, long delay, long timer) {
        Tempest.getInstance().getServer().getScheduler().runTaskTimer(Tempest.getInstance(), runnable, delay, timer);
    }

    public static void runTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(Tempest.getInstance(), delay, timer);
    }

    public static void runLater(Runnable runnable, long delay) {
        Tempest.getInstance().getServer().getScheduler().runTaskLater(Tempest.getInstance(), runnable, delay);
    }

    public static void runSync(Runnable runnable) {
        if (Bukkit.isPrimaryThread())
            runnable.run();
        else
            Bukkit.getScheduler().runTask(Tempest.getInstance(), runnable);
    }

    public static void runAsync(Runnable runnable) {
        if (Bukkit.isPrimaryThread())
            Bukkit.getScheduler().runTaskAsynchronously(Tempest.getInstance(), runnable);
        else
            runnable.run();
    }

    public static void runTimerAsync(Runnable runnable, long delay, long timer) {
        try {
            Tempest.getInstance().getServer().getScheduler().runTaskTimerAsynchronously(Tempest.getInstance(), runnable, delay, timer);
        } catch (IllegalStateException e) {
            Tempest.getInstance().getServer().getScheduler().runTaskTimer(Tempest.getInstance(), runnable, delay, timer);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void runTimerAsync(BukkitRunnable runnable, long delay, long timer) {
        Tempest.getInstance().getServer().getScheduler().runTaskTimerAsynchronously(Tempest.getInstance(), runnable, delay, timer);
    }
    public interface Callable {
        void call();
    }
}
