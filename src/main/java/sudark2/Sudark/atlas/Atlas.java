package sudark2.Sudark.atlas;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class Atlas extends JavaPlugin {

    public static Set<Location> preBlocks = new HashSet<>();
    static String BackWorld = "BEEF-Atlas";
    static World mainWorld = Bukkit.getWorld("BEEF-DUNE");

    @Override
    public void onEnable() {
        CreateWorld.createVoidWorld(BackWorld);
        Bukkit.getPluginManager().registerEvents(new AtlasListener(), this);
        Bukkit.getPluginCommand("atlas").setExecutor(new CommandExecutor());
        Clock.main(this);
    }


}
