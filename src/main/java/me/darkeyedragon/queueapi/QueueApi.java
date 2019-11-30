package me.darkeyedragon.queueapi;

import me.darkeyedragon.queueapi.queue.MaxSizeExceeded;
import me.darkeyedragon.queueapi.queue.Queue;
import me.darkeyedragon.queueapi.queue.ScheduleHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class QueueApi extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Queue queue = new Queue(this, 65);
        try {
            queue.add(Bukkit.getPlayer("DarkEyeDragon"));
        } catch (MaxSizeExceeded maxSizeExceeded) {
            maxSizeExceeded.printStackTrace();
        }
        ScheduleHandler handler = new ScheduleHandler(queue);
        handler.startCountDown();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
