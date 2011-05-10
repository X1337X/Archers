package TechGuard.x1337x.Archers.Arrow;

import java.util.Random;

import net.minecraft.server.EntityArrow;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityTNTPrimed;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MathHelper;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
/**
 * @author TechGuard
 */
public class Arrow extends EntityArrow{
	public EnumBowMaterial material;
	public int speed = 0;
	
	private int moving = 0;
	private double firstY = 123;

	public Arrow(World world, LivingEntity entityliving, EnumBowMaterial material) {
		super(((CraftWorld)world).getHandle(), ((CraftLivingEntity)entityliving).getHandle());	
		this.material = material;
	}

        public Arrow(World w, LivingEntity el, EnumBowMaterial material, int thrice) {
		super(((CraftWorld)w).getHandle());
		this.material = material;
		EntityLiving entityliving = ((CraftLivingEntity)el).getHandle();

	    this.shooter = entityliving;
	    b(0.5F, 0.5F);
	    int int0 = 0;
	    if(thrice==0) int0 = -10;
	    if(thrice==1) int0 = 10;
	    setPositionRotation(entityliving.locX, entityliving.locY + entityliving.s(), entityliving.locZ, entityliving.yaw+int0, entityliving.pitch);
	    this.locX -= MathHelper.cos(this.yaw / 180.0F * 3.141593F) * 0.16F;
	    this.locY -= 0.1000000014901161D;
	    this.locZ -= MathHelper.sin(this.yaw / 180.0F * 3.141593F) * 0.16F;
	    setPosition(this.locX, this.locY, this.locZ);
	    this.height = 0.0F;
	    this.motX = (-MathHelper.sin(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F));
	    this.motZ = (MathHelper.cos(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F));
	    this.motY = (-MathHelper.sin(this.pitch / 180.0F * 3.141593F));
	    a(this.motX, this.motY, this.motZ, 1.5F, 1.0F);
	}

	public void p_() {
		super.p_();

		if(firstY == 123) firstY = motY;
		if(speed > 0){
			motY = firstY;
			speed--;
		}

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
		}else
                if(material == EnumBowMaterial.MONSTER){
			World world = getBukkitEntity().getWorld();
			CreatureType[] types = { CreatureType.CREEPER, CreatureType.SKELETON, CreatureType.SLIME, CreatureType.SPIDER, CreatureType.ZOMBIE };
			world.spawnCreature(getBukkitEntity().getLocation(), types[(new Random()).nextInt(5)]);
		}
                else if(material == EnumBowMaterial.TREE){
                	World world = getBukkitEntity().getWorld();
                	Location loc = getBukkitEntity().getLocation();
                   world.generateTree(loc, TreeType.TREE);
                   
                }
                else if(material == EnumBowMaterial.ZEUS){
                	Location loc = getBukkitEntity().getLocation();
                	World worldf = loc.getWorld();
                	worldf.strikeLightning(loc);
                	loc.getBlock().setType(Material.FIRE);
                	EntityTNTPrimed tnt = new EntityTNTPrimed(this.world, locX, locY, locZ);

        			tnt.a = 0;
        			world.addEntity(tnt);
        			tnt.f_();
                }
                else if(material == EnumBowMaterial.TP){
                	if(shooter.getBukkitEntity() instanceof Player){
                		Player p = ((Player)shooter.getBukkitEntity());
                		p.teleport(new Location(p.getWorld(),locX, locY, locZ, shooter.yaw, shooter.pitch));
                	}
                }
        else if(material == EnumBowMaterial.THRICE){
			die();
		}
        else if(material == EnumBowMaterial.TORCH){
        	Location loc = getBukkitEntity().getLocation();
        	loc.getBlock().setType(Material.TORCH);
        	
        }
        else if(material == EnumBowMaterial.WEB){
        	Location loc = getBukkitEntity().getLocation();
        	loc.getBlock().setType(Material.WEB);
        	
            Location loc1 = new Location(loc.getWorld(), loc.getX() + 1,loc.getY(),loc.getZ());
            if(loc1.getBlock().getType() == Material.AIR){
            loc1.getBlock().setType(Material.WEB);
            }
            Location loc2 = new Location(loc.getWorld(), loc.getX() - 1,loc.getY(),loc.getZ());
            if(loc2.getBlock().getType() == Material.AIR){
            loc2.getBlock().setType(Material.WEB);
            }
            Location loc3 = new Location(loc.getWorld(), loc.getX(),loc.getY(),loc.getZ() + 1);
            if(loc3.getBlock().getType() == Material.AIR){
            loc3.getBlock().setType(Material.WEB);
            }
            Location loc4 = new Location(loc.getWorld(), loc.getX(),loc.getY(),loc.getZ() - 1);
            if(loc4.getBlock().getType() == Material.AIR){
            loc4.getBlock().setType(Material.WEB);
            }
        }
	/*	if(material == EnumBowMaterial.NUKE){
			//tnt one start
		    Location one = getBukkitEntity().getLocation();
		    double onex = one.getX();
		    double oney = one.getY();
		    double onez = one.getZ();
			net.minecraft.server.World oneworld = (net.minecraft.server.World) one.getWorld();
			EntityTNTPrimed tnt = new EntityTNTPrimed(oneworld, onex,oney, onez);
			tnt.a = 0;
			world.addEntity(tnt);
			tnt.f_();
			//tnt one end
			//tnt two start
		    Location two = getBukkitEntity().getLocation();
		    double twox = two.getX();
		    double twoy = two.getY() - 1;
		    double twoz = two.getZ();
			net.minecraft.server.World twoworld = (net.minecraft.server.World) two.getWorld();
			EntityTNTPrimed tnttwo = new EntityTNTPrimed(twoworld, twox,twoy, twoz);
			tnttwo.a = 0;
			world.addEntity(tnttwo);
			tnttwo.f_();
			//tnt two end
			//tnt three start
		    Location three = getBukkitEntity().getLocation();
		    double threex = three.getX();
		    double threey = three.getY() + 2;
		    double threez = three.getZ();
			net.minecraft.server.World threeworld = (net.minecraft.server.World) three.getWorld();
			EntityTNTPrimed tntthree = new EntityTNTPrimed(threeworld, threex,threey, threez);
			tntthree.a = 0;
			world.addEntity(tntthree);
			tntthree.f_();
			//tnt three end
			//tnt four start
		    Location four = getBukkitEntity().getLocation();
		    double fourx = four.getX() + 1;
		    double foury = four.getY();
		    double fourz = four.getZ();
			net.minecraft.server.World fourworld = (net.minecraft.server.World) four.getWorld();
			EntityTNTPrimed tntfour = new EntityTNTPrimed(fourworld, fourx,foury, fourz);
			tntfour.a = 0;
			world.addEntity(tntfour);
			tntfour.f_();
			//tnt four end
			//tnt five start
		    Location five = getBukkitEntity().getLocation();
		    double fivex = five.getX() - 1;
		    double fivey = five.getY();
		    double fivez = five.getZ();
			net.minecraft.server.World fiveworld = (net.minecraft.server.World) five.getWorld();
			EntityTNTPrimed tntfive = new EntityTNTPrimed(fiveworld, fivex,fivey, fivez);
			tntfive.a = 0;
			world.addEntity(tntfive);
			tntfive.f_();
			//tnt five end
			//tnt six start
		    Location six = getBukkitEntity().getLocation();
		    double sixx = six.getX();
		    double sixy = six.getY();
		    double sixz = six.getZ() + 1;
			net.minecraft.server.World sixworld = (net.minecraft.server.World) six.getWorld();
			EntityTNTPrimed tntsix = new EntityTNTPrimed(sixworld, sixx,sixy, sixz);
			tntsix.a = 0;
			world.addEntity(tntsix);
			tntsix.f_();
			//tnt six end
			//tnt seven start
		    Location seven = getBukkitEntity().getLocation();
		    double sevenx = seven.getX();
		    double seveny = seven.getY();
		    double sevenz = seven.getZ() - 1;
			net.minecraft.server.World sevenworld = (net.minecraft.server.World) seven.getWorld();
			EntityTNTPrimed tntseven = new EntityTNTPrimed(sevenworld, sevenx,seveny, sevenz);
			tntseven.a = 0;
			world.addEntity(tntseven);
			tntseven.f_();
			//tnt seven end
		}
*/
		
}

	public void b(EntityHuman entityhuman) {
		if ((!this.world.isStatic) && (this.shooter == entityhuman) && moving==2 && (entityhuman.inventory.canHold(new ItemStack(Item.ARROW, 1)))) {
			this.world.makeSound(this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			entityhuman.receive(this, 1);
			die();
		}
	}
}