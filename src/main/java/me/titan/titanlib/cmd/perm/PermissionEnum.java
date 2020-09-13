package me.titan.titanlib.cmd.perm;

import org.bukkit.permissions.Permissible;

public interface PermissionEnum {

	String getId();

	default boolean check(Permissible permissible){

		return permissible.hasPermission(getId());

	}

}
