/**
 * 2020 e3ndr.
 * Proudly licensed under MIT. (Don't be a dick though)
 */
package xyz.e3ndr.GuiUtil.Items;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

/**
 * The Class GuiItemStack.
 */
public class GuiItemStack extends ItemStack {

    /**
     * Instantiates a new gui item stack.
     *
     * @param material the material
     * @param name the name
     * @param amount the amount
     * @param lore the lore
     */
    public GuiItemStack(XMaterial material, String name, int amount, String... lore) {
        super(material.parseItem());
        
        ItemMeta meta = this.getItemMeta();
        
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        
        this.setAmount(amount);
        this.setItemMeta(meta);
    }

    /**
     * Instantiates a new gui item stack.
     *
     * @param item the item to clone
     */
    public GuiItemStack(ItemStack item) {
        super(item);
    }
    
}
