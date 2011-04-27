package TechGuard.x1337x.Archers.Arrow;
/**
 * @author TechGuard
 */
public enum EnumBowMaterial{
	STANDARD("Normal", 4),//Normal arrow
	ICE("Ice", 7),//Turns where arrow lands to ice(ice ice baby)
	FIRE("Fire", 5),//Sets fire to target entity/block
	TNT("TNT", 0),//Creates a primed TNT block at target
	THUNDER("Thunder", 0),//Thunder lands at target
	MONSTER("Monster",0),//Why? It will spawn a random monster, not only a skeleton
        THRICE("Thrice", 3),//Fire 3 arrows
        ZOMBIE("Zombie", 0),//Hit a zombie and it turns into a giant,hit a giant and it turns into a zombie
        TREE("Tree",0),//Makes a tree grow where it lands
        PIG("Pig",0),//Same as zombie arrow but beetween pig and zombie pigman 
        ZEUS("Zeus",20);//Fire,lightning and tnt all in one! overkill for the win
	
	private String name;
	private short data;
	private int damage;
	
	EnumBowMaterial(String name, int damage){
		this.name = name;
		this.data = ArrowHandler.lastData++;
		this.damage = damage;
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
