package TechGuard.x1337x.Archers.Arrow;

import java.util.Scanner;

import org.bukkit.Material;

import TechGuard.x1337x.Archers.Properties;
/**
 * @author TechGuard
 */
public enum EnumBowMaterial{
	
	STANDARD(	"Normal",	getDamage("Normal") , getSpeed("Normal"),		new Object[]{ Material.LOG, Material.WOOD },"The basic arrow"),
	ICE(		"Ice",		getDamage("Ice"),     getSpeed("Ice"),			new Object[]{ Material.SNOW, Material.ICE },"Spreads snow around the arrow"),
	FIRE(		"Fire",		getDamage("Fire"),	  getSpeed("Fire"),			new Object[]{ Material.FIRE },"Sets fire to what it hits"),
	TNT(		"TNT",		getDamage("TNT"),	  getSpeed("TNT"),			new Object[]{ Material.TNT },"Explodes on impact"),
	THUNDER(	"Thunder",	getDamage("Thunder"), getSpeed("Thunder"),		new Object[]{ Material.REDSTONE_ORE },"Makes thunder strike on impact"),
	MONSTER(	"Monster",	getDamage("Monster"), getSpeed("Monster"),		new Object[]{ Material.MOB_SPAWNER },"Spawns a random mob on impact"),
    THRICE(		"Thrice",	getDamage("Thrice"),  getSpeed("Thrice"),		new Object[]{ Material.DISPENSER },"Shoots 3 arrows instead of one"),
    ZOMBIE(		"Zombie",	getDamage("Zombie"),  getSpeed("Zombie"),		new Object[]{ Material.LAPIS_BLOCK },"Morphs giants and zombie beetween each other"),
    TREE(		"Tree",		getDamage("Tree"),	  getSpeed("Tree"),			new Object[]{ Material.SAPLING },"Spawns a tree where it lands"),
    PIG(		"Pig",		getDamage("Pig"),	  getSpeed("Pig"),			new Object[]{ Material.GRASS },"Morps pig and zombie pigman beetween each other"),
    ZEUS(		"Zeus",		getDamage("Zeus"),    getSpeed("Zeus"),			new Object[]{ Material.DIAMOND_BLOCK },"Makes fire lightning and tnt on impact"),
	TP(			"TP",		getDamage("TP"),      getSpeed("TP"),			new Object[]{ Material.WEB},"Tps you to where it lands"),
	FLY(		"Fly",		getDamage("Fly"),     getSpeed("Fly"),			new Object[]{ Material.WOOL},"Hit a mob (including players) and they go 20 block up"),
	TORCH(      "Torch",    getDamage("Torch"),	  getSpeed("Torch"),      	new Object[]{ Material.COAL_ORE},"Spawns a single torch where it lands"),
	WEB  (      "Web",      getDamage("Web"),	  getSpeed("Web"),      	new Object[]{ Material.IRON_ORE},"Makes a square of 4 webs where it lands"),
	STEAL(      "Steal",    getDamage("Steal"),   getSpeed("Steal"),        new Object[]{ Material.CHEST},"Steals the hits player item in hand and throws it out"),
	//NUKE(       "Nuke",     0,      new Object[]{ Material.OBSIDIAN},"10 tnt at once!"),
	SNIPER(     "Sniper",   getDamage("Sniper"),  getSpeed("Sniper"),       new Object[]{ Material.DIAMOND_ORE},"One shot super fast arrow");
	//MINIGUN(    "MiniGun"   getDamage("Minigun")  getSpeed("Minigun"),      )
	
	
	private String name;
	private String description;
	private short data;
	private int damage;
	private Object[] activate;
	int speed;

	EnumBowMaterial(String name, int damage,int speed,Object[] activate,String description){
		this.name = name;
		this.data = ArrowHandler.lastData++;
		this.damage = damage;
		this.activate = activate;
		this.description = description;
		this.speed = speed;
	}

	public String getName(){
		return name;
	}

	public short getDataValue(){
		return data;
	}

	public int getDamageValue(){
		return damage;
	}

	public Object[] getBlocks(){
		return activate;
	}
	public String getDescription(){
		return description;
	}

	public static EnumBowMaterial fromData(short data){
		for(EnumBowMaterial material : values()){
			if(material.data == data){
				return material;
			}
		}
		return STANDARD;
	}

	public static EnumBowMaterial fromName(String name){
		for(EnumBowMaterial material : values()){
			if(material.name.toLowerCase().startsWith(name.toLowerCase())){
				return material;
			}
		}
		return STANDARD;
	}
	public static int getSpeed(String m){
		Scanner scan = new Scanner(Properties.dir + "config.txt");
		int speed = 1;
        while(scan.hasNextLine()){
        	String name = scan.nextLine();
        	if(name.equalsIgnoreCase(m)){
        		String[] line = name.split(":");
        		speed  = Integer.parseInt(line[2].toString());
        		System.out.println("Speed for the " + m + " = " + line[2]);
        		
        	}
        }
        return speed;
	}
	public static int getDamage(String m){
		Scanner scan = new Scanner(Properties.dir + "config.txt");
		int damage = 0;
        while(scan.hasNextLine()){
        	String name = scan.nextLine();
        	if(name.equalsIgnoreCase(m)){
        		String[] line = name.split(":");
        		System.out.println(line);
        		damage  = Integer.parseInt(line[1].toString());
        		
        	}
        }
        return damage;
	}
}