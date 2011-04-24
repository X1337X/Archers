package TechGuard.x1337x.Archers.Arrow;

import net.minecraft.server.EntityArrow;

import org.bukkit.craftbukkit.entity.CraftArrow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
/**
 * @author TechGuard
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
		} else
		if(arrow.material == EnumBowMaterial.ZOMBIE){
			if(event.getEntity() instanceof Zombie){
				Zombie zombie = (Zombie)event.getEntity();
				zombie.getWorld().spawnCreature(zombie.getLocation(), CreatureType.GIANT);
				zombie.remove();
			}
		}
	}
}
