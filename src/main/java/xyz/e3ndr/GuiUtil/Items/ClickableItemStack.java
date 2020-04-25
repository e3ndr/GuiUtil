/**
 * 2020 e3ndr.
 * Proudly licensed under MIT. (Don't be a dick though)
 */
package xyz.e3ndr.GuiUtil.Items;

import org.bukkit.inventory.ItemStack;

import com.cryptomorin.xseries.XMaterial;

import lombok.Getter;
import xyz.e3ndr.GuiUtil.Listeners.ClickListener;

@Getter
public class ClickableItemStack extends GuiItemStack {
    private ClickListener clickListener;
    
    public ClickableItemStack(XMaterial material, String name, int amount, ClickListener listener, String... lore) {
        super(material, name, amount, lore);
        
        this.clickListener = listener;
    }
    
    public ClickableItemStack(ItemStack item, ClickListener listener) {
        super(item);
        
        this.clickListener = listener;
    }

}
