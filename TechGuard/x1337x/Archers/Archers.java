package TechGuard.x1337x.Archers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import TechGuard.x1337x.Archers.Commands.Archerscommand;
import TechGuard.x1337x.Archers.Commands.ArrowCommand;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
/**
 * @author TechGuard
 */
public class Archers extends JavaPlugin{
	private eListener eL = new eListener();
	private pListener pL = new pListener();
	public static PermissionHandler Permissions;
   public  int rb = 740;

    public void onDisable() {
    	sM("Disabled!");
    }

    public void onEnable() {
       
     	loadCommands();
    	registerEvents();
    	setupPermissions();
    	Properties.reload();
    	rightBuild();
    	sM("Made by " + this.getDescription().getAuthors().get(0) + " and " +this.getDescription().getAuthors().get(1)  + " Enabled!");
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
                sM("Found permissions");
    
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
    private void loadCommands(){
    	getCommand("arrow").setExecutor(new ArrowCommand(this));
    	getCommand("archers").setExecutor(new Archerscommand(this));
    }
    private void rightBuild(){
    	 Matcher versionMatch = Pattern.compile("git-Bukkit-([0-9]+).([0-9]+).([0-9]+)-[0-9]+-[0-9a-z]+-b([0-9]+)jnks.*").matcher(getServer().getVersion());
    	    if (versionMatch.matches()) {
    	      int versionNumber = Integer.parseInt(versionMatch.group(4));
    	      if (versionNumber < this.rb){
    	       sM("Bukkit version is not the recommended build");
    	       
    	       }
    	      else{
    	    	  sM("Bukkit version is the recommend version for Archers");
    	      }
    	    }
    	   
    	    else {
    	     
    	    }
    }
}