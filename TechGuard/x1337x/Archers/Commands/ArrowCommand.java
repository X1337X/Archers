package TechGuard.x1337x.Archers.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import TechGuard.x1337x.Archers.Archers;
import TechGuard.x1337x.Archers.Arrow.EnumBowMaterial;

public class ArrowCommand implements CommandExecutor {
Archers plugin;
public ArrowCommand(Archers a){
	this.plugin = a;
}
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		Player player = (Player)arg0;
		if(arg3.length == 0){
		player.sendMessage("Archer: The list of arrows are");
		for(EnumBowMaterial bow : EnumBowMaterial.values()){
       		player.sendMessage("  "+bow.getName());
       	}
		player.sendMessage("to get what is costs to use a arrow use /a [bowname]");
		}	
		else if(arg3.length == 1){
			String bowname = arg3[1].toString();
			if(bowname.equalsIgnoreCase("Standard")){
				player.sendMessage("To use a Standard arrow it costs");
				player.sendMessage("1 Arrow");
				player.sendMessage("and to activate it you need 1 log or 1 plank");
			}
			else if(bowname.equalsIgnoreCase("Ice")){
				player.sendMessage("To use a Ice arrow it costs");
				player.sendMessage("1 Arrow and one snowball");
				player.sendMessage("and to activate it you need 1 snow");
			}
			else if(bowname.equalsIgnoreCase("Fire")){
				player.sendMessage("To use a Fire arrow it costs");
				player.sendMessage("1 Arrow and one coal");
				player.sendMessage("and to activate it you need one fire");
			}
			else if(bowname.equalsIgnoreCase("TNT")){
				player.sendMessage("To use a TNT arrow it costs");
				player.sendMessage("1 Arrow and one gunpowder(sulpher");
				player.sendMessage("and to activate it you need one TNT block");
			}
			else if(bowname.equalsIgnoreCase("Thunder")){
				player.sendMessage("To use a Thunder arrow it costs");
				player.sendMessage("1 Arrow and one redstone dust");
				player.sendMessage("and to activate it you need one redstone ore");
			}
			else if(bowname.equalsIgnoreCase("Monster")){
				player.sendMessage("To use a Monster arrow it costs");
				player.sendMessage("1 Arrow and one bone");
				player.sendMessage("and to activate it you need one mob spawner");
			}
			else if(bowname.equalsIgnoreCase("Thrice")){
				player.sendMessage("To use a Thrice arrow it costs");
				player.sendMessage("3 Arrows");
				player.sendMessage("and to activate it you need one dispener");
			}
			else if(bowname.equalsIgnoreCase("Zombie")){
				player.sendMessage("To use a Zombie arrow it costs");
				player.sendMessage("1 Arrow and one seed");
				player.sendMessage("and to activate it you need one lapis block");
			}
			else if(bowname.equalsIgnoreCase("Tree")){
				player.sendMessage("To use a Tree arrow it costs");
				player.sendMessage("1 Arrow and one sapling");
				player.sendMessage("and to activate it you need one sapling");
			}
			else if(bowname.equalsIgnoreCase("Pig")){
				player.sendMessage("To use a Pig arrow it costs");
				player.sendMessage("1 Arrow and one cooked pork");
				player.sendMessage("and to activate it you need one grass block");
			}
			else if(bowname.equalsIgnoreCase("Zeus")){
				player.sendMessage("To use a Zeus arrow it costs");
				player.sendMessage("1 Arrow and two Diamond");
				player.sendMessage("and to activate it you need one Diamond block");
			}
			else{
				player.sendMessage("There is no bow matching that name!");
			}
		}
		return true;
	}

}
