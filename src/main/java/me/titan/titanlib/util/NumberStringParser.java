package me.titan.titanlib.util;

public class NumberStringParser {
	public static long BILLION = 1_000_000_000;
	public static long MILLION = 1_000_000;
	public static long KILO =  1_000;

	public static String B_BIG = "billion";
	public static String B_SMALL = "b";
	public static String M_BIG = "million";
	public static String M_SMALL = "m";
	public static String K = "k";
	public static String fixString(String value, String add) {

		String gg = value;
		if (gg.contains(".")) {
			String gg2 = gg.substring(gg.indexOf(".") + 1);
			if (gg2.equalsIgnoreCase("0")) {
				gg = gg.replace("." + gg2, "");
			} else if (gg2.length() > 2) {
				gg = gg.replace(gg2, gg2.substring(0, 2));
			}
		}
		return gg + add;
	}
	public static String parseSmall(long t){
		String r = "";
		// 3.0
		// 3.1212

		if(t >= BILLION){

			r = fixString(((double) t / BILLION) +"" , B_SMALL );
		}else if(t >= MILLION){
			r = fixString(((double) t / MILLION) +"" , M_SMALL);
		}else if(t >= KILO){
			r = fixString(((double) t /KILO)+"" , K);
		}else{
			r = t+"";
		}
		return r;
	}
	public static String parseBig(long t){
		String r = "";
		if(t >= BILLION){

			r = fixString(((double) t / BILLION) +"" , B_BIG );
		}else if(t >= MILLION){
			r = fixString(((double) t / MILLION) +"" , M_BIG);
		}else if(t >= KILO){
			r = fixString(((double) t /KILO) +"" , K);
		}else{
			r = t+"";
		}
		return r;
	}
}
