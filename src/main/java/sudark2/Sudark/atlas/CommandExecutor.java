package sudark2.Sudark.atlas;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {

        if (sender instanceof Player pl) {
            if (!pl.isOp()) return false;

            switch (args[0]) {
                case "save" -> BlocksManager.save(pl, Integer.valueOf(args[1]));
                case "load" -> BlocksManager.load(pl, Integer.valueOf(args[1]));
            }
            return true;
        }
        return false;
    }
}

