package TechGuard.Archers.Arrow;
/**
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
 * @author TechGuard
=======
 * @author �TechGuard
>>>>>>> e43e95f23c953e7afd9e1691f7e6d64fa13a7610
=======
 * @author �TechGuard
>>>>>>> e43e95f23c953e7afd9e1691f7e6d64fa13a7610
 */
public enum EnumBowMaterial{
	STANDARD("Normal", 4),
	ICE("Ice", 7),
	FIRE("Fire", 5),
	TNT("TNT", 0),
	THUNDER("Thunder", 0),
<<<<<<< HEAD
<<<<<<< HEAD
	SKELTION("Skeltion",0),
    THRICE("Thrice", 3),
    ZOMBIE("Zombie",0);

=======
=======
>>>>>>> e43e95f23c953e7afd9e1691f7e6d64fa13a7610
	MONSTER("Monster", 0),//Why? It will spawn a random monster, not only a skeleton
        THRICE("Thrice", 3),
        ZOMBIE("Zombie", 0);
=======
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
        TREE("Tree",0);//Makes a tree grow where it lands

>>>>>>> 7c070997cd8d191722ab6fcca92d47a1490addee
	
>>>>>>> e43e95f23c953e7afd9e1691f7e6d64fa13a7610
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
