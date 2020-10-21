package me.darkeyedragon.queueapi.queue;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

/**
 * A game queue, with an optional maximum player limit. This is not a conventional "Queue", it is unordered
 * and is essentially a managed Set of players. It is used directly by {@link GameScheduleHandler}.
 */
public interface Queue {
    /**
     * Adds a {@link Player} to the queue.
     * @param player The {@link Player} to add
     * @throws MaxSizeExceeded If adding the player would exceed the maximum size
     */
    void add(Player player) throws MaxSizeExceeded;

    /**
     * Removes a given {@link Player} from the queue.
     * @param player The {@link Player} to remove
     */
    void remove(Player player);

    /**
     * Checks if the queue contains the given {@link Player}.
     * @param player The {@link Player} to check
     * @return If the queue contains the given {@link Player}
     */
    boolean contains(Player player);

    /**
     * Gets the maximum size of the queue set in the constructor, returning -1 for no upper bound.
     * @return The maximum size of the queue
     */
    int getMaxSize();

    /**
     * Checks if a maximum size was set in the constructor.
     * @return If a maximum size has been set
     */
    boolean hasMaxSize();

    /**
     * Gets all {@link Player}s in the queue.
     * @return A set of {@link Player}s
     */
    Set<Player> getPlayers();

    /**
     * Gets the {@link JavaPlugin} used.
     * @return The {@link JavaPlugin}
     */
    JavaPlugin getJavaPlugin();

    /**
     * Gets the countdown time (set in the constructor) in seconds.
     * @return The countdown time
     */
    long getCountdownTime();
}
