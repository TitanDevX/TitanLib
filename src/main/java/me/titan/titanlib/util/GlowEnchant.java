package me.titan.titanlib.util;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GlowEnchant extends Enchantment {
	public GlowEnchant() {
		super(new NamespacedKey("hydrithcore","glowenchant"));
	}

	@Override
	public @NotNull String getName() {
		return " ";
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	@Override
	public @NotNull EnchantmentTarget getItemTarget() {

		return null;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean conflictsWith(@NotNull Enchantment enchantment) {
		return false;
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack itemStack) {
		return true;
	}
}
