package TechGuard.x1337x.Archers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

import TechGuard.x1337x.Archers.Arrow.Arrow;
import TechGuard.x1337x.Archers.Arrow.ArrowHandler;
import TechGuard.x1337x.Archers.Arrow.EnumBowMaterial;
import TechGuard.x1337x.Archers.Crafting.CraftHandler;
/**
 * @author TechGuard
 */
public class pListener extends PlayerListener{
//CraftHandler c = this.plugin.c;
Archers plugin;

	public void onItemHeldChange(PlayerItemHeldEvent event){
		Player p = event.getPlayer();
		ItemStack item = p.getInventory().getContents()[event.getNewSlot()];
		
		if(item == null){
			return;
		}
		
		if(item.getType() == Material.BOW){
			p.sendMessage(ChatColor.DARK_GREEN+"Bow Material: "+ChatColor.YELLOW+EnumBowMaterial.fromData(item.getDurability()).getName());
		}
	}
	
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getAction() == Action.LEFT_CLICK_BLOCK){
			Player p = event.getPlayer();
			ItemStack item = p.getItemInHand();
			if(item.getType() == Material.BOW){
				Material id = event.getClickedBlock().getType();
				Material id2 = event.getClickedBlock().getRelative(0, 1, 0).getType();
				EnumBowMaterial bm = null;
				
				for(EnumBowMaterial material : EnumBowMaterial.values()){
					for(Object object : material.getBlocks()){
						if(id == (Material)object || id2 == (Material)object){
							bm = material;
						}
					}
				}

				if(bm != null){
					if(bm.getDataValue() == EnumBowMaterial.fromData(item.getDurability()).getDataValue()){//Less spam
						return;
					}
					item.setDurability(bm.getDataValue());
					p.sendMessage(ChatColor.DARK_GREEN+"Changed Bow Material to "+ChatColor.YELLOW+bm.getName()+ChatColor.DARK_GREEN+".");
				}
			}
		}
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(event.getClickedBlock().getType() == Material.WORKBENCH){
				event.getPlayer().sendMessage("Running crafthread");
			//	c.run(event.getPlayer(),this.plugin);
			}
		}
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			Player p = event.getPlayer();
			ItemStack item = p.getItemInHand();
			if(item.getType() == Material.BOW){
				event.setUseInteractedBlock(Result.DENY);
				event.setCancelled(true);
				EnumBowMaterial material = EnumBowMaterial.fromData(item.getDurability());
				
				if(!Archers.Permissions.has(p, "archers.bow."+material.getName().toLowerCase())){
					event.setCancelled(true);
					return;
				}
				
				int has = 0;
				for(ItemStack stack : Properties.ArrowAmmo.get(material.getDataValue())){
					int amount = 0;
					for(int i = 0; i < p.getInventory().getContents().length; i++){
						if(p.getInventory().getContents()[i] == null){
							continue;
						}
						if(p.getInventory().getContents()[i].getTypeId() == stack.getTypeId()){
							amount += p.getInventory().getContents()[i].getAmount();
						}
						if(amount >= stack.getAmount()){
							++has;
							break;
						}
					}
				}
				if(has != Properties.ArrowAmmo.get(material.getDataValue()).size()){
					p.sendMessage(ChatColor.RED+"You don't have enough ammo!");
					return;
				}
				
				if(material == EnumBowMaterial.THRICE){
                    for(int i = 0; i <= 2; i++){
				        Arrow arrow = new Arrow(p.getWorld(), p, EnumBowMaterial.THRICE, i);
				        ArrowHandler.onArrowCreate(p, arrow);
                    }
					Arrow arrow = new Arrow(p.getWorld(), p, EnumBowMaterial.STANDARD);
					ArrowHandler.onArrowCreate(p, arrow);
				} else {
					Arrow arrow = new Arrow(p.getWorld(), p, material);
					ArrowHandler.onArrowCreate(p, arrow);
			}
			/*	
				if(material == EnumBowMaterial.MINIGUN){
                    for(int i = 0; i <= 2; i++){
				        Arrow arrow = new Arrow(p.getWorld(), p, EnumBowMaterial.THRICE, i);
				        ArrowHandler.onArrowCreate(p, arrow);
                    }
					Arrow arrow = new Arrow(p.getWorld(), p, EnumBowMaterial.STANDARD);
					ArrowHandler.onArrowCreate(p, arrow);
				} else {
					Arrow arrow = new Arrow(p.getWorld(), p, material);
					ArrowHandler.onArrowCreate(p, arrow);
				}
				*/
				for(ItemStack stack : Properties.ArrowAmmo.get(material.getDataValue())){
					CraftUpdate.removeItem(p, stack);
				}
			}
		}
	}
}
