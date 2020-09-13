package me.titan.titanlib.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {
	public static Location LocfromString(String str){
		if(str == null){
			throw new IllegalArgumentException("String to get location from must not be null!");
		}
		str = str.trim();
		String[] args = str.split(",");
		if(args.length < 4) return null;

		String wn = args[0];
		double x = Double.parseDouble(args[1]);
		double y = Double.parseDouble(args[2]);
		double z = Double.parseDouble(args[3]);
		Location loc = new Location(Bukkit.getWorld(wn),x,y,z);

		if(args.length == 6){
			float yaw = Float.parseFloat(args[4]);
			float pit = Float.parseFloat(args[5]);

			loc.setYaw(yaw);
			loc.setPitch(pit);
		}
		return loc;

	}
	public static String LocToString(Location loc){
		if(loc == null){
			new IllegalArgumentException("Location cannot be null!").printStackTrace();

			return null;
		}
		return String.format("%s, %s, %s, %s, %s, %s", loc.getWorld().getName(),loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());
	}
}
