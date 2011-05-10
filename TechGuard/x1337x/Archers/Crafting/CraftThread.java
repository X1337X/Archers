package TechGuard.x1337x.Archers.Crafting;

import net.minecraft.server.ContainerWorkbench;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Packet103SetSlot;

import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import TechGuard.x1337x.Archers.Archers;

public class CraftThread implements Runnable {
	private CraftPlayer craftPlayer;
	private EntityPlayer entityPlayer;
	Archers plugin = new Archers();
	private int id;

	public CraftThread(Player p) {
		this.craftPlayer = (CraftPlayer) p;
		this.entityPlayer = craftPlayer.getHandle();
	}

	public void addID(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		try {
			if (entityPlayer == null
					|| entityPlayer.activeContainer == entityPlayer.defaultContainer) {
				kill();
				return;
			}
			ContainerWorkbench containerBench = null;
			try {
				containerBench = (ContainerWorkbench) entityPlayer.activeContainer;
			} catch (Exception ex) {
				kill();
				return;
			}
			ItemStack Current = (ItemStack) containerBench.d.get(0);
	
					//craftPlayer.sendMessage("Something was crafted");
					int ingredient = Current.id;
				if(ingredient == Material.BOW.getId()){
					Player p = (Player) entityPlayer;
					if(!this.plugin.Permissions.has(p, "archer.craft"));{
						setNull(this.craftPlayer,containerBench);
						p.sendMessage("You cant make a bow!");
					}
				}
				
			

			if (!craftPlayer.isOnline())
				kill();

	}

		 catch (Exception ex) {
			plugin.sM("Error in workbench task. Error is: "
							+ ex.getMessage() + ". Stack trace: "
							+ ex.getStackTrace()[0]);
			return;
		}
	}

	public void kill() {
		plugin.getServer().getScheduler().cancelTask(id);
	
	}
	public void setNull(CraftPlayer p,ContainerWorkbench c){
		//p.sendMessage(c.d.get(0)+"");
		//c.d.set(0, null);
		//p.sendMessage(c.d.get(0)+"");
		//c.b.a(0, (Integer) null);
		//Packet103SetSlot packet = new Packet103SetSlot(
		//		entityPlayer.activeContainer.f, 0, null);
		//entityPlayer.a.b(packet);

		ItemStack is = new ItemStack(0,0,0);
        if (is.id == 0)
          is = null;
        c.b.setItem(0, is);
        Packet103SetSlot packet = new Packet103SetSlot(
          this.entityPlayer.activeContainer.f, 0, is);
        this.entityPlayer.netServerHandler.sendPacket(packet);

	}
}
