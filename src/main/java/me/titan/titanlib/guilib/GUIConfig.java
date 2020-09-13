package me.titan.titanlib.guilib;

import me.titan.titanlib.config.SimpleConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GUIConfig extends SimpleConfig {


	int size;
	String title;

	Map<String, ConfigButton> buttons = new HashMap<>();
	Map<Integer, String> buttonsSlots = new HashMap<>();

	public GUIConfig(JavaPlugin plugin, File file) {
		super(plugin, file);
		init();


	}

	public Map<String, ConfigButton> getButtons() {
		return buttons;
	}

	public Map<Integer, String> getButtonsSlots() {
		return buttonsSlots;
	}

	@Override
	protected void init() {

		size = getInt("Size");
		title = getString("Title");
		for(String id : singleLayerKeySet("Buttons")){
			ConfigButton c = ConfigButton.load(this,"Buttons." + id,id);
			buttons.put(id,c);
			buttonsSlots.put(c.getSlot(),id);
		}
	}

	public int getSize() {
		return size;
	}

	public String getTitle() {
		return title;
	}

	public ConfigButton getButtonAt(int slot){
		return buttons.get(buttonsSlots.get(slot));
	}

}
