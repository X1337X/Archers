package TechGuard.Archers;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijikokun.bukkit.Permissions.Permissions;
import com.nijiko.permissions.PermissionHandler;
/**
 * @author �TechGuard
 */
public class Archers extends JavaPlugin{
	private eListener eL = new eListener();
	private pListener pL = new pListener();
	public static PermissionHandler Permissions;
		
    public void onDisable() {
    	sM("Disabled!");
    }

    public void onEnable() {
         sM("Made by " + this.getDescription().getAuthors() + " Enabled!");
        
    	registerEvents();
    	setupPermissions();
    	Properties.reload();
    }
    
    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_ITEM_HELD, pL, Priority.Highest, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT, pL, Priority.Highest, this);
        
        pm.registerEvent(Event.Type.ENTITY_DAMAGE, eL, Priority.Highest, this);
    }
    
    private void setupPermissions() {
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
        if (this.Permissions == null) {
            if (test != null) {
                this.Permissions = ((Permissions)test).getHandler();
            } else {
                sM("Permission system not detected! Disabling..");
                getPluginLoader().disablePlugin(this);
            }
        }
    }

    public String getName(){
        PluginDescriptionFile pdfFile = this.getDescription();
        return pdfFile.getName();
    }
    public String getVersion(){
        PluginDescriptionFile pdfFile = this.getDescription();
        return pdfFile.getVersion();
    }
    public void sM(String message){
    	System.out.println("["+getName()+" : "+getVersion()+"] "+message);
    }
    public Player getPlayer(String name){
		for(Player pl : this.getServer().getOnlinePlayers()){
			if(pl.getName().toLowerCase().startsWith(name.toLowerCase())){
				return pl;
			}
		}
    	return null;
    }
}