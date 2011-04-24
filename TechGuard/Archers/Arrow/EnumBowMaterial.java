package TechGuard.Archers.Arrow;
/**
 * @author ´TechGuard
 */
public enum EnumBowMaterial{
	STANDARD("Normal", 4),
	ICE("Ice", 7),
	FIRE("Fire", 5),
	TNT("TNT", 0),
	THUNDER("Thunder", 0),
	SKELTION("Skeltion",0);
	
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
