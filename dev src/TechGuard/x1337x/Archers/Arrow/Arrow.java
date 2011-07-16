package TechGuard.x1337x.Archers.Arrow;

import java.util.Random;

import net.minecraft.server.EntityArrow;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityItem;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityTNTPrimed;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MathHelper;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftItem;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.util.Vector;

import TechGuard.x1337x.Archers.Archers;

public class Arrow extends EntityArrow
{
	static Archers a;
	
  public EnumBowMaterial material;

  public int speed = 0;

public static void getPlugin(Archers p){
	a = p;
}
  private int moving = 0;
  private double firstY = 123.0D;

  public Arrow(org.bukkit.World world, LivingEntity entityliving, EnumBowMaterial material) {
    super(((CraftWorld)world).getHandle(), ((CraftLivingEntity)entityliving).getHandle());
    this.material = material;
    
  }

  public Arrow(org.bukkit.World w, LivingEntity el, EnumBowMaterial material, int thrice) {
    super(((CraftWorld)w).getHandle());
    this.material = material;
    EntityLiving entityliving = ((CraftLivingEntity)el).getHandle();

    this.shooter = entityliving;
    b(0.5F, 0.5F);
    int int0 = 0;
    if (thrice == 0) int0 = -10;
    if (thrice == 1) int0 = 10;
    setPositionRotation(entityliving.locX, entityliving.locY + entityliving.t(), entityliving.locZ, entityliving.yaw + int0, entityliving.pitch);
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

  public void m_() {
    super.m_();

    if (this.firstY == 123.0D) this.firstY = this.motY;
    if (this.speed > 0) {
      this.motY = this.firstY;
      this.speed -= 1;
    }

    if ((this.lastX == this.locX) && (this.lastY == this.locY) && (this.lastZ == this.locZ) && (this.moving == 0)) {
      this.moving = 1;
    }
    if (this.moving == 1) {
      destroy();
      this.moving = 2;
    }
  }

  public void destroy() {
	  if(this.shooter instanceof EntityPlayer){
	  EntityPlayer p = (EntityPlayer) this.shooter;
	  Player player = (Player) p.getBukkitEntity();
	  Location loca = getBukkitEntity().getLocation();
	   loca.setY(loca.getY() - 1);
	   Location newlock = loca;
	   BlockBreakEvent e = new BlockBreakEvent(newlock.getBlock(), player);
	   BlockBreakEvent ev = new BlockBreakEvent(loca.getBlock(),player);

	   
     
     
	   a.getServer().getPluginManager().callEvent(e);
	   a.getServer().getPluginManager().callEvent(ev);
      
	
	  if(!e.isCancelled() && !ev.isCancelled()){
    if (this.material == EnumBowMaterial.ICE) {
      int radius = 3;
      int radiusSq = (int)Math.pow(radius, 2.0D);
      org.bukkit.World world = getBukkitEntity().getWorld();

      for (int x = getBukkitEntity().getLocation().getBlockX() - radius; x <= getBukkitEntity().getLocation().getBlockX() + radius; x++) {
        for (int z = getBukkitEntity().getLocation().getBlockZ() - radius; z <= getBukkitEntity().getLocation().getBlockZ() + radius; z++)
        {
          if (new Vector(x, getBukkitEntity().getLocation().getBlockY(), z).distanceSquared(
            new Vector(getBukkitEntity().getLocation().getX(), getBukkitEntity().getLocation().getY(), getBukkitEntity().getLocation().getZ())) > radiusSq) {
            continue;
          }
          if (new Random().nextInt(4) > 0) {
            continue;
          }
          for (int y = getBukkitEntity().getLocation().getBlockY() + radius; y >= getBukkitEntity().getLocation().getBlockY() - radius; y--) {
            int id = world.getBlockTypeIdAt(x, y, z);
            if ((id == 6) || (id == 10) || (id == 11) || (id == 37) || (id == 38) || (id == 39) || (id == 40) || (id == 44) || 
              (id == 50) || (id == 51) || (id == 53) || (id == 55) || (id == 59) || ((id >= 63) && (id <= 72)) || (id == 75) || 
              (id == 76) || (id == 77) || (id == 78) || (id == 79) || (id == 81) || (id == 83) || (id == 85) || (id == 90)) {
              break;
            }
            if ((id == 8) || (id == 9)) {
              world.getBlockAt(x, y, z).setTypeId(79);
              break;
            }
            if (id != 0) {
              if (y == 127) {
                break;
              }
              world.getBlockAt(x, y + 1, z).setTypeId(78);
              break;
            }
          }
        }
      }
    }
    else if (this.material == EnumBowMaterial.FIRE) {
      org.bukkit.World world = getBukkitEntity().getWorld();
      world.getBlockAt((int)this.locX, (int)this.locY, (int)this.locZ).setType(Material.FIRE);
    }
    else if (this.material == EnumBowMaterial.TNT) {
   	 
    	World world1 = getBukkitEntity().getLocation().getWorld();
        net.minecraft.server.World world2 = ((CraftWorld) world1)
                .getHandle();
        EntityTNTPrimed ttnt = new EntityTNTPrimed(
                (net.minecraft.server.World) world2, locX,
                locY, locZ);
        TNTPrimed Ex = (TNTPrimed) ttnt.getBukkitEntity();
        ExplosionPrimeEvent event = new ExplosionPrimeEvent(Ex,20,false);
        EntityExplodeEvent event2 = new EntityExplodeEvent(Ex, new Location(world1, locX, locY, locZ), null);
        a.getServer().getPluginManager().callEvent(event);
        a.getServer().getPluginManager().callEvent(event2);
        if(!event.isCancelled() && !event2.isCancelled()){

        world2.createExplosion(((CraftPlayer)shooter.getBukkitEntity()).getHandle(), locX, locY, locZ, 3, false);

    }
    else if (this.material == EnumBowMaterial.THUNDER) {
      org.bukkit.World world = getBukkitEntity().getWorld();
      world.strikeLightning(new Location(world, this.locX, this.locY, this.locZ));
    }
    else if (this.material == EnumBowMaterial.MONSTER) {
      org.bukkit.World world = getBukkitEntity().getWorld();
      CreatureType[] types = { CreatureType.CREEPER, CreatureType.SKELETON, CreatureType.SLIME, CreatureType.SPIDER, CreatureType.ZOMBIE };
      world.spawnCreature(getBukkitEntity().getLocation(), types[new Random().nextInt(5)]);
    }
    else if (this.material == EnumBowMaterial.TREE) {
      org.bukkit.World world = getBukkitEntity().getWorld();
      Location loc = getBukkitEntity().getLocation();
      world.generateTree(loc, TreeType.TREE);
    }
    else if (this.material == EnumBowMaterial.ZEUS) {
      Location loc = getBukkitEntity().getLocation();
      org.bukkit.World worldf = loc.getWorld();
      worldf.strikeLightning(loc);
      loc.getBlock().setType(Material.FIRE);
      int x = (int) this.locX;
 	 int y = (int) this.locY;
 	 int z = (int) this.locZ;
 	World world = getBukkitEntity().getLocation().getWorld();
    net.minecraft.server.World world3 = ((CraftWorld) world)
            .getHandle();
    EntityTNTPrimed tnt = new EntityTNTPrimed(
            (net.minecraft.server.World) world2, locX,
            locY, locZ);
    TNTPrimed Exp = (TNTPrimed) tnt.getBukkitEntity();
    ExplosionPrimeEvent event3 = new ExplosionPrimeEvent(Ex,20,false);
    EntityExplodeEvent event4 = new EntityExplodeEvent(Ex, new Location(world, locX, locY, locZ), null);
    a.getServer().getPluginManager().callEvent(event);
    a.getServer().getPluginManager().callEvent(event2);
    if(!event.isCancelled() && !event2.isCancelled()){

    world2.createExplosion(((CraftPlayer)shooter.getBukkitEntity()).getHandle(), locX, locY, locZ, 3, false);
 	
    }
    else if (this.material == EnumBowMaterial.TP) {
      if ((this.shooter.getBukkitEntity() instanceof Player)) {
        Player pl = (Player)this.shooter.getBukkitEntity();
        pl.teleport(new Location(pl.getWorld(), this.locX, this.locY, this.locZ, this.shooter.yaw, this.shooter.pitch));
      }
    }
    else if (this.material == EnumBowMaterial.THRICE) {
      die();
    }
    else if (this.material == EnumBowMaterial.TORCH) {
      Location locati = getBukkitEntity().getLocation();
      if(loc.getBlock().getType() != Material.SIGN_POST || loc.getBlock().getType() != Material.SIGN || loc.getBlock().getType() != Material.RAILS || loc.getBlock().getType() != Material.POWERED_RAIL || loc.getBlock().getType() != Material.DETECTOR_RAIL){
      loc.getBlock().setType(Material.TORCH);
      }
    }
    else if (this.material == EnumBowMaterial.WEB) {
      Location locati = getBukkitEntity().getLocation();
      loc.getBlock().setType(Material.WEB);

      Location loc1 = new Location(loc.getWorld(), loc.getX() + 1.0D, loc.getY(), loc.getZ());
      if (loc1.getBlock().getType() == Material.AIR) {
        loc1.getBlock().setType(Material.WEB);
      }
      Location loc2 = new Location(loc.getWorld(), loc.getX() - 1.0D, loc.getY(), loc.getZ());
      if (loc2.getBlock().getType() == Material.AIR) {
        loc2.getBlock().setType(Material.WEB);
      }
      Location loc3 = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1.0D);
      if (loc3.getBlock().getType() == Material.AIR) {
        loc3.getBlock().setType(Material.WEB);
      }
      Location loc4 = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1.0D);
      if (loc4.getBlock().getType() == Material.AIR)
        loc4.getBlock().setType(Material.WEB);
    }
    else if(this.material == EnumBowMaterial.ROCKET){
    	World worldp = getBukkitEntity().getLocation().getWorld();
        net.minecraft.server.World world4 = ((CraftWorld) world)
                .getHandle();
        EntityTNTPrimed rtnt = new EntityTNTPrimed(
                (net.minecraft.server.World) world2, locX,
                locY, locZ);
        TNTPrimed Expl = (TNTPrimed) rtnt.getBukkitEntity();
        ExplosionPrimeEvent event5 = new ExplosionPrimeEvent(Ex,20,false);
        EntityExplodeEvent event6 = new EntityExplodeEvent(Ex, new Location(world, locX, locY, locZ), null);
        a.getServer().getPluginManager().callEvent(event);
        a.getServer().getPluginManager().callEvent(event2);
        if(!event.isCancelled() && !event2.isCancelled()){

        world2.createExplosion(((CraftPlayer)shooter.getBukkitEntity()).getHandle(), locX, locY, locZ, 3, false);

        
    }
    else if(this.material == EnumBowMaterial.STORM){
    	Location locat = getBukkitEntity().getLocation();
    	if(!locat.getWorld().hasStorm()){
    	locat.getWorld().setStorm(true);
    	}
    	else if(locat.getWorld().hasStorm()){
    		locat.getWorld().setStorm(false);
    	}
    }
 
  }}
    }
	  }
	  }
	  
  }
 
  public void b(EntityHuman entityhuman) {
	 
	    super.b(entityhuman);
	  }
}