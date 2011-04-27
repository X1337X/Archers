package TechGuard.x1337x.Archers.Arrow;

import org.bukkit.Material;
/**
 * @author TechGuard
 */
public enum EnumBowMaterial{
	STANDARD(	"Normal",	4,		new Object[]{ Material.LOG, Material.WOOD }),
	ICE(		"Ice",		7,		new Object[]{ Material.SNOW, Material.ICE }),
	FIRE(		"Fire",		5,		new Object[]{ Material.FIRE }),
	TNT(		"TNT",		0,		new Object[]{ Material.TNT }),
	THUNDER(	"Thunder",	0,		new Object[]{ Material.REDSTONE_ORE }),
	MONSTER(	"Monster",	0,		new Object[]{ Material.MOB_SPAWNER }),
    THRICE(		"Thrice",	3,		new Object[]{ Material.DISPENSER }),
    ZOMBIE(		"Zombie",	0,		new Object[]{ Material.LAPIS_BLOCK }),
    TREE(		"Tree",		0,		new Object[]{ Material.SAPLING }),
    PIG(		"Pig",		0,		new Object[]{ Material.GRASS }),
    ZEUS(		"Zeus",		20,		new Object[]{ Material.DIAMOND_BLOCK }),
    TP("tp",0,new Object[]{Material.WEB}),
	FLY("Fly",0,new Object[]{Material.WOOL});
	private String name;
	private short data;
	private int damage;
	private Object[] activate;

	EnumBowMaterial(String name, int damage, Object[] activate){
		this.name = name;
		this.data = ArrowHandler.lastData++;
		this.damage = damage;
		this.activate = activate;
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