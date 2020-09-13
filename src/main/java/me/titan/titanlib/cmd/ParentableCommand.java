package me.titan.titanlib.cmd;

import java.util.List;

public interface ParentableCommand {


	List<SubCommand> getSubCommands();
	int getChildrenPosition();
}
