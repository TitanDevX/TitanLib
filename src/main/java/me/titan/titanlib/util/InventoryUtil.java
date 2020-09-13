package me.titan.titanlib.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


public class InventoryUtil {


	public static boolean hasEnough(final Material item, final int price, final PlayerInventory inv) {

		if (!inv.contains(item)) {


			return false;
		} else if (!inv.contains(item, price)) {

//			buyer.closeInventory();
//			ChatManager.sendAffordMessageError(buyer, this);
			return false;

		}
		int amount = 0;
		for (final int i : inv.all(item).keySet()) {
			amount += inv.getItem(i).getAmount();
			if (amount >= price) {
				//pay(buyer);
				return true;

			}
		}
		return false;
	}
	public static boolean hasEnough(final ItemStack item, final int price, final PlayerInventory inv) {

		if (!inv.contains(item)) {


			return false;
		} else if (!inv.containsAtLeast(item, price)) {

//			buyer.closeInventory();
//			ChatManager.sendAffordMessageError(buyer, this);
			return false;

		}
		int amount = 0;
		for (final int i : inv.all(item).keySet()) {
			amount += inv.getItem(i).getAmount();
			if (amount >= price) {
				System.out.println("TF TF " + amount + " " + price);
				//pay(buyer);
				return true;

			}
		}
		return false;
	}

	public static void buy(final int price, Material material, final Player player) {

			int a = 0;
			int slot = -1;
			for (ItemStack player_item : player.getInventory().getContents()) {
				slot++;
				if (player_item != null && player_item.getType() != Material.AIR) {
					if (player_item.getType() == material) {

						player_item = player_item.clone(); //New
						player_item.setAmount(Math.min(player_item.getAmount(), price - a)); //New

						a += player_item.getAmount();
						//remove(p, player_item); //Old
						player.getInventory().removeItem(player_item); //New

						if (a >= price) { //Reached amount. Can stop!
							break;
						}
					}
				}
			}


	}
	public static void buy(int price, ItemStack material, final Player player) {

		int slot = 0;
		it1: for (ItemStack item : player.getInventory().getContents()) {
			slot++;
			if (item != null && item.getType() != Material.AIR) {
				if (item.isSimilar(material)) {
					int amount = item.getAmount();
					if(item.getAmount() >= price){
						amount-=price;

						if(amount <= 0){
							player.getInventory().setItem(slot,null);
							continue it1;
						}else
						 item.setAmount(amount);
					}else {
						price-=amount;
						player.getInventory().setItem(slot,null);
					}
					if(price <= 0) break;

//					for(int p= price;p>=0;p--){
//
//						int amount = item.getAmount();
//						amount--;
//
//						if(amount <= 0){
//							player.getInventory().setItem(slot,null);
//							continue it1;
//						}else
//						 item.setAmount(amount);
//
//
//					}

				}
			}
		}


	}

}
