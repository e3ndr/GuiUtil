/**
 * 2020 e3ndr.
 * Proudly licensed under MIT. (Don't be a dick though)
 */
package xyz.e3ndr.GuiUtil.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import xyz.e3ndr.GuiUtil.Items.ClickableItemStack;
import xyz.e3ndr.GuiUtil.Items.GuiItemStack;
import xyz.e3ndr.GuiUtil.Listeners.BlankListener;
import xyz.e3ndr.GuiUtil.Listeners.WindowListener;

/**
 * Instantiates a new window.
 *
 * @param name the name
 * @param size the size
 * @param plugin the plugin
 */
@RequiredArgsConstructor
public class Window {
    private GuiItemStack[] items;
    private final String name;
    private final int size;
    private final JavaPlugin plugin;
    
    /**
     * Instantiates a new window.
     *
     * @param name the name
     * @param size the size
     * @param plugin the plugin
     * @param items the items
     */
    public Window(String name, int size, JavaPlugin plugin, GuiItemStack... items) {
        this(name, size, plugin);
        
        this.setItems(items);
    }
    
    /**
     * Show the inventory to the player.
     *
     * @param player the player
     */
    public void open(Player player) {
        this.open(player, new BlankListener());
    }

    /**
     * Show the inventory to the player.
     *
     * @param player the player
     * @param listener the listener for other click events not caught by the item's associated {@link ClickableItemStack#getClickListener()} or blank slots
     */
    public void open(Player player, WindowListener listener) {
        Inventory bukkit = this.getBukkitInventory();
        
        Bukkit.getPluginManager().registerEvents(new BukkitListener(player, bukkit, listener), this.plugin);
        player.openInventory(bukkit);
    }
    
    /**
     * Sets the item slot.
     *
     * @param slot the slot
     * @param item the item
     */
    public void setItemSlot(int slot, GuiItemStack item) {
        this.checkItems();
        
        this.items[slot] = item;
    }
    
    private void checkItems() {
        if (this.items == null) {
            this.items = new GuiItemStack[this.size];
        }
    }
    
    /**
     * Gets the items held by the window.
     *
     * @return the items
     */
    public ItemStack[] getItems() {
        return this.items;
    }
    
    /**
     * Sets the items in the window.
     *
     * @param items the items
     * @return the window
     */
    public Window setItems(GuiItemStack... items) {
        if (items.length > this.size) {
            throw new IllegalArgumentException("New item array length is greater than the internal inventory's size.");
        } else {
            this.items = new GuiItemStack[this.size];
            
            for (int i = 0; i != items.length; i++) {
                this.items[i] = items[i];
            }
        }
        
        return this;
    }
    
    /**
     * Gets an {@link Inventory} represented by the items held by the window.
     *
     * @return the bukkit inventory
     */
    public Inventory getBukkitInventory() {
        Inventory inventory = Bukkit.createInventory(null, this.size, this.name);
        
        this.checkItems();
        inventory.setContents(this.items);
        
        return inventory;
    }
    
    /**
     * Gets a {@link GuiItemStack}.
     *
     * @param item the item
     * @return the gui item
     */
    public GuiItemStack getGuiItem(ItemStack item) {
        if ((item != null) && !item.getType().equals(Material.AIR)) {
            for (GuiItemStack gis : this.items) {
                if (gis.equals(item)) return gis;
            }
        }
        
        return null;
    }
    
    @RequiredArgsConstructor
    private class BukkitListener implements Listener {
        private boolean closed = false;
        private @NonNull Player player;
        private @NonNull Inventory bukkitInv;
        private @NonNull WindowListener listener;
        
        @EventHandler
        public void onClick(InventoryClickEvent e) {
            if (this.closed) {
                e.getHandlers().unregister(this);
            } else if ((e.getClickedInventory() != null) && e.getClickedInventory().equals(this.bukkitInv)) {
                GuiItemStack clicked = getGuiItem(e.getCurrentItem());
                
                e.setCancelled(true);
                
                if ((clicked != null) && (clicked instanceof ClickableItemStack)) {
                    ClickableItemStack clickable = (ClickableItemStack) clicked;
                    
                    clickable.getClickListener().onClick(e);
                } else {
                    this.listener.onClick(e);
                }
            }
        }
        
        @EventHandler
        public void onClose(InventoryCloseEvent e) {
            if (e.getInventory().equals(this.bukkitInv)) {
                this.closed = true;
                e.getHandlers().unregister(this);
                
                this.listener.onClose(e);
            }
        }
        
    }

}