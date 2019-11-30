package me.darkeyedragon.queueapi.queue;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class ScheduleHandler {

    private final Queue queue;
    private boolean isRunning;
    private BukkitTask bukkitTask;
    private long currentCountDown;

    public ScheduleHandler(Queue queue) {
        this.queue = queue;
        this.currentCountDown = queue.getCountDownTime();
    }

    public void startCountDown() {
        isRunning = true;
        bukkitTask = Bukkit.getServer().getScheduler().runTaskTimer(queue.getQueueApi(), () -> {
            String message = null;
            if (currentCountDown == 0) {
                message = ChatColor.GREEN + "" + ChatColor.BOLD + "Game is starting!";
            } else if ((currentCountDown < 60 && currentCountDown % 20 == 0) || (currentCountDown <= 5)) {
                message = ChatColor.YELLOW + "" + ChatColor.BOLD + "Game starts in " + currentCountDown + " seconds";
            } else if (currentCountDown % 60 == 0) {
                if (currentCountDown != 60) {
                    message = ChatColor.YELLOW + "" + ChatColor.BOLD + "Game starts in " + currentCountDown / 60 + " minutes";
                } else {
                    message = ChatColor.YELLOW + "" + ChatColor.BOLD + "Game starts in " + currentCountDown / 60 + " minute";
                }
            }

            if (message != null) {
                for (Player player : queue.getPlayers()) {
                    player.sendMessage(message);
                }
            }

            if (currentCountDown <= 0) {
                cancelTimer();
            }
            currentCountDown--;
        }, 0, 20);
    }

    public void cancelTimer() {
        bukkitTask.cancel();
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Queue getQueue() {
        return queue;
    }

    public BukkitTask getBukkitTask() {
        return bukkitTask;
    }

    public long getCurrentCountDown() {
        return currentCountDown;
    }
}
