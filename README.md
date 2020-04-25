# GuiUtil
A small utility library for bukkit.

How to use:
```java
GuiItemStack button = new ClickableItemStack(XMaterial.OAK_BUTTON, "Test", 1, new ClickListener() {
    @Override
    public void onClick(InventoryClickEvent e) {
        System.out.println("Hello world!");
    }
}, "Clicking this button is dangerous!");

Window window = new Window("Example Inv", 9, yourPlugin, button);

window.open(player, new WindowListener() {
    @Override
    public void onClick(InventoryClickEvent e) {
        System.out.println("Slot clicked: " + e.getRawSlot());
    }
    
    @Override
    public void onClose(InventoryCloseEvent e) {
        System.out.println("Inventory closed!");
    }
    
});
```
