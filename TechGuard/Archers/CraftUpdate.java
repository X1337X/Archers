package TechGuard.Archers;

import org.bukkit.craftbukkit.inventory.CraftInventoryPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
/**
 * @author ´TechGuard
 */
public class CraftUpdate {
	
	public static void addItem(Player p, ItemStack q){
		CraftInventoryPlayer pInv = (CraftInventoryPlayer)p.getInventory();
		
		pInv.addItem(q);
		p.updateInventory();
	}
	public static void removeItem(Player p, ItemStack q){
		CraftInventoryPlayer pInv = (CraftInventoryPlayer)p.getInventory();
		
		for(int i = 0; i < q.getAmount(); i++){
			if(pInv.getItem(pInv.first(q.getTypeId())).getAmount() == 1){
				pInv.remove(new ItemStack(q.getTypeId(), 1));
			} else {
				ItemStack first = pInv.getItem(pInv.first(q.getTypeId()));
				pInv.setItem(pInv.first(q.getTypeId()), new ItemStack(q.getTypeId(), first.getAmount()-1));
			}
		}
		p.updateInventory();
	}
}
