package me.darkeyedragon.queueapi.queue;

import org.bukkit.Bukkit;
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
            if (currentCountDown % 60 == 0) {
                for (Player player : queue.getPlayerList()) {
                    player.sendMessage("Yeet " + currentCountDown / 60);
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
