package me.titan.titanlib.guilib;

import me.titan.titanlib.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SimpleGUI implements InventoryHolder {

	Map<Integer, ItemStack> items = new HashMap<>();
	Map<Integer, Button> buttons = new HashMap<>();
	Inventory inv;
	int size;
	String title;

	List<Integer> protectedSlots = new ArrayList<>();
	boolean allProtected;

	boolean allowInventoryInteractions;

	public boolean isAllowInventoryInteractions() {
		return allowInventoryInteractions;
	}

	public void setAllowInventoryInteractions(boolean allowInventoryInteractions) {
		this.allowInventoryInteractions = allowInventoryInteractions;
	}

	public void setAllProtected(boolean allProtected) {
		this.allProtected = allProtected;
	}

	public List<Integer> getProtectedSlots() {
		return protectedSlots;
	}

	public boolean isAllProtected() {
		return allProtected;
	}

	public void addProtectedSlots(int from, int to){
		for(int i =from;i<to;++i){
			protectedSlots.add(i);
		}
	}
	public void addProtectedSlot(int... slots){
		for(int i : slots){
			protectedSlots.add(i);
		}
	}
	public void setProtectedSlot(Integer... exceptions){
		List<Integer> exp = Arrays.asList(exceptions);
		for(int i =0; i< size;++i){

			if(!exp.contains(i)) {
				addProtectedSlot(i);
			}
		}
	}

	public void displayTo(Player p){
		p.openInventory(getInventory());
	}
	public String getTitle() {
		return title;
	}
	public void setFillings(ItemStack fillings, Integer... exceptions){

		List<Integer> exp = Arrays.asList(exceptions);
		for(int i =0; i< size;++i){

			if(!exp.contains(i)) {
				setItem(i, fillings);
			}
		}


	}
	public boolean onItemPlace(InventoryClickEvent e){
		return !isAllProtected() && !protectedSlots.contains(e.getSlot());
	}
	public boolean onItemPickup(InventoryClickEvent e){
		return  !isAllProtected() && !protectedSlots.contains(e.getSlot());
	}
	public boolean onItemDrop(InventoryClickEvent e){
		return  !isAllProtected() && !protectedSlots.contains(e.getSlot());
	}
	public boolean onItemCollect(InventoryClickEvent e){
		return  !isAllProtected() && !protectedSlots.contains(e.getSlot());
	}
	public boolean onClick(InventoryClickEvent e){
		return  !isAllProtected() && !protectedSlots.contains(e.getSlot());
	}
	public boolean onDrag(InventoryDragEvent e){
		return e.getInventorySlots().stream().noneMatch((i)-> protectedSlots.contains(i));
	}

	public void setTitle(String title) {
		this.title = title;

	}

	public void clearSlots(Integer... slots){
		for(int i : slots){
			setItem(i,null);
		}
	}
	public void setSize(int size) {
		this.size = size;

	}
	public <T extends SimpleGUI> void refresh(T gui)  {

		Class<? extends SimpleGUI> clazz =gui.getClass();
		Constructor con = null;
		try {
			con = clazz.getDeclaredConstructor(clazz);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		con.setAccessible(true);
		try {
			((SimpleGUI)con.newInstance(gui)).displayTo(Bukkit.getPlayer("TitanDev"));
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public int getSize() {
		return size;
	}

	public void addItem(ItemStack item){
		items.put(items.size(),item );
		if(inv != null){
			inv.addItem(item);
		}
	}
	public void setItem(int slot, ItemStack item){
		items.put(slot,item) ;
		if(inv != null){
			inv.setItem(slot,item);
		}
	}
	public void setItems(int slotFrom, int slotTo, ItemStack item){
		for(int slot =slotFrom;slot<slotTo;slot++) {
			items.put(slot, item);
			if (inv != null) {
				inv.setItem(slot, item);
			}
		}
	}
	public void updateInv(Inventory inv){
		if(inv == null) return;
		this.inv = inv;
	}
	public void setButton(int slot, Button btn){
		buttons.put(slot,btn);
		setItem(slot,btn.getItemStack());
	}

	public Map<Integer, Button> getButtons() {
		return buttons;
	}

	@Override
	public Inventory getInventory(){

		if(inv != null) return inv;
		inv = Bukkit.createInventory(this,size, Common.colorize(title));

		for(Map.Entry<Integer, ItemStack> en : items.entrySet()){
			inv.setItem(en.getKey(),en.getValue());

		}
		return inv;
	}



}
