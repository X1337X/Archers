package TechGuard.x1337x.Archers.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import TechGuard.x1337x.Archers.Archers;
import TechGuard.x1337x.Archers.Properties;
import TechGuard.x1337x.Archers.Arrow.EnumBowMaterial;

public class ArrowCommand implements CommandExecutor {
	Archers plugin;
	
	public ArrowCommand(Archers a){
		this.plugin = a;
	}

	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		Player p = (Player)arg0;
		if(arg3.length == 0){
			p.sendMessage(ChatColor.DARK_GREEN+"===> "+ChatColor.YELLOW+"Archer Arrows"+ChatColor.DARK_GREEN+" <===");
			for(EnumBowMaterial bow : EnumBowMaterial.values()){
	       		p.sendMessage("  "+ChatColor.YELLOW+bow.getName());
	       	}
			p.sendMessage(ChatColor.DARK_GREEN+"To see more info about the arrow: "+ChatColor.YELLOW+"/a [arrow name]");
		} else
		if(arg3.length >= 1){
			EnumBowMaterial arrow = EnumBowMaterial.fromName(arg3[0]);
			
			if(arrow == null){
				p.sendMessage(ChatColor.RED+"There is no Arrow matching that name.");
				return false;
			}
			p.sendMessage(ChatColor.DARK_GREEN+"To use a "+ChatColor.YELLOW+arrow.getName()+" Arrow"+ChatColor.YELLOW+" it Costs:");
			for(ItemStack item : Properties.ArrowAmmo.get(arrow.getDataValue())){
				p.sendMessage("  "+ChatColor.YELLOW+fix(item.getType().toString()));
			}
			p.sendMessage(ChatColor.DARK_GREEN+"To activate it, left click:");
			for(Object object : arrow.getBlocks()){
				p.sendMessage("  "+ChatColor.YELLOW+fix(((Material)object).toString()));
			}
		}
		return true;
	}

	public String fix(String name){
		name = name.replaceAll("_", " ");
		name = name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
		return name;
	}
}