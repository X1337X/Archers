package TechGuard.x1337x.Archers.Arrow;

import org.bukkit.Material;

public enum EnumBowMaterial
{
  STANDARD("Normal", new Object[] { Material.LOG, Material.WOOD }, "The basic arrow"), 
  ICE("Ice", new Object[] { Material.SNOW, Material.ICE }, "Spreads snow around the arrow"), 
  FIRE("Fire",new Object[] { Material.FIRE }, "Sets fire to what it hits"), 
  TNT("TNT", new Object[] { Material.TNT }, "Explodes on impact"), 
  THUNDER("Thunder", new Object[] { Material.REDSTONE_ORE }, "Makes thunder strike on impact"), 
  MONSTER("Monster", new Object[] { Material.MOB_SPAWNER }, "Spawns a random mob on impact"), 
  THRICE("Thrice", new Object[] { Material.DISPENSER }, "Shoots 3 arrows instead of one"), 
  ZOMBIE("Zombie", new Object[] { Material.LAPIS_BLOCK }, "Morphs giants and zombie beetween each other"), 
  TREE("Tree", new Object[] { Material.SAPLING }, "Spawns a tree where it lands"), 
  PIG("Pig", new Object[] { Material.GRASS }, "Morps pig and zombie pigman beetween each other"), 
  ZEUS("Zeus", new Object[] { Material.DIAMOND_BLOCK }, "Makes fire lightning and tnt on impact"), 
  TP("Tp", new Object[] { Material.WEB }, "Tps you to where it lands"), 
  FLY("Fly", new Object[] { Material.WOOL }, "Hit a mob (including players) and they go 20 block up"), 
  TORCH("Torch", new Object[] { Material.COAL_ORE }, "Spawns a single torch where it lands"), 
  WEB("Web",new Object[] { Material.IRON_ORE }, "Makes a square of 4 webs where it lands"), 
  STEAL("Steal", new Object[] { Material.CHEST }, "Steals the hits player item in hand and throws it out"), 
  SNIPER("Sniper",new Object[] { Material.DIAMOND_ORE }, "One shot super fast arrow"),
  ROCKET("Rocket",new Object[] {Material.OBSIDIAN},"Thrice arrow,with TNT!"),
  STORM("Storm",new Object[] {Material.SOUL_SAND},"Turns storms off and on");


  private String name;
  private String description;
  private short data;

  private Object[] activate;

  private EnumBowMaterial(String name, Object[] activate, String description) {
	  this.name = name;
    this.data = (ArrowHandler.lastData++);
    this.activate = activate;
    this.description = description; }

  public String getName()
  {
    return this.name;
  }

  public short getDataValue() {
    return this.data;
  }


  public Object[] getBlocks() {
    return this.activate;
  }
  public String getDescription() {
    return this.description;
  }

  public static EnumBowMaterial fromData(short data) {
    for (EnumBowMaterial material : values()) {
      if (material.data == data) {
        return material;
      }
    }
    return STANDARD;
  }

  public static EnumBowMaterial fromName(String name) {
    for (EnumBowMaterial material : values()) {
      if (material.name.toLowerCase().startsWith(name.toLowerCase())) {
        return material;
      }
    }
    return STANDARD;
  }
}