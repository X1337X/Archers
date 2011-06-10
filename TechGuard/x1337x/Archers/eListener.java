package TechGuard.x1337x.Archers;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

import TechGuard.x1337x.Archers.Arrow.ArrowHandler;

public class eListener extends EntityListener
{
  public void onEntityDamage(EntityDamageEvent event)
  {
    if ((event instanceof EntityDamageByProjectileEvent)) {
      EntityDamageByProjectileEvent ev = (EntityDamageByProjectileEvent)event;
      if ((ev.getProjectile() instanceof Arrow) && ev.getDamager() instanceof Player)
        ArrowHandler.onArrowDestroy(ev);
    
    }
  }
}