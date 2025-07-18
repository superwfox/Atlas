package sudark2.Sudark.atlas;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static sudark2.Sudark.atlas.BlocksManager.autoSave;

public class Clock {

    public static void main(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).getHour() == 4) {
                    autoSave();
                }
            }
        }.runTaskTimer(plugin, 0, 20 * 60 * 60L);
    }
}
