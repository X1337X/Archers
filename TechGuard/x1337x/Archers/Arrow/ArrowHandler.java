package TechGuard.x1337x.Archers.Arrow;

import net.minecraft.server.EntityArrow;

import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftArrow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import TechGuard.x1337x.Archers.Properties;
/**
 * @author TechGuard
 */
public class ArrowHandler {
	public static short lastData = 0;

	public static void onArrowCreate(Player p, Arrow arrow){
		try{
		org.bukkit.entity.Arrow  ea = ((org.bukkit.entity.Arrow)arrow.getBukkitEntity());
		if(arrow.material == EnumBowMaterial.FIRE){
			ea.setFireTicks(300);
		}
		if(arrow.material == EnumBowMaterial.STANDARD){
			arrow.speed = (int)Properties.SPEED;
		}
		if(arrow.material == EnumBowMaterial.SNIPER){
			arrow.speed = 70;
		}
		arrow.world.addEntity((EntityArrow)arrow);
	}catch(Exception e){
		
	}
	}

	public static void onArrowDestroy(EntityDamageByProjectileEvent event){
		try{
		Arrow arrow = (Arrow)((CraftArrow)event.getProjectile()).getHandle();
		
		if(arrow.material == EnumBowMaterial.STANDARD) event.setDamage((int)Properties.DAMAGE);
		else event.setDamage(arrow.material.getDamageValue());
		arrow.destroy();

		if(arrow.material == EnumBowMaterial.FIRE){
			event.getEntity().setFireTicks(80);
		} else
		if(arrow.material == EnumBowMaterial.ZOMBIE){
			if(event.getEntity() instanceof Zombie){
					Zombie zombie = (Zombie)event.getEntity();
					Giant giant = (Giant)zombie.getWorld().spawnCreature(zombie.getLocation(), CreatureType.GIANT);
					giant.setHealth(zombie.getHealth());
					zombie.remove();
				} else
				if(event.getEntity() instanceof Giant){
					Giant giant = (Giant)event.getEntity();
					Zombie zombie = (Zombie)giant.getWorld().spawnCreature(giant.getLocation(), CreatureType.ZOMBIE);
					zombie.setHealth(giant.getHealth());
					giant.remove();
				}
		}
        if(arrow.material == EnumBowMaterial.PIG){
			if(event.getEntity() instanceof Pig){
				Pig pig = (Pig)event.getEntity();
				PigZombie pigman = (PigZombie) pig.getWorld().spawnCreature(pig.getLocation(), CreatureType.PIG_ZOMBIE);
				pigman.setHealth(pig.getHealth());
				pig.remove();
			}
			else if(event.getEntity() instanceof PigZombie){
				PigZombie pigman = (PigZombie)event.getEntity();
				Pig pig = (Pig) pigman.getWorld().spawnCreature(pigman.getLocation(), CreatureType.PIG);
				pig.setHealth(pigman.getHealth());
				pigman.remove();
			}
		}
        if(arrow.material == EnumBowMaterial.FLY){
        	org.bukkit.entity.LivingEntity entity = (org.bukkit.entity.LivingEntity)event.getEntity();
			entity.teleport(new Location(entity.getWorld(), entity.getLocation().getX(),entity.getLocation().getY()+20, entity.getLocation().getZ()));
		}
        if(arrow.material == EnumBowMaterial.STEAL){
        	if(event.getEntity() instanceof Player){
        		System.out.println("87");
        		Player p = (Player) event.getEntity();
        		ItemStack iih = p.getItemInHand();
        		Location loc = p.getLocation();        	
        		Location locnew = new Location(loc.getWorld(),loc.getX() + 10,loc.getY()+ 10,loc.getBlockZ());
        		p.getInventory().remove(iih);
        		loc.getWorld().dropItemNaturally(locnew, iih);
        		System.out.println("done");
        		}
        	
        }
if(arrow.material == EnumBowMaterial.SNIPER){
	event.setDamage(arrow.material.getDamageValue());	
		}
		}catch(Exception e){
			
		}
	}

}
