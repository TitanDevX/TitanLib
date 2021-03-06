package me.titan.titanlib.cmd.base;


import me.titan.titanlib.config.MessagesEnum;
import me.titan.titanlib.util.Replacer;

import java.util.*;

public enum Messages implements MessagesEnum {


	No_Perms("&cYou don't have permission to do this."),
	Usage("&cUsage: {usage}."),
	Must_Be_Player("&cOnly players can use this command."),
	Must_Be_Console("&cThis command can only be executed from the console!"),
	Help_Message__Header(true
			, "&6----------------------------------------------------"
			          ,"               &b&l{label} Sub Commands"
			          ,""),
	Help_Message__Each("&8/{label} &7{sublabel} {usage}&8: &7{description}"),
	Help_Message__Footer(true,  ""
			, "&6----------------------------------------------------"),
	Player_Is_Not_Online("&cPlayer {arg} is not online!");

	final String path;

	boolean isList;

	List<String> defaults = new ArrayList<>();

	Messages(String path, String... defaults) {
		this.path = path;
		this.defaults.addAll(Arrays.asList(defaults));
	}
	Messages(boolean isList, String... defaults) {
		this.path = name().replace("__",".");
		this.isList = isList;
		this.defaults.addAll(Arrays.asList(defaults));
	}
	Messages(String defaults) {
		this.path = name().replace("__",".");

		this.defaults.add(defaults);
	}


	@Override
	public String getPath() {
		return path;
	}

	@Override
	public boolean isList() {
		return isList;
	}

	public static Map<Messages, List<String>> messages = new HashMap<>();

	public String get(){
//		if(messages.isEmpty()){
//			((CustomCommandsPlugin) Bukkit.getPluginManager().getPlugin("CustomCommands")).getCommandsConfig().init();
//		}
		return getList().get(0);

	}
	private static void putMessage(MessagesEnum msg, List<String> gg){

		messages.put((Messages) msg,gg);
	}
	public String getReplaced(String... replacer){
		return Replacer.getReplaced(get(),replacer);
	}
	public String getReplaced(Replacer replacer){
		return replacer.replace(get());
	}

	public List<String> getList(){
		List<String> msgs = messages.get(this);
		if(msgs == null){
			msgs = defaults;
		}
		if(msgs == null){
			msgs = Arrays.asList("&kAbCd&r&bSending this default message because no default message has been set for message " + name() + "!");
		}
		return msgs;
	}
	public List<String> getListReplaced(String... rep){
		return Replacer.getReplaced(messages.get(this),rep);
	}
	public List<String> getListReplaced(Replacer rep){
		return rep.replace(messages.get(this));
	}

}
