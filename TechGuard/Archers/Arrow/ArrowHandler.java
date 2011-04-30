package TechGuard.Archers.Arrow;

import net.minecraft.server.EntityArrow;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.craftbukkit.entity.CraftArrow;
/**
 * @author ´TechGuard
 */
public class ArrowHandler {
	public static short lastData = 0;

	public static void onArrowCreate(Player p, Arrow arrow){
		org.bukkit.entity.Arrow  ea = ((org.bukkit.entity.Arrow)arrow.getBukkitEntity());
		if(arrow.material == EnumBowMaterial.FIRE){
			ea.setFireTicks(300);
		}
		arrow.world.addEntity((EntityArrow)arrow);
	}
	
	public static void onArrowDestroy(EntityDamageByProjectileEvent event){
		Arrow arrow = (Arrow)((CraftArrow)event.getProjectile()).getHandle();
		
		event.setDamage(arrow.material.getDamageValue());
		arrow.destroy();
		
		if(arrow.material == EnumBowMaterial.FIRE){
			event.getEntity().setFireTicks(80);
		}
	}
}
