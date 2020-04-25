/**
 * 2020 e3ndr.
 * Proudly licensed under MIT. (Don't be a dick though)
 */
package xyz.e3ndr.GuiUtil.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import xyz.e3ndr.GuiUtil.Items.GuiItemStack;

/**
 * The Class SharedWindow.
 */
public class SharedWindow extends Window {
    /**
     * Gets the shared Inventory.
     *
     * @return the shared Inventory
     */
    private @Getter Inventory shared;
    
    /**
     * Instantiates a new shared window.
     * This type of window has it's items shared across all instances.
     *
     * @param name the name
     * @param size the size
     * @param plugin the plugin
     */
    public SharedWindow(String name, int size, JavaPlugin plugin) {
        super(name, size, plugin);
        
        this.shared = Bukkit.createInventory(null, size, name);
    }
    
    /**
     * Gets a bukkit inventory shared by the viewers.
     *
     * @return the bukkit inventory
     */
    @Override
    public Inventory getBukkitInventory() {
        return this.shared;
    }
    
    @Override
    public Window setItems(GuiItemStack... items) {
        this.shared.setContents(items);
        
        return this;
    }
    
    public ItemStack[] getItems() {
        return this.shared.getContents();
    }

}
