package me.titan.titanlib;

import me.titan.titanlib.config.SimpleConfig;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface TitanPlugin {
	List<SimpleConfig> configs = new ArrayList<>();

	default void reload() {
		onPreReload();
		if (configs.isEmpty()) {
			for (Field f : getClass().getDeclaredFields()) {

				if (f.isAnnotationPresent(Config.class)) {
					try {
						SimpleConfig c = (SimpleConfig) f.get(this);
						configs.add(c);

					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}

			}
		}
		for (SimpleConfig c : configs) {
			if (!c.getFile().exists()) {
				saveResource(c.getFile().getName(), false);
			}
			c.reload();
		}
		onReload();
	}

	void onPreReload();

	void onReload();

	default void scanConfigs() {
		for (Field f : getClass().getDeclaredFields()) {

			if (f.isAnnotationPresent(Config.class)) {
				Config c = f.getAnnotation(Config.class);
				saveResource(c.name(), false);
			}

		}
	}

	void saveResource(String name, boolean replace);

}
