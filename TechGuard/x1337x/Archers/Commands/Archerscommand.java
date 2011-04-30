package TechGuard.x1337x.Archers.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import TechGuard.x1337x.Archers.Archers;

public class Archerscommand implements CommandExecutor {
Archers plugin;
String cmd;
public Archerscommand(Archers i){
	this.plugin = i;
}
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		Player player = (Player)arg0;
		// TODO Auto-generated method stub
		cmd = arg3[0];

		
		if(cmd.equalsIgnoreCase("help") && Archers.Permissions.has(player, "archers.commandhelp")){
			player.sendMessage("Archers Commands");
		    player.sendMessage("/arrow [arrowname]");
		    player.sendMessage("/archers [info]");
		    player.sendMessage("/archers [disable]");
			}

		
			
		else if(cmd.equalsIgnoreCase("info") && Archers.Permissions.has(player, "archers.info")){
				player.sendMessage("Archers info");
				player.sendMessage("Version = " + this.plugin.getDescription().getVersion());
				player.sendMessage("Made by = " + this.plugin.getDescription().getAuthors());
				}
				else if(cmd.equalsIgnoreCase("disable") && Archers.Permissions.has(player,"archers.disable")){
				    	this.plugin.getPluginLoader().disablePlugin(this.plugin);
				}
				
		
		
		return true;
	}

}
