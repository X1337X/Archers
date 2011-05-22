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
			String String0 = "";
			for(EnumBowMaterial bow : EnumBowMaterial.values()){
				String0 += ChatColor.YELLOW+bow.getName()+ChatColor.WHITE+", ";
	       	}
			p.sendMessage(String0.substring(0, String0.length()-4));
			p.sendMessage(ChatColor.DARK_GREEN+"To see more info about a arrow type: "+ChatColor.YELLOW+"/arrow [arrow name]");
		} else
		if(arg3.length >= 1){
			EnumBowMaterial arrow = EnumBowMaterial.fromName(arg3[0]);
			
			if(arrow == null){
				p.sendMessage(ChatColor.RED+"There is no Arrow matching that name.");
				String String1 = "";
				for(EnumBowMaterial bow : EnumBowMaterial.values()){
					String1 += ChatColor.YELLOW+bow.getName()+ChatColor.WHITE+", ";
		       	}
				p.sendMessage(String1.substring(0, String1.length()-4));
				return false;
			}
			p.sendMessage(ChatColor.DARK_GREEN+"To use a "+ChatColor.YELLOW+arrow.getName()+" Arrow"+ChatColor.DARK_GREEN+" it Costs:");
			String String0 = "";
			for(ItemStack item : Properties.ArrowAmmo.get(arrow.getDataValue())){
				String0 += ChatColor.YELLOW+""+item.getAmount()+" "+fix(item.getType().toString())+ChatColor.WHITE+", ";
	       	}
			p.sendMessage(String0.substring(0, String0.length()-4));
			p.sendMessage(ChatColor.DARK_GREEN+"To activate it, left click:");
			String String1 = "";
			for(Object object : arrow.getBlocks()){
				String1 += ChatColor.YELLOW+fix(((Material)object).toString())+ChatColor.WHITE+", ";
			}
			p.sendMessage(ChatColor.RED + "-----------------------");
			String string = arrow.getDescription();
		
			p.sendMessage(String1.substring(0, String1.length()-4));
			p.sendMessage(ChatColor.RED + "----------------------");
			p.sendMessage(ChatColor.YELLOW + string);
			p.sendMessage(ChatColor.RED+"-------------------------");
			int damage = arrow.getDamageValue();
			String dts = "Damage = " + damage;
			String hdamage = "health Damage = " + arrow.getDamage(arrow.getName());
			String speed = "Speed = " + arrow.getSpeed(arrow.getName());
			p.sendMessage(dts);
			p.sendMessage(hdamage);
			p.sendMessage(speed);
		}
		return true;
	}

	public String fix(String name){
		name = name.replaceAll("_", " ");
		name = name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
		return name;
	}
}