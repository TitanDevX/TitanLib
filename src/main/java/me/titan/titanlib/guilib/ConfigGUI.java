package me.titan.titanlib.guilib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

public class ConfigGUI extends SimpleGUI{

	final GUIConfig config;

	public GUIConfig getConfig() {
		return config;
	}
	public ConfigGUI(ConfigGUI config) {

		this(config.getConfig());
	}

	public ConfigGUI(GUIConfig config) {
		this.config = config;
		setSize(config.getSize());
		setTitle(config.getTitle());
		String[] replacer = getReplacer();

		for(ConfigButton btn : config.getButtons().values()){
			if(!btn.getSlots().isEmpty()){
				if(btn.getSlots().size() == 2){
					setItems(btn.getSlots().get(0),btn.getSlots().get(0),btn.getItemStackReplaced(replacer));
				}else{
					System.out.println(btn.getSlots());
					for(int s : btn.getSlots()){
						setItem(s,btn.getItemStackReplaced(replacer));
					}
				}
				continue;
			}
			setButton(btn.getSlot(), new Button() {
				@Override
				public ItemStack getItemStack() {
					return btn.getItemStackReplaced(replacer);
				}

				@Override
				public boolean onClick(ItemStack clicked, int slot, Player p, ClickType click) {
					if(btn.getOnClick() != null){
						doClicks(btn.getOnClick(),p);
					}
					return false;
				}
			});
		}

	}
	public void doClicks(List<String> cmds, Player p){
		for(String cmd : cmds){
			if(cmd.startsWith("[GUI:OPEN]")){
				String clazz = cmd.replace("[GUI:OPEN]","");
				Class<SimpleGUI> menuClazz = null;
				try {
					menuClazz = (Class<SimpleGUI>) Class.forName(clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					Logger.getGlobal().warning("Unable to init class "+ clazz + ": class not found.");
					return;

				}

				SimpleGUI inst = null;
				for(Constructor con : menuClazz.getDeclaredConstructors()){
					if(con.getParameterCount() == 0){
						try {
							inst = (SimpleGUI) con.newInstance();
						} catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
							continue;
						}
					}else if(con.getParameterCount() == 1 && con.getParameterTypes()[0] == Player.class ){
						try {
							inst = (SimpleGUI) con.newInstance(p);
						} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
							continue;
						}
					}
				}
				if(inst == null){

					Logger.getGlobal().warning("Unable to init class "+ clazz + ": empty or only player constructor parameter required.");
					return;


				}
				inst.displayTo(p);
				continue;
			}else if(cmd.startsWith("[REFRESH]")){

				refresh(this);
				continue;

			}
			cmd = cmd.replace("{player}",p.getName());
			if(cmd.startsWith("/")){
				p.performCommand(cmd.substring(1));
			}else{
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),cmd);
			}
		}
	}
	public String[] getReplacer(){
		return new String[0];
	}
}
