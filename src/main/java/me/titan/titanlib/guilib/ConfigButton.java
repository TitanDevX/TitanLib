package me.titan.titanlib.guilib;

import de.leonhard.storage.Yaml;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class ConfigButton extends ConfigItem{
	final int slot;
	final List<String> onClick;
	List<Integer> slots = new ArrayList<>();

	public ConfigButton(String id, Material material, String name, List<String> lore, int amount, boolean glow, int slot, List<String> onClick) {
		super(id, material, name, lore, amount, glow);
		this.slot = slot;
		this.onClick = onClick;

	}

	public int getSlot() {
		return slot;
	}

	public List<String> getOnClick() {
		return onClick;
	}

	public List<Integer> getSlots() {
		return slots;
	}

	public static ConfigButton load(Yaml yaml, String path, String id){
		yaml.setPathPrefix(path);
		Material mat = Material.getMaterial(yaml.getString("Material").toUpperCase());
		System.out.println(path +  " " + mat);
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
		int slot = 0;
		if(yaml.contains("Slot")){
			slot = yaml.getInt("Slot");
			System.out.println(path + " " + slot);
		}
		List<String> onClick = null;
		if(yaml.contains("OnClick")){
			onClick = yaml.getStringList("OnClick");
		}


		ConfigButton c = new ConfigButton(id,mat,name,lore,amount,glow, slot,onClick);
		if(yaml.contains("Slots")){

			List<Integer> l = new ArrayList<>();
			String slots = yaml.getString("Slots");
			slots = slots.trim().replace("[","").replace("]","");
			String[] args = slots.split(",");
			for(String s : args){
				l.add(Integer.parseInt(s));
			}
			c.setSlots(l);
		}
		yaml.setPathPrefix(null);

		return c;
	}

	public void setSlots(List<Integer> slots) {
		this.slots = slots;
	}
}
