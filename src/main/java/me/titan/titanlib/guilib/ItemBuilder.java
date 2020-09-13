package me.titan.titanlib.guilib;

import me.titan.titanlib.Common;
import me.titan.titanlib.TitanLib;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

	ItemStack current;

	public ItemBuilder(ItemStack current) {
		this.current = current;
	}

	public static ItemBuilder create(Material material){

		ItemStack item = new ItemStack(material);
		return new ItemBuilder(item);

	}
	public static ItemBuilder create(Material material, int amount){

		ItemStack item = new ItemStack(material,amount);
		return new ItemBuilder(item);

	}

	public static ItemBuilder create(Material material, short damage){

		ItemStack item = new ItemStack(material, 1, damage);
		return new ItemBuilder(item);

	}
	public ItemBuilder book(String title, String author, String... pages){
		BookMeta b = (BookMeta) getItemMeta();
		b.setPages(Common.colorize(Arrays.asList(pages)));
		b.setAuthor(Common.colorize(author));
		b.setTitle(Common.colorize(title));
		current.setItemMeta(b) ;
		return this;
	}
	public ItemBuilder openBook(Player p){
		p.openBook(current);
		return this;
	}
	public static ItemBuilder create(Material material, int amount, short damage){

		ItemStack item = new ItemStack(material, amount, (short) damage);
		return new ItemBuilder(item);

	}
	public static ItemBuilder create(Material material, int amount, byte data){

		ItemStack item = new ItemStack(material, amount, (short) 0,data);
		return new ItemBuilder(item);
	}
	public static ItemBuilder create(Material material, int amount, short damage, byte data){

		ItemStack item = new ItemStack(material, amount, damage,data);
		return new ItemBuilder(item);

	}

	public static ItemBuilder create(Material material, byte data){

		ItemStack item = new ItemStack(material, 1, (short) 0,data);
		return new ItemBuilder(item);

	}
	public static ItemBuilder create(ItemStack item){

		return new ItemBuilder(item);

	}
	public ItemBuilder data(byte data){
		current.setData(current.getType().getNewData(data));

		return this;
	}
	public ItemBuilder glow(){

		current.addEnchantment(TitanLib.getInstance().glowEnchant,1);
		return this;

	}
	public ItemBuilder damage(short damage){
		current.setDurability(damage);

		return this;
	}

	public ItemMeta getItemMeta(){
		ItemMeta m = current.getItemMeta();
		if(m == null){
			m = Bukkit.getItemFactory().getItemMeta(current.getType());
		}
		current.setItemMeta(m);
		return m;
	}
	public ItemBuilder itemMeta(ItemMeta meta){
		current.setItemMeta(meta);
		return this;
	}
	public ItemBuilder amount(int amount){
		current.setAmount(amount);
		return this;
	}
	public ItemBuilder color( Color color){
		if(!current.getType().name().contains("LEATHER_")) current.setType(Material.LEATHER_HELMET);
		LeatherArmorMeta meta = (LeatherArmorMeta) getItemMeta();
		meta.setColor(color);
		current.setItemMeta(meta);
		return this;
	}
//	public ItemBuilder nbt(String key, String value){
//		this.current = Utils1_16.setNBT(key,value,current);
//		return this;
//	}
//	public ItemBuilder headURL(String url){
//		current.setType(Material.PLAYER_HEAD);
//		this.current.setItemMeta(Utils1_16.setSkullURL(getItemMeta(),url));
//		return this;
//	}
	public ItemBuilder potionType(PotionType type){

		PotionMeta meta = (PotionMeta) getItemMeta();
		meta.setBasePotionData(new PotionData(type));

		current.setItemMeta(meta);
		return this;

	}
	public ItemBuilder name(String name){
		ItemMeta m = getItemMeta();
		m.setDisplayName(Common.colorize(name));
		return itemMeta(m);
	}
	public ItemBuilder lores(String... lores){
		ItemMeta m = getItemMeta();
		m.setLore(Common.colorize(Arrays.asList(lores)));
		return itemMeta(m);
	}
	public ItemBuilder lore(String... lores){
		ItemMeta m = getItemMeta();
		List<String> lore = new ArrayList<>(m.getLore() != null ? m.getLore() : new ArrayList<>());
				lore.addAll(Arrays.asList(lores));
		m.setLore(Common.colorize(lore));
		return itemMeta(m);
	}
	public ItemBuilder lores(List<String> lores){
		ItemMeta m = getItemMeta();
		m.setLore(Common.colorize(lores));
		return itemMeta(m);
	}
	public ItemStack getItemStack(){
		return current.clone();
	}

}
