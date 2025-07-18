package sudark2.Sudark.atlas;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedList;
import java.util.Queue;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;
import static sudark2.Sudark.atlas.Atlas.BackWorld;
import static sudark2.Sudark.atlas.Atlas.preBlocks;

public class BlocksManager {

    public static void autoSave() {
        World world = Bukkit.getWorld(BackWorld);
        for (Location srcLoc : preBlocks) {
            BlockState srcState = srcLoc.getBlock().getState();

            Location targetLoc = new Location(world, srcLoc.getX(), srcLoc.getY(), srcLoc.getZ());

            BlockState copyState = srcState.copy(targetLoc);
            copyState.update(true, false);
        }
    }

    public static void save(Player pl, int range) {
        World targetWorld = Bukkit.getWorld(BackWorld);
        World world = pl.getWorld();
        Location plLoc = pl.getLocation();
        int x = plLoc.getBlockX();
        int y = plLoc.getBlockY();
        int z = plLoc.getBlockZ();
        for (int i = -range; i < range; i++) {
            for (int j = -range; j < range; j++) {
                for (int k = -range; k < range; k++) {
                    BlockState srcState = world.getBlockAt(x + i, y + j, z + k).getState();

                    Location targetLoc = new Location(targetWorld, x + i, y + j, z + k);

                    BlockState copyState = srcState.copy(targetLoc);
                    copyState.update(true, false);
                }
            }
        }
    }

    public static void load(Player pl, int range) {
        Queue<Location> temBlocks = new LinkedList<>();
        World origin = Bukkit.getWorld(BackWorld);
        World targetWorld = pl.getWorld();
        Location plLoc = pl.getLocation();
        int x = plLoc.getBlockX();
        int y = plLoc.getBlockY();
        int z = plLoc.getBlockZ();

        for (int i = -range; i < range; i++) {
            for (int j = -range; j < range; j++) {
                for (int k = -range; k < range; k++) {
                    temBlocks.add(new Location(origin, x + i, y + j, z + k));
                }
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 64; i++) {
                    Location targetLoc = temBlocks.poll();
                    if (targetLoc == null) break;
                    Block srcBlock = targetLoc.getBlock();
                    if (srcBlock.getType().equals(Material.AIR)) continue;

                    BlockState srcState = srcBlock.getState();
                    targetLoc.setWorld(targetWorld);

                    BlockState copyState = srcState.copy(targetLoc);
                    copyState.update(true, false);
                }
                if (temBlocks.isEmpty()) {
                    cancel();
                }
            }
        }.runTaskTimer(getPlugin(Atlas.class), 0, 1);
    }

}
