package me.darkeyedragon.queueapi.queue;

import me.darkeyedragon.queueapi.QueueApi;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Queue {

    private final int maxSize;
    private final long countDownTime;
    private Set<Player> playerList = new HashSet<>();
    private QueueApi queueApi;

    /**
     * @param maxSize The maximum amount of players allowed in the queue
     * @param queueApi the {@link QueueApi} instance
     * @param countdownTime countdown timer in seconds
     */
    public Queue(int maxSize, QueueApi queueApi, long countdownTime) {
        this.maxSize = maxSize;
        this.queueApi = queueApi;
        this.countDownTime =countdownTime;
    }

    public Queue(QueueApi queueApi, long time) {
        this(-1, queueApi, time);
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

    public Set<Player> getPlayerList() {
        return playerList;
    }

    public QueueApi getQueueApi() {
        return queueApi;
    }

    public long getCountDownTime() {
        return countDownTime;
    }

    public boolean hasMaxSize(){
        return maxSize > 0;
    }
}
