package me.titan.titanlib.guilib;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class Button {

	public abstract ItemStack getItemStack();

	public abstract boolean onClick(ItemStack clicked, int slot, Player p, ClickType click);
}
