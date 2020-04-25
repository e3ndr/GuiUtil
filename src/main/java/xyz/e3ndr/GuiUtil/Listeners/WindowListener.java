/**
 * 2020 e3ndr.
 * Proudly licensed under MIT. (Don't be a dick though)
 */
package xyz.e3ndr.GuiUtil.Listeners;

import org.bukkit.event.inventory.InventoryCloseEvent;

public interface WindowListener extends ClickListener {
    default void onClose(InventoryCloseEvent e) {}

}
