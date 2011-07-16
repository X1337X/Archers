package TechGuard.x1337x.Archers.Arrow;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftArrow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.inventory.ItemStack;

import TechGuard.x1337x.Archers.Archers;

public class ArrowHandler
{
  public static short lastData = 0;
  static Archers plugin;
public ArrowHandler(Archers a){
	plugin = a;
}
  public static void onArrowCreate(Player p, Arrow arrow) {
    try {
      org.bukkit.entity.Arrow ea = (org.bukkit.entity.Arrow)arrow.getBukkitEntity();
      arrow.speed = Archers.getSpeed(arrow.material.getName());
      if (arrow.material == EnumBowMaterial.FIRE) {
        ea.setFireTicks(300);
      }
  
      arrow.world.addEntity(arrow);
    }
    catch (Exception localException) {
    }
  }

  public static void onArrowDestroy(final EntityDamageByProjectileEvent event) {
    try {
      Arrow arrow = (Arrow)((CraftArrow)event.getProjectile()).getHandle();
event.setDamage(Archers.getDamage(arrow.material.getName()));
    
      if (arrow.material == EnumBowMaterial.FIRE) {
        event.getEntity().setFireTicks(80);
      }
      else if (arrow.material == EnumBowMaterial.ZOMBIE) {
        if ((event.getEntity() instanceof Zombie)) {
          Zombie zombie = (Zombie)event.getEntity();
          Giant giant = (Giant)zombie.getWorld().spawnCreature(zombie.getLocation(), CreatureType.GIANT);
          giant.setHealth(zombie.getHealth());
          zombie.remove();
        }
        else if ((event.getEntity() instanceof Giant)) {
          Giant giant = (Giant)event.getEntity();
          Zombie zombie = (Zombie)giant.getWorld().spawnCreature(giant.getLocation(), CreatureType.ZOMBIE);
          zombie.setHealth(giant.getHealth());
          giant.remove();
        }
      }
      if (arrow.material == EnumBowMaterial.PIG) {
        if ((event.getEntity() instanceof Pig)) {
          Pig pig = (Pig)event.getEntity();
          PigZombie pigman = (PigZombie)pig.getWorld().spawnCreature(pig.getLocation(), CreatureType.PIG_ZOMBIE);
          pigman.setHealth(pig.getHealth());
          pig.remove();
        }
        else if ((event.getEntity() instanceof PigZombie)) {
          PigZombie pigman = (PigZombie)event.getEntity();
          Pig pig = (Pig)pigman.getWorld().spawnCreature(pigman.getLocation(), CreatureType.PIG);
          pig.setHealth(pigman.getHealth());
          pigman.remove();
        }
      }
      if (arrow.material == EnumBowMaterial.FLY) {
        LivingEntity entity = (LivingEntity)event.getEntity();
        entity.teleport(new Location(entity.getWorld(), entity.getLocation().getX(), entity.getLocation().getY() + 20.0D, entity.getLocation().getZ()));
  
      }
      if ((arrow.material == EnumBowMaterial.STEAL) && 
        ((event.getEntity() instanceof Player)))
      {
        Player p = (Player)event.getEntity();
        Random r = new Random();
        int ran = r.nextInt(17);
        ItemStack iih = p.getInventory().getItem(ran);
        Location loc = p.getLocation();
        Location locnew = new Location(loc.getWorld(), loc.getX() + 10.0D, loc.getY() + 10.0D, loc.getBlockZ());
  
        if (iih != null || iih.getType() != Material.AIR) {
          p.getInventory().remove(iih);
         Player shooter = (Player) event.getDamager();
         shooter.getInventory().addItem(iih);
        }

      }
   if(arrow.material == EnumBowMaterial.STONE){
	 Archers.stone.add((Player) event.getEntity());
	 plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
		 public void run(){
			 Archers.stone.remove(event.getEntity());
		 }
	 }, 100);
   }
      if(arrow.material == EnumBowMaterial.SPAWN){
    	  Location spawn = event.getEntity().getLocation().getWorld().getSpawnLocation();
    	  Entity t = event.getEntity();
    	  t.teleport(spawn);
      }
    }
    catch (Exception localException)
    {
    }
  }
}