package TechGuard.x1337x.Archers.Crafting;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import TechGuard.x1337x.Archers.Archers;

public class CraftHandler {
public  ArrayList<Integer> tasks = new ArrayList<Integer>();
public void run(Player p ,Archers pl){
	CraftThread task = new CraftThread(p);
	int id = pl
	.getServer()
	.getScheduler()
	.scheduleSyncRepeatingTask(pl, task, 0,
	2);
	tasks.add(id);
	task.addID(id);
}
}
