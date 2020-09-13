package me.titan.titanlib.cmd.perm;

import me.titan.titanlib.TitanLib;

public enum ExamplePermissionEnum implements AdvancedPermissionEnum {

	EXAMPLE_PERM;

	@Override
	public String getLabel() {
		return TitanLib.getPlugin().getDescription().getName().toLowerCase();
	}


}
