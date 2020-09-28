package net.almostmc.FiatMachine.GUI;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChestGUIClickEvent {
    public ChestGUIClickEvent(@Nullable ItemStack item, @NotNull Player clicker) {
        this.item = item;
        this.clicker = clicker;
    }

    public final @Nullable ItemStack item;
    public final @NotNull Player clicker;
}
