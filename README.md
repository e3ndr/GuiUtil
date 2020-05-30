# GuiUtil
A small utility library for bukkit.  
[![](https://jitpack.io/v/e3ndr/GuiUtil.svg)](https://jitpack.io/#e3ndr/GuiUtil)  
  
  
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

Maven:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.e3ndr</groupId>
        <artifactId>GuiUtil</artifactId>
        <version>Tag</version>
    </dependency>
</dependencies>
```
