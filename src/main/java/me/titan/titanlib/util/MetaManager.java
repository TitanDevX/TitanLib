package me.titan.titanlib.util;

import me.titan.titanlib.TitanLib;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.Metadatable;

public class MetaManager {

	public static void addMetadata(Metadatable en, String key, Object value) {
		en.setMetadata(key, new FixedMetadataValue(TitanLib.getPlugin(),
				value));
	}

	public static Object getMetadataObject(Metadatable en, String key) {
		return en.getMetadata(key).get(0);
	}

	public static void setMetadataObject(Metadatable en, String key, Object nobject) {

		en.removeMetadata(key, TitanLib.getPlugin());
		addMetadata(en, key, nobject);

	}

	public static void removeMetadata(Metadatable en, String key) {
		en.removeMetadata(key, TitanLib.getPlugin());

	}

}
