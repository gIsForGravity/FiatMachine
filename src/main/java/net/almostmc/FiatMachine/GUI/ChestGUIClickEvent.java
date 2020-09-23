package net.almostmc.FiatMachine.GUI;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChestGUIClickEvent {
    public ChestGUIClickEvent(ItemStack item, Player clicker) {
        this.item = item;
        this.clicker = clicker;
    }

    public final ItemStack item;
    public final Player clicker;
}
