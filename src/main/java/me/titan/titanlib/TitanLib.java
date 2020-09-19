package me.titan.titanlib;

import me.titan.titanlib.cmd.CommandsRegistrar;
import me.titan.titanlib.guilib.InventoryListener;
import me.titan.titanlib.util.GlowEnchant;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TitanLib {

	private static TitanLib instance;
	final JavaPlugin plugin;
	public GlowEnchant glowEnchant;
	CommandsRegistrar commandsRegistrar;

	public TitanLib(JavaPlugin plugin) {
		this.plugin = plugin;
		registerListeners(plugin);
		glowEnchant = new GlowEnchant();
	}


	public static TitanLib getInstance() {

		return instance;
	}

	public CommandsRegistrar getCommandsRegistrar() {
		return commandsRegistrar;
	}

	public static void setPlugin(JavaPlugin plugin) {

		instance = new TitanLib(plugin);
	}

	public static Plugin getPlugin() {
		return getInstance().plugin;
	}

	private void registerListeners(Plugin plugin) {

		Bukkit.getPluginManager().registerEvents(new InventoryListener(),plugin);
	}



}
