package me.darkeyedragon.queueapi.queue;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Queue {

    private final int maxSize;
    private final long countDownTime;
    private Set<Player> playerList = new HashSet<>();
    private JavaPlugin javaPlugin;

    /**
     * @param maxSize The maximum amount of players allowed in the queue
     * @param javaPlugin the {@link JavaPlugin} instance
     * @param countdownTime countdown timer in seconds
     */
    public Queue(int maxSize, JavaPlugin javaPlugin, long countdownTime) {
        this.maxSize = maxSize;
        this.javaPlugin = javaPlugin;
        this.countDownTime =countdownTime;
    }

    public Queue(JavaPlugin javaPlugin, long time) {
        this(-1, javaPlugin, time);
    }

    public void add(Player player) throws MaxSizeExceeded {
        if(maxSize != -1 && playerList.size()+1 > maxSize) throw new MaxSizeExceeded();
        playerList.add(player);
    }

    public boolean contains(Player player){
        return playerList.contains(player);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public Set<Player> getPlayers() {
        return playerList;
    }

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public long getCountDownTime() {
        return countDownTime;
    }

    public boolean hasMaxSize(){
        return maxSize > 0;
    }
}
