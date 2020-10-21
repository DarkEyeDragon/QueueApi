package me.darkeyedragon.queueapi.queue;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.Optional;

public class GameScheduleHandler implements ScheduleHandler {

    private final Queue queue;
    private long currentCountdown;
    private BukkitTask countdownTask;

    public GameScheduleHandler(Queue queue) {
        this.queue = queue;
        this.currentCountdown = queue.getCountdownTime();
    }

    @Override
    public void startCountdown() {
        countdownTask = Bukkit.getServer().getScheduler().runTaskTimer(queue.getJavaPlugin(), () -> {
            String message = null;
            if (currentCountdown == 0) {
                message = ChatColor.GREEN + "" + ChatColor.BOLD + "Game is starting!";
            } else if ((currentCountdown < 60 && currentCountdown % 20 == 0) || (currentCountdown <= 5)) {
                message = ChatColor.YELLOW + "" + ChatColor.BOLD + "Game starts in " + currentCountdown + " seconds";
            } else if (currentCountdown % 60 == 0) {
                if (currentCountdown != 60) {
                    message = ChatColor.YELLOW + "" + ChatColor.BOLD + "Game starts in " + currentCountdown / 60 + " minutes";
                } else {
                    message = ChatColor.YELLOW + "" + ChatColor.BOLD + "Game starts in " + currentCountdown / 60 + " minute";
                }
            }

            if (message != null) {
                for (Player player : queue.getPlayers()) {
                    player.sendMessage(message);
                }
            }

            if (currentCountdown <= 0) {
                cancelTimer();
            }
            currentCountdown--;
        }, 0, 20);
    }

    @Override
    public void cancelTimer() {
        if (countdownTask != null) {
            countdownTask.cancel();
            countdownTask = null;
            currentCountdown = queue.getCountdownTime();
        }
    }

    @Override
    public boolean isRunning() {
        return countdownTask != null;
    }

    @Override
    public Queue getQueue() {
        return queue;
    }

    @Override
    public Optional<BukkitTask> getCountdownTask() {
        return Optional.ofNullable(countdownTask);
    }

    @Override
    public long getCurrentCountdown() {
        return currentCountdown;
    }
}
