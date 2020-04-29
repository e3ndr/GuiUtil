/**
 * 2020 e3ndr.
 * Proudly licensed under MIT. (Don't be a dick though)
 */
package xyz.e3ndr.GuiUtil.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public interface WindowListener extends ClickListener {
    default void onClose(InventoryCloseEvent e) {}
    default void onOpen(Player player, Inventory inventory) {}
    
}
