package peace.minecraftserver.utils;


import java.util.ArrayList;
import java.util.List;
import org.bukkit.OfflinePlayer;

public class TimerManager {
    private List<Timer> timer = new ArrayList<>();

    public List<Timer> getTimer() {
        return this.timer;
    }

    public Timer getTimer(OfflinePlayer p) {
        for (Timer timer : this.timer) {
            if (timer.getPlayer().equals(p))
                return timer;
        }
        return null;
    }

    public void registerTimer(Timer timer) {
        if (!this.timer.contains(timer))
            this.timer.add(timer);
    }

    public void unregister(Timer timer) {
        if (this.timer.contains(timer))
            this.timer.remove(timer);
    }

    public void saveAll() {
        for (Timer timer : this.timer)
            timer.save();
    }
}
