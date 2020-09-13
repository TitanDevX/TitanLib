package me.titan.titanlib.cmd;

import me.titan.titanlib.cmd.base.CommandRequirements;

import java.util.ArrayList;
import java.util.List;

public class SubCommand {


	List<String> aliases = new ArrayList<>();

	// default position is 0, works in /maincommand subcommand <args..>
	int position = 0;

	CommandRequirements requirements;



}
