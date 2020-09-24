package net.almostmc.FiatMachine.GUI;

import net.almostmc.FiatMachine.FiatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChestGUIBuilder implements Listener {
    public ChestGUIBuilder(String name, ChestSize size) {
        switch (size) {
            case NINE:
                inventory = Bukkit.createInventory(null, 9, name);
                break;
            case TWENTY_SEVEN:
                inventory = Bukkit.createInventory(null, 27, name);
                break;
            case FIFTY_FOUR:
                inventory = Bukkit.createInventory(null, 54, name);
                break;
        }
        Bukkit.getPluginManager().registerEvents(this, FiatPlugin.singleton);
    }

    private Inventory inventory;

    private List<Handler<ChestGUIClickEvent>> onClickHandlers = new ArrayList<>();
    private List<Handler<ChestGUIBuilder>> onCloseHandlers = new ArrayList<>();

    public ChestGUIBuilder SetItem(final ItemStack item, int slot) {
        inventory.setItem(slot, item);
        return this;
    }

    public ChestGUIBuilder RemoveItem(int slot) {
        inventory.clear(slot);
        return this;
    }

    public ChestGUIBuilder RegisterOnClickHandler(final Handler<ChestGUIClickEvent> onClick) {
        onClickHandlers.add(onClick);
        return this;
    }

    public ChestGUIBuilder RegisterOnCloseHandler(final Handler<ChestGUIBuilder> onClose) {
        onCloseHandlers.add(onClose);
        return this;
    }

    public Inventory ToInventory() {
        return inventory;
    }

    public void Destroy() {
        onClickHandlers = null;
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        if (Objects.equals(event.getClickedInventory(), inventory)) {
            for (Handler<ChestGUIClickEvent> eventHandler : onClickHandlers) {
                eventHandler.onEvent(new ChestGUIClickEvent(event.getCurrentItem(), (Player) event.getWhoClicked()));
            }
        }
    }

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent event) {
        if (Objects.equals(event.getInventory(), inventory)) {

        }
    }
}
