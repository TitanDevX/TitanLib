package me.titan.titanlib.config;

import de.leonhard.storage.Config;
import de.leonhard.storage.util.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SimpleConfig extends Config{


	public SimpleConfig(JavaPlugin plugin, File file){
		super(FileUtils.replaceExtensions(file.getName()), FileUtils.getParentDirPath(file) );

		plugin.saveResource(file.getName(),false);
		forceReload();
		//de.leonhard.storage.LightningBuilder.fromFile(file).addInputStream(plugin.getResource(file.getName())).createConfig();

	}
	protected void init(){

	}
	public void reload(){
		forceReload();
		init();
	}


}
