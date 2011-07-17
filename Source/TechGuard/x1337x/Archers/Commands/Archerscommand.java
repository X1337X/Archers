package TechGuard.x1337x.Archers.Commands;

import TechGuard.x1337x.Archers.Archers;
import com.nijiko.permissions.PermissionHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;

public class Archerscommand
  implements CommandExecutor
{
  Archers plugin;
  String cmd;

  public Archerscommand(Archers i)
  {
    this.plugin = i;
  }

  public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3)
  {
    Player player = (Player)arg0;

    this.cmd = arg3[0];

    if ((this.cmd.equalsIgnoreCase("help")) && (Archers.Permissions.has(player, "archers.commandhelp"))) {
      player.sendMessage("Archers Commands");
      player.sendMessage("/arrow [arrowname]");
      player.sendMessage("/archers [info]");
      player.sendMessage("/archers [disable]");
    }
    else if ((this.cmd.equalsIgnoreCase("info")) && (Archers.Permissions.has(player, "archers.info"))) {
      player.sendMessage("Archers info");
      player.sendMessage("Version = " + this.plugin.getDescription().getVersion());
      player.sendMessage("Made by = " + this.plugin.getDescription().getAuthors());
    }
    else if ((this.cmd.equalsIgnoreCase("disable")) && (Archers.Permissions.has(player, "archers.disable"))) {
      this.plugin.getPluginLoader().disablePlugin(this.plugin);
    }

    return true;
  }
}