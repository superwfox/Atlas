package sudark2.Sudark.atlas;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import static sudark2.Sudark.atlas.Atlas.mainWorld;
import static sudark2.Sudark.atlas.Atlas.preBlocks;

public class AtlasListener implements Listener {

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        Location loc = event.getBlock().getLocation();
        if (loc.getWorld().equals(mainWorld)) preBlocks.add(loc);
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if (loc.getWorld().equals(mainWorld)) preBlocks.remove(loc);
    }

}
