package me.titan.titanlib.guilib;

import de.leonhard.storage.Yaml;
import me.titan.titanlib.util.Replacer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ConfigItem {

	final String id;
	final Material material;
	final String name;
	final List<String> lore;
	final int amount;
	final boolean glow;

	public ConfigItem(String id, Material material, String name, List<String> lore, int amount, boolean glow) {
		this.id = id;
		this.material = material;
		this.name = name;
		this.lore = lore;
		this.amount = amount;
		this.glow = glow;
	}
	ItemStack rawCache;

	public static ConfigItem load(Yaml yaml, String path,String id){

		yaml.setPathPrefix(path);
		Material mat = Material.getMaterial(yaml.getString("Material").toUpperCase());
		String name = null;
		if(yaml.contains("Name")){
			name = yaml.getString("Name");
		}
		List<String> lore = null;
		if (yaml.contains("Lore")) {
			lore = yaml.getStringList("Lore");
		}
		int amount = 1;
		if(yaml.contains("Amount")){
			amount = yaml.getInt("Amount");
		}
		boolean glow = yaml.getBoolean("Glow");
		yaml.setPathPrefix(null);
		ConfigItem c = new ConfigItem(id,mat,name,lore,amount,glow);
		return c;
	}
	public ItemStack getRawItemStack(){
		if(rawCache != null) return rawCache.clone();
		ItemBuilder b = ItemBuilder.create(material);
		if(name != null){
			b.name(name);
		}
		if(lore != null){
			b.lores(lore);
		}
		if(amount>0){
			b.amount(amount);
		}
		if(glow){
			b.glow();
		}
		rawCache = b.getItemStack();
		return rawCache;
	}
	public ItemStack getItemStackReplaced(String... replacer){

		ItemBuilder b = ItemBuilder.create(getRawItemStack());
		if(name != null){
			b.name(Replacer.getReplaced(name,replacer));
		}
		if(lore != null){
			b.lores(Replacer.getReplaced(lore,replacer));
		}
		return b.getItemStack();
	}
}
