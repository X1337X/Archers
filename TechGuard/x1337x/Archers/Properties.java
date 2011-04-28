package TechGuard.x1337x.Archers;

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

import TechGuard.x1337x.Archers.Arrow.EnumBowMaterial;


/**
 * @author TechGuard
 */
public class Properties {
	public static HashMap<Short,ArrayList<ItemStack>> ArrowAmmo = new HashMap<Short,ArrayList<ItemStack>>();
	public static double DAMAGE,SPEED;
	
	private static String dir = "plugins/Archers/";
	private static File ArrowFile;
	private static File ConfigFile;

	public static void reload(){
		load();
	}

	private static void load(){
		ArrowFile = new File(dir + "arrow.ammo");
		ConfigFile = new File(dir + "config.yml");
		checkForConfig();
		checkForArrow();

		loadArrow();
		loadConfig(new Configuration(ConfigFile));
	}

	private static void checkForArrow(){
		try{
			if(!ArrowFile.exists()){
				ArrowFile.getParentFile().mkdirs();
				ArrowFile.createNewFile();
		        BufferedWriter out = new BufferedWriter(new FileWriter(ArrowFile));

		        out.write("#The right order:"); out.newLine();
		        out.write("#  ARROW NAME:ITEM ID,AMOUNT:NEW ITEM ID, AMOUNT, etc. etc."); out.newLine();
		        out.write("#Arrow names:"); out.newLine();
		       	for(EnumBowMaterial bow : EnumBowMaterial.values()){
		       		out.write("  "+bow.getName()); out.newLine();
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
		        out.write("Thunder:331,5:262,1");out.newLine();
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
		        out.write("#Tree Arrow");out.newLine();
		        out.write("Tree:6,1:262,1");out.newLine();
		        out.write(""); out.newLine();
		        out.write("#Pig Arrow");out.newLine();
		        out.write("Pig:320,1:262,1");out.newLine();
		        out.write(""); out.newLine();
		        out.write("#Zeus Arrow");out.newLine();
		        out.write("Zeus:264,2:262,1");out.newLine();
		        out.write(""); out.newLine();
		        out.write("#TP Arrow");out.newLine();
		        out.write("Tp:287,1:262,1");out.newLine();
		        out.write(""); out.newLine();
		        out.write("#Fly arrow");out.newLine();
		        out.write("Fly:288,5:262,1");
		        out.write(""); out.newLine();
		        out.newLine();
		        out.write("#Torch arrow");out.newLine();
		        out.write("Torch:50:1:262,1");out.newLine();
		        out.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void checkForConfig(){
		try{
			if(!ConfigFile.exists()){
				ConfigFile.getParentFile().mkdirs();
				ConfigFile.createNewFile();
				Configuration config = new Configuration(ConfigFile);
				
				String tag = "Global.";
				config.setProperty(tag + "Normal-Arrow-Damage", 4);
				config.setProperty(tag + "Normal-Arrow-Speed", 1);
				
				config.save();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void loadArrow(){
		try{
		    BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(ArrowFile))));
		    String strLine;
		    while ((strLine = br.readLine()) != null){
		    	if(strLine.startsWith("#") || strLine.startsWith(" ") || !strLine.contains(":")){
		    		continue;
		    	}
		    	String[] split = strLine.split(":");
		    	ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		    	for(String s : split){
		    		if(s.contains(",")){
		    			list.add(new ItemStack(Integer.parseInt(s.split(",")[0]), Integer.parseInt(s.split(",")[1])));
		    		}
		    	}
		    	ArrowAmmo.put(EnumBowMaterial.fromName(split[0]).getDataValue(), list);
		    }
		    br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void loadConfig(Configuration config){
		try{
			config.load();
			
			String tag = "Global.";
			DAMAGE = config.getDouble(tag + "Normal-Arrow-Damage", 4);
			SPEED = config.getDouble(tag + "Normal-Arrow-Speed", 1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}