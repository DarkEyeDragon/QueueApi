package me.darkeyedragon.queueapi.queue;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class GameQueue implements Queue {

    private final int maxSize;
    private final long countdownTime;
    private final Set<Player> playerList = new HashSet<>();
    private final JavaPlugin javaPlugin;

    /**
     * Creates a Queue with a max size and countdown timer.
     * @param maxSize The maximum amount of players allowed in the queue
     * @param javaPlugin The {@link JavaPlugin} instance
     * @param countdownTime The countdown timer in seconds
     */
    public GameQueue(int maxSize, JavaPlugin javaPlugin, long countdownTime) {
        this.maxSize = maxSize;
        this.javaPlugin = javaPlugin;
        this.countdownTime = countdownTime;
    }

    /**
     * Creates an unbounded Queue with a countdown timer.
     * @param javaPlugin The {@link JavaPlugin} instance
     * @param countdownTime The countdown timer in seconds
     */
    public GameQueue(JavaPlugin javaPlugin, long countdownTime) {
        this(-1, javaPlugin, countdownTime);
    }

    @Override
    public void add(Player player) throws MaxSizeExceeded {
        if (maxSize != -1 && playerList.size() + 1 > maxSize) throw new MaxSizeExceeded(maxSize);
        playerList.add(player);
    }

    @Override
    public void remove(Player player) {
        playerList.remove(player);
    }

    @Override
    public boolean contains(Player player){
        return playerList.contains(player);
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public boolean hasMaxSize(){
        return maxSize > 0;
    }

    @Override
    public Set<Player> getPlayers() {
        return playerList;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    @Override
    public long getCountdownTime() {
        return countdownTime;
    }
}
