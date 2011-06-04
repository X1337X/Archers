package TechGuard.x1337x.Archers;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkitcontrib.BukkitContrib;

import TechGuard.x1337x.Archers.Commands.Archerscommand;
import TechGuard.x1337x.Archers.Commands.ArrowCommand;
import TechGuard.x1337x.Archers.Crafting.iListener;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class Archers extends JavaPlugin
{
  private eListener eL = new eListener();
  private pListener pL = new pListener();
  private iListener iL = new iListener();
  public static PermissionHandler Permissions;

  boolean contrib = false;
  public int rb = 803;
 public static boolean enable = true;
  public void onDisable() {
    sM("Disabled!");
  }

  public void onEnable()
  {
	  getContrib();
  
	  if(contrib){
	    	sM("Found BukkitContrib crafting permissions enabled!");
	    }
	    else{
	    	sM("Cant find BukkitContrib, disabling....");
	    }
	  if(enable){
    loadCommands();
    registerEvents();
    setupPermissions();
    Properties.reload();
    rightBuild();
	  }
   
    if(enable){
    sM("Made by " + (String)getDescription().getAuthors().get(0) + " and " + (String)getDescription().getAuthors().get(1) + " Enabled!");
    }
  }

  private void registerEvents()
  {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvent(Event.Type.PLAYER_ITEM_HELD, this.pL, Event.Priority.Highest, this);
    pm.registerEvent(Event.Type.PLAYER_INTERACT, this.pL, Event.Priority.Highest, this);
    if(this.contrib){
    pm.registerEvent(Event.Type.CUSTOM_EVENT, this.iL, Event.Priority.Highest, this);
    }
    pm.registerEvent(Event.Type.ENTITY_DAMAGE, this.eL, Event.Priority.Highest, this);
  }

  private void setupPermissions() {
	  Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

      if (this.Permissions == null) {
          if (test != null) {
        this.Permissions = ((Permissions)test).getHandler();
        sM("Permissions found");
        enable = true;
          } else {
              sM("Permissions not found...disabling");
              this.enable = false;
              this.getServer().getPluginManager().disablePlugin(this);
          }
      }
  }

  public String getName()
  {
    PluginDescriptionFile pdfFile = getDescription();
    return pdfFile.getName();
  }
  public String getVersion() {
    PluginDescriptionFile pdfFile = getDescription();
    return pdfFile.getVersion();
  }
  public void sM(String message) {
    System.out.println("[" + getName() + " : " + getVersion() + "] " + message);
  }
  public Player getPlayer(String name) {
    for (Player pl : getServer().getOnlinePlayers()) {
      if (pl.getName().toLowerCase().startsWith(name.toLowerCase())) {
        return pl;
      }
    }
    return null;
  }
  private void loadCommands() {
    getCommand("arrow").setExecutor(new ArrowCommand(this));
    getCommand("archers").setExecutor(new Archerscommand(this));
  }
  private void rightBuild() {
    Matcher versionMatch = Pattern.compile("git-Bukkit-([0-9]+).([0-9]+).([0-9]+)-[0-9]+-[0-9a-z]+-b([0-9]+)jnks.*").matcher(getServer().getVersion());
    if (versionMatch.matches()) {
      int versionNumber = Integer.parseInt(versionMatch.group(4));
      if (versionNumber < this.rb) {
        sM("Bukkit version is not the recommended build");
      }
      else
      {
        sM("Bukkit version is the recommend version for Archers");
      }
    }
  }
  public static int getSpeed(String m){

  	String file = Properties.dir + "config.txt";
		File f = new File(file);
		int speed = 0;
		try {
			Scanner scan = new Scanner(f);
		//System.out.println(scan);
	        while(scan.hasNextLine()){
	        	String line1 = scan.nextLine();
	        	String[] split = line1.split(":");
	        	String name = split[0];
	        	if(!name.startsWith("#")){
	        	if(m.equalsIgnoreCase(name)){
	        		speed  = Integer.parseInt(split[1].toString());
	        		return speed;
	        	}
	        }
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      return speed;
  }
  public static int getDamage(String m){
	  
	  	String file = Properties.dir + "config.txt";
			File f = new File(file);
			int damage = 0;
			try {
				Scanner scan = new Scanner(f);
			
		        while(scan.hasNextLine()){
		        	String line1 = scan.nextLine();
		        	String[] split = line1.split(":");
		        	String name = split[0];
		        	if(!name.startsWith("#")){
		 
		        	if(m.equalsIgnoreCase(name)){
		        	
		        		
		        		damage  = Integer.parseInt(split[2].toString());
		        		return damage;
		        	}
		        }
		        }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      return damage;
  }
  public void getContrib(){
	  Plugin test = this.getServer().getPluginManager().getPlugin("BukkitContrib");
    if(test != null){
    	this.contrib = true;
    	
    }
     else{
    	
    	this.contrib = false;
    	this.getServer().getPluginManager().disablePlugin(this);
    	this.enable = false;
     }
  }
}