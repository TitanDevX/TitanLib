package me.titan.titanlib.guilib;

import me.titan.titanlib.Common;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void onInvInteract(InventoryInteractEvent e){

		if(e.getInventory().getHolder() instanceof SimpleGUI){


			SimpleGUI gui = (SimpleGUI) e.getInventory().getHolder();
			if(gui.isAllProtected() ) {

				e.setResult(Event.Result.DENY);

			}

		}else if(e.getInventory().getType() == InventoryType.PLAYER && e.getView().getTopInventory().getType() == InventoryType.CHEST && e.getView().getTopInventory().getHolder() instanceof SimpleGUI){

			SimpleGUI g = (SimpleGUI) e.getView().getTopInventory().getHolder();
			if(!g.isAllowInventoryInteractions()){
				e.setResult(Event.Result.DENY);
				//e.setCancelled(true);
			}


		}

	}

	@EventHandler(ignoreCancelled = true)
	public void onInvClick(InventoryClickEvent e) {

		if(e.getClickedInventory() == null) return;
		if (e.getClickedInventory().getHolder() instanceof SimpleGUI) {
			SimpleGUI gui = (SimpleGUI) e.getInventory().getHolder();

			if(gui.getButtons().containsKey(e.getSlot())){
				gui.getButtons().get(e.getSlot()).onClick(e.getCurrentItem(),e.getSlot(),(Player) e.getWhoClicked(),e.getClick());
			}
			if(gui.isAllProtected() || gui.getProtectedSlots().contains(e.getSlot())){

				e.setResult(Event.Result.DENY);
				e.setCancelled(true);
			}else{
				Common.tell(e.getWhoClicked(), "" + e.getAction() + e.getSlot() );
			}
			if(e.getAction().name().contains("PLACE")){
				if(!gui.onItemPlace(e)){
					e.setResult(Event.Result.DENY);
					e.setCancelled(true);
				}else{
					e.setResult(Event.Result.ALLOW);
					e.setCancelled(false);
				}

			}else if(e.getAction().name().contains("PICKUP")){
				if(!gui.onItemPickup(e)){
					e.setResult(Event.Result.DENY);
					e.setCancelled(true);
				}else{
					e.setResult(Event.Result.ALLOW);
					e.setCancelled(false);
				}
			}else if(e.getAction().name().contains("DROP")){
				if(!gui.onItemDrop(e)){
					e.setResult(Event.Result.DENY);
					e.setCancelled(true);
				}else{
					e.setResult(Event.Result.ALLOW);
					e.setCancelled(false);
				}
			}else if(e.getAction().name().contains("COLLECT")){
				if(!gui.onItemCollect(e)){
					e.setResult(Event.Result.DENY);
					e.setCancelled(true);
				}else{
					e.setResult(Event.Result.ALLOW);
					e.setCancelled(false);
				}
			}

			if(!gui.onClick(e)){
				e.setResult(Event.Result.DENY);
				e.setCancelled(true);
			}else{
				e.setResult(Event.Result.ALLOW);
				e.setCancelled(false);
			}
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void onDrag(InventoryDragEvent e){

		if(e.getInventory().getHolder() instanceof SimpleGUI){

			SimpleGUI sg = (SimpleGUI) e.getInventory().getHolder();
			if(!sg.onDrag(e)){
				e.setResult(Event.Result.DENY);
				e.setCancelled(true);
			}
		}
	}
}
