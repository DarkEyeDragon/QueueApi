package me.darkeyedragon.queueapi.queue;

import org.bukkit.scheduler.BukkitTask;

import java.util.Optional;

/**
 * Handles scheduling for displaying a countdown to a {@link Queue} of players.
 */
public interface ScheduleHandler {
    /**
     * Starts the scheduled countdown.
     */
    void startCountdown();

    /**
     * Cancels the timer, if already running.
     */
    void cancelTimer();

    /**
     * Checks if the countdown has been started {@link #startCountdown()} and is actively running.
     * @return If the countdown is running
     */
    boolean isRunning();

    /**
     * Gets the created {@link Queue} being used.
     * @return The {@link Queue}
     */
    Queue getQueue();

    /**
     * Gets the {@link BukkitTask} containing the countdown. The {@link Optional} will be empty if the task is not
     * running.
     * @return An {@link Optional} of the countdown {@link BukkitTask}, if running
     */
    Optional<BukkitTask> getCountdownTask();

    /**
     * Gets the current remaining seconds of the countdown.
     * @return The remaining seconds of the countdown
     */
    long getCurrentCountdown();
}
