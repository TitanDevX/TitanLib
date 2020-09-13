package me.titan.titanlib;

import me.titan.titanlib.util.NumberStringParser;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Common {

	public static void tell(CommandSender sender, String... msgs){
		for(String msg : msgs){
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',msg));
		}
	}
	public static void tell(CommandSender sender, List<String> msgs){
		for(String msg : msgs){

			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',msg));

		}
	}
	public static String colorize(String msg){
		if(msg == null) return "";
		return ChatColor.translateAlternateColorCodes('&',msg);
	}
	public static List<String> colorize(List<String> msgs){
		if(msgs == null) return null;
		List<String> list = new ArrayList<>();
		for(String msg : msgs){
			list.add(colorize(msg));
		}


		return list;
	}
	public static String formatDifference(long current, long up, boolean parse){

		long big = Math.max(current,up);
		long small = Math.min(current,up);

		long result = big - small;
		String mid = "";
		if(up > current){
			mid = "+";
		}else if(up < current){
			mid = "-";
		}else{
			mid = null;
		}
		if(mid == null){
			if(parse){
				return NumberStringParser.parseSmall(current);
			}else{
				return current + "";

			}
		}
		if(parse){
			return NumberStringParser.parseSmall(current)  + " &a"+ mid + NumberStringParser.parseSmall(result);
		}else{
			return current  + " &a"+ mid + result;

		}


	}
	public static boolean  isInteger(String raw){
		try {
			Integer.parseInt(raw);
		}catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	private static long MIN = 60;
	private static long HOURS =MIN*60;
	private static long DAYS = HOURS*24;
	private static long WEEKS = DAYS*7;
	private static long MONTHS = WEEKS*4;
	private static long YEARS = MONTHS*12;
	public static String formatTime(long seconds){
		long second = seconds % 60L;
		long minute = seconds / 60L;
		String hourMsg = "";
		if (minute >= 60L) {
			long hour = seconds / 60L / 60L;
			minute %= 60L;
			hourMsg = hour + (hour == 1L ? " hour" : " hours") + " ";
		}

		return hourMsg + (minute != 0L ? minute : "") + (minute > 0L ? (minute == 1L ? " minute" : " minutes") + " " : "") + Long.parseLong(String.valueOf(second)) + (Long.parseLong(String.valueOf(second)) == 1L ? " second" : " seconds");

	}

//	public static void sendActionBar(Player player, String message) {
//
//		IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + colorize(message) + "\"}");
//
//		PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(chat, ChatMessageType.GAME_INFO, player.getUniqueId());
//		CraftPlayer craft = (CraftPlayer) player;
//
//		craft.getHandle().playerConnection.sendPacket(packetPlayOutChat);
//	}
	public static long toSecondsFromHumanFormatShort(String string){

		// 1h. 1h,2min
		if(isInteger(string)){
			return Long.parseLong(string);
		}

		String str = string.replace(" ","");
		List<String> strsToCheck = new ArrayList<>();
		if(str.contains(",")){

			strsToCheck.addAll(Arrays.asList(str.split(",")));

		}else{
			strsToCheck.add(str);
		}
		long total = 0;
		for(String s : strsToCheck){

			String time = s.substring(s.length()-1);
			if(time.equals("n")){
				time = s.substring(s.length()-3);
			}
			int num = Integer.parseInt(s.replace(time,""));

			long subtotal = 0;
			if(time.equalsIgnoreCase("h")){
				subtotal = HOURS;
			}else
			if(time.equalsIgnoreCase("min")){
				subtotal = MIN;
			}else
			if(time.equalsIgnoreCase("d")){
				subtotal = DAYS;
			}else
			if(time.equalsIgnoreCase("w")){
				subtotal = WEEKS;
			}else
			if(time.equalsIgnoreCase("mo")) {
				subtotal = MONTHS;
			}

			if(subtotal == 0){
				total = total + num;
				continue;
			}
			total = total + subtotal * num;

		}
		Predicate<String> pre = s -> {
			List<String> matches = Arrays.asList("minutes","hours","seconds","days","weeks","months");

			for(int i = 0;i<matches.size();i++){
				String match = matches.get(i);
				if(StringUtils.contains(s,match) || StringUtils.contains(s,match.substring(0,match.length()-1))){
					return true;
				}
			}
			return false;
		};
		if(pre.test(string)){
			String[] ss = string.split(" ");
			String time = ss[0];
			int num = Integer.parseInt(ss[1]);

			long subtotal = 1;
			if(StringUtils.contains(time, "hour")){
				subtotal = HOURS;
			}

			if(StringUtils.contains(time, "minute")){
				subtotal = MIN;
			}
			if(StringUtils.contains(time, "day")){
				subtotal = DAYS;
			}
			if(StringUtils.contains(time, "week")){
				subtotal = WEEKS;
			}
			if(StringUtils.contains(time, "month")){
				subtotal = MONTHS;
			}

			total = total + subtotal * num;
		}
		return total;

	}
}
