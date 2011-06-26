package TechGuard.x1337x.Archers.Crafting;

import TechGuard.x1337x.Archers.Archers;
import java.util.ArrayList;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class CraftHandler
{
  public ArrayList<Integer> tasks = new ArrayList();

  public void run(Player p, Archers pl) { CraftThread task = new CraftThread(p);
    int id = pl
      .getServer()
      .getScheduler()
      .scheduleSyncRepeatingTask(pl, task, 0L, 
      2L);
    this.tasks.add(Integer.valueOf(id));
    task.addID(id);
  }
}