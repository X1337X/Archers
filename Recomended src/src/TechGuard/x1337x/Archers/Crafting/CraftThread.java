package TechGuard.x1337x.Archers.Crafting;

import TechGuard.x1337x.Archers.Archers;
import com.nijiko.permissions.PermissionHandler;
import java.util.List;
import net.minecraft.server.Container;
import net.minecraft.server.ContainerWorkbench;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.IInventory;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NetServerHandler;
import net.minecraft.server.Packet103SetSlot;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class CraftThread
  implements Runnable
{
  private CraftPlayer craftPlayer;
  private EntityPlayer entityPlayer;
  Archers plugin = new Archers();
  private int id;

  public CraftThread(Player p)
  {
    this.craftPlayer = ((CraftPlayer)p);
    this.entityPlayer = this.craftPlayer.getHandle();
  }

  public void addID(int id) {
    this.id = id;
  }

  public void run()
  {
    try {
      if ((this.entityPlayer == null) || 
        (this.entityPlayer.activeContainer == this.entityPlayer.defaultContainer)) {
        kill();
        return;
      }
      ContainerWorkbench containerBench = null;
      try {
        containerBench = (ContainerWorkbench)this.entityPlayer.activeContainer;
      } catch (Exception ex) {
        kill();
        return;
      }
      ItemStack Current = (ItemStack)containerBench.d.get(0);

      int ingredient = Current.id;
      if (ingredient == Material.BOW.getId()) {
        Player p = (Player)this.entityPlayer;
        if (!Archers.Permissions.has(p, "archer.craft"));
        setNull(this.craftPlayer, containerBench);
        p.sendMessage("You cant make a bow!");
      }

      if (!this.craftPlayer.isOnline()) {
        kill();
      }
    }
    catch (Exception ex)
    {
      this.plugin.sM("Error in workbench task. Error is: " + 
        ex.getMessage() + ". Stack trace: " + 
        ex.getStackTrace()[0]);
      return;
    }
  }

  public void kill() {
    this.plugin.getServer().getScheduler().cancelTask(this.id);
  }

  public void setNull(CraftPlayer p, ContainerWorkbench c)
  {
    ItemStack is = new ItemStack(0, 0, 0);
    if (is.id == 0)
      is = null;
    c.b.setItem(0, is);
    Packet103SetSlot packet = new Packet103SetSlot(
      this.entityPlayer.activeContainer.f, 0, is);
    this.entityPlayer.netServerHandler.sendPacket(packet);
  }
}