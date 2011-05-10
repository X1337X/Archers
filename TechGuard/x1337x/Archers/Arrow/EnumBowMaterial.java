package TechGuard.x1337x.Archers.Arrow;

import org.bukkit.Material;
/**
 * @author TechGuard
 */
public enum EnumBowMaterial{
	STANDARD(	"Normal",	4,		new Object[]{ Material.LOG, Material.WOOD },"The basic arrow"),
	ICE(		"Ice",		7,		new Object[]{ Material.SNOW, Material.ICE },"Spreads snow around the arrow"),
	FIRE(		"Fire",		5,		new Object[]{ Material.FIRE },"Sets fire to what it hits"),
	TNT(		"TNT",		0,		new Object[]{ Material.TNT },"Explodes on impact"),
	THUNDER(	"Thunder",	0,		new Object[]{ Material.REDSTONE_ORE },"Makes thunder strike on impact"),
	MONSTER(	"Monster",	0,		new Object[]{ Material.MOB_SPAWNER },"Spawns a random mob on impact"),
    THRICE(		"Thrice",	3,		new Object[]{ Material.DISPENSER },"Shoots 3 arrows instead of one"),
    ZOMBIE(		"Zombie",	0,		new Object[]{ Material.LAPIS_BLOCK },"Morphs giants and zombie beetween each other"),
    TREE(		"Tree",		0,		new Object[]{ Material.SAPLING },"Spawns a tree where it lands"),
    PIG(		"Pig",		0,		new Object[]{ Material.GRASS },"Morps pig and zombie pigman beetween each other"),
    ZEUS(		"Zeus",		20,		new Object[]{ Material.DIAMOND_BLOCK },"Makes fire lightning and tnt on impact"),
	TP(			"Tp",		0,		new Object[]{ Material.WEB},"Tps you to where it lands"),
	FLY(		"Fly",		0,		new Object[]{ Material.WOOL},"Hit a mob (including players) and they go 20 block up"),
	TORCH(      "Torch",    0,      new Object[]{ Material.COAL_ORE},"Spawns a single torch where it lands"),
	WEB  (      "Web",      0,      new Object[]{ Material.IRON_ORE},"Makes a square of 4 webs where it lands"),
	STEAL(      "Steal",    0,      new Object[]{ Material.CHEST},"Steals the hits player item in hand and throws it out"),
	//NUKE(       "Nuke",     0,      new Object[]{ Material.OBSIDIAN},"10 tnt at once!"),
	SNIPER(     "Sniper",   100,    new Object[]{ Material.DIAMOND_ORE},"One shot super fast arrow");
	
	
	private String name;
	private String description;
	private short data;
	private int damage;
	private Object[] activate;

	EnumBowMaterial(String name, int damage, Object[] activate,String description){
		this.name = name;
		this.data = ArrowHandler.lastData++;
		this.damage = damage;
		this.activate = activate;
		this.description = description;
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
}