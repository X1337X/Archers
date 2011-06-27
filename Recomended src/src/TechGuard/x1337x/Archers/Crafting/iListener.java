package TechGuard.x1337x.Archers.Crafting;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkitcontrib.event.inventory.InventoryCraftEvent;
import org.bukkitcontrib.event.inventory.InventoryListener;

import TechGuard.x1337x.Archers.Archers;

public class iListener extends InventoryListener {
public iListener(){

}

@Override
public void onInventoryCraft(InventoryCraftEvent event){
	ItemStack result = event.getResult();
	if(result != null){
	boolean bow = result.getType() == Material.BOW;
	if(bow){
	Player p = event.getPlayer();
	if(!Archers.Permissions.has(p, "archers.craft")){
		event.setResult(null);
		event.setCancelled(true);
		
		p.sendMessage("You dont have permission to craft a bow!");
	}
	}
}
}
}
