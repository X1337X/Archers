package TechGuard.x1337x.Archers.Updater;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.plugin.Plugin;

public class Updater {
public static boolean needsUpdate() throws MalformedURLException{
	String location = "http://tetragaming.com/1337/Archers.jar";
	URL u = new URL(location);
	File newjar = new File(u.getFile());
	long lastupdate = newjar.lastModified();
	String jar = "plugins" + File.separator + "Archers.jar";
	File archers = new File(jar);
	long jarupdate = archers.lastModified();
	if(lastupdate  > jarupdate){
		return true;
	}else{
		return false;
	}
}
public static void update(Plugin plugin) throws IOException{
	System.out.println("Disabling archers...updating");
	plugin.getServer().getPluginManager().disablePlugin(plugin);
	String location = "http://tetragaming.com/1337/Archers.jar";
	URL u = new URL(location);
	u.openConnection();
	InputStream s = u.openStream();
	
	
}
}
