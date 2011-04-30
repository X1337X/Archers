package TechGuard.Archers.Arrow;

import java.util.Random;

import net.minecraft.server.EntityArrow;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntitySkeleton;
import net.minecraft.server.EntityTNTPrimed;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
/**
 * @author ´TechGuard
 */
public class Arrow extends EntityArrow{
	public EnumBowMaterial material;
	private int moving = 0;
	
	public Arrow(World world, LivingEntity entityliving, EnumBowMaterial material) {
		super(((CraftWorld)world).getHandle(), ((CraftLivingEntity)entityliving).getHandle());	
		this.material = material;
	}

	public void p_() {
		super.p_();
	    
	    if(lastX == locX && lastY== locY && lastZ == locZ && moving == 0){
			moving = 1;
		}
		if(moving == 1){
			destroy();
			moving = 2;
		}
	}
	
	public void destroy(){
		if(material == EnumBowMaterial.ICE){
			int radius = 3;
			int radiusSq = (int)Math.pow(radius, 2.0D);
			World world = getBukkitEntity().getWorld();

		    for(int x = getBukkitEntity().getLocation().getBlockX()-radius; x <= getBukkitEntity().getLocation().getBlockX()+radius; x++){
			  for(int z = getBukkitEntity().getLocation().getBlockZ()-radius; z <= getBukkitEntity().getLocation().getBlockZ()+radius; z++){
				  if(new Vector(x, getBukkitEntity().getLocation().getBlockY(), z).distanceSquared(new Vector(
				  getBukkitEntity().getLocation().getX(),getBukkitEntity().getLocation().getY(), getBukkitEntity().getLocation().getZ())) > radiusSq){
					  continue;
			      }
				  if((new Random()).nextInt(4) > 0){
					  continue;
				  }
				  for(int y = getBukkitEntity().getLocation().getBlockY()+radius; y >= getBukkitEntity().getLocation().getBlockY()-radius; y--){
					  int id = world.getBlockTypeIdAt(x, y, z);
					  if ((id == 6) || (id == 10) || (id == 11) || (id == 37) || (id == 38) || (id == 39) || (id == 40) || (id == 44) ||
					  (id == 50) || (id == 51) || (id == 53) || (id == 55) || (id == 59) || ((id >= 63) && (id <= 72)) || (id == 75) ||
					  (id == 76) || (id == 77) || (id == 78) || (id == 79) || (id == 81) || (id == 83) || (id == 85) || (id == 90)){
						  break;
					  }
					  if ((id == 8) || (id == 9)) {
						  world.getBlockAt(x, y, z).setTypeId(79);
						  break;
				      }
					  if (id != 0) {
						  if (y == 127){
							  break;
						  }
						  world.getBlockAt(x, y+1, z).setTypeId(78);
						  break;
					  }
				  }
			  }
			}
		} else
		if(material == EnumBowMaterial.FIRE){
			World world = getBukkitEntity().getWorld();
			world.getBlockAt((int)locX, (int)locY, (int)locZ).setType(Material.FIRE);
		} else
		if(material == EnumBowMaterial.TNT){
			EntityTNTPrimed tnt = new EntityTNTPrimed(this.world, locX, locY, locZ);
			
			tnt.a = 0;
			world.addEntity(tnt);
			tnt.f_();
		} else
		if(material == EnumBowMaterial.THUNDER){
			World world = getBukkitEntity().getWorld();
			world.strikeLightning(new Location(world, locX, locY, locZ));
		}
		else if(material == EnumBowMaterial.SKELTION){
			World world = getBukkitEntity().getWorld();
			CreatureType s = CreatureType.SKELETON;
			world.spawnCreature(getBukkitEntity().getLocation(), s);
		}
	}
	
	public void b(EntityHuman entityhuman) {
		if ((!this.world.isStatic) && (this.shooter == entityhuman) && moving==2 && (entityhuman.inventory.canHold(new ItemStack(Item.ARROW, 1)))) {
			this.world.makeSound(this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			entityhuman.receive(this, 1);
			die();
		}
	}
}