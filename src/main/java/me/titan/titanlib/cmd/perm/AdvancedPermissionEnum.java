package me.titan.titanlib.cmd.perm;

public interface AdvancedPermissionEnum extends PermissionEnum{

	String getLabel();
	String name();
	@Override
	default String getId(){
		return getLabel() + "." + name().toLowerCase().replace("_",".");
	}

}
