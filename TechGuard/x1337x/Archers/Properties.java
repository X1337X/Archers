package TechGuard.x1337x.Archers;

import TechGuard.x1337x.Archers.Arrow.EnumBowMaterial;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.config.Configuration;

public class Properties
{
	static Archers plugin;
	public Properties(Archers p){
		this.plugin = p;
	}
  public static HashMap<Short, ArrayList<ItemStack>> ArrowAmmo = new HashMap();
  public static double DAMAGE;
  public static double SPEED;
  static String dir = "plugins/Archers/";
  private static File ArrowFile;
  private static File ConfigFile;

  public static void reload()
  {
    load();
  }

  private static void load() {
    ArrowFile = new File(dir + "arrow.ammo");
    ConfigFile = new File(dir + "config.txt");
    makeConfig();
    checkForArrow();

    loadArrow();
    loadConfig(new Configuration(ConfigFile));
  }

  private static void checkForArrow() {
    try {
      if (!ArrowFile.exists()) {
        ArrowFile.getParentFile().mkdirs();
        ArrowFile.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(ArrowFile));

        out.write("#The right order:"); out.newLine();
        out.write("#  ARROW NAME:ITEM ID,AMOUNT:NEW ITEM ID, AMOUNT, etc. etc."); out.newLine();
        out.write("#Arrow names:"); out.newLine();
        for (EnumBowMaterial bow : EnumBowMaterial.values()) {
          out.write("  " + bow.getName()); out.newLine();
        }
        out.write("#Lines witch start with the # symbol, will be ignored!"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Normal Arrow"); out.newLine();
        out.write("Normal:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Ice Arrow"); out.newLine();
        out.write("Ice:332,1:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Fire Arrow"); out.newLine();
        out.write("Fire:263,1:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#TNT Arrow"); out.newLine();
        out.write("TNT:289,2:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Thunder Arrow"); out.newLine();
        out.write("Thunder:331,5:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Monster Arrow"); out.newLine();
        out.write("Monster:352,2:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Thrice Arrow"); out.newLine();
        out.write("Thrice:262,3"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Zombie Arrow"); out.newLine();
        out.write("Zombie:295,1:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Tree Arrow"); out.newLine();
        out.write("Tree:6,1:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Pig Arrow"); out.newLine();
        out.write("Pig:320,1:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Zeus Arrow"); out.newLine();
        out.write("Zeus:264,2:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#TP Arrow"); out.newLine();
        out.write("Tp:287,1:262,1"); out.newLine();
        out.write(""); out.newLine();
        out.write("#Fly arrow"); out.newLine();
        out.write("Fly:288,5:262,1");
        out.write(""); out.newLine();
        out.newLine();
        out.write("#Torch arrow"); out.newLine();
        out.write("Torch:50:1:262,1"); out.newLine();
        out.write("#web arrow"); out.newLine();
        out.write("Web:30,4:262,1"); out.newLine();
        out.write("#Steal arrow"); out.newLine();
        out.write("Steal:287,1:262,1"); out.newLine();
        out.write("#Sniper arrow"); out.newLine();
        out.write("Sniper:348,1:262,2");

        out.newLine();
        out.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
private static void makeConfig(){
	try{
		if(!ConfigFile.exists()){
			ConfigFile.getParentFile().mkdirs();
			ConfigFile.createNewFile();
			BufferedWriter b = new BufferedWriter(new FileWriter(ConfigFile));
			b.write("#usage is arrowname:damage:speed");
			b.newLine();
			b.write("#Do not change the arrowname");
			b.newLine();
			b.write("#lines starting with a # will be ignored");
			b.newLine();
			b.write("#example making tnt move 20 blocks a second and setting its damage to 20");
			b.newLine();
			b.write("#TNT:20:20");
			b.newLine();
			b.write("#------------------------------------------------------------------------");
			b.newLine();
			b.write("Normal:4:1");
			b.newLine();
			b.write("Ice:7:1");
			b.newLine();
			b.write("Fire:4:1");
			b.newLine();
			b.write("TNT:0:2");
			b.newLine();
			b.write("Thunder:0:1");
			b.newLine();
			b.write("Monster:0:1");
			b.newLine();
			b.write("Thrice:4:1");
			b.newLine();
			b.write("Zombie:0:1");
			b.newLine();
			b.write("Tree:0:1");
			b.newLine();
			b.write("Pig:0:1");
			b.newLine();
			b.write("Zeus:20:3");
			b.newLine();
			b.write("TP:0:1");
			b.newLine();
			b.write("Fly:0:1");
			b.newLine();
			b.write("Web:0:1");
			b.newLine();
			b.write("Steal:0:1");
			b.newLine();
			b.write("Sniper:100:70");
			b.close();

           

plugin.sM("Made config.txt sucessfully!");
		}
	}catch(Exception e){
		e.printStackTrace();
	}

}
  
  private static void loadArrow() {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(ArrowFile))));
      String strLine;
      while ((strLine = br.readLine()) != null)
      {
      
        if ((strLine.startsWith("#")) || (strLine.startsWith(" ")) || (!strLine.contains(":"))) {
          continue;
        }
        String[] split = strLine.split(":");
        ArrayList list = new ArrayList();
        for (String s : split) {
          if (s.contains(",")) {
            list.add(new ItemStack(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1])));
          }
        }
        ArrowAmmo.put(Short.valueOf(EnumBowMaterial.fromName(split[0]).getDataValue()), list);
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void loadConfig(Configuration config) {
    try {
      config.load();

      String tag = "Global.";
      DAMAGE = config.getDouble(tag + "Normal-Arrow-Damage", 4.0D);
      SPEED = config.getDouble(tag + "Normal-Arrow-Speed", 1.0D);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}