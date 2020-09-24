package net.almostmc.FiatMachine.Commands;

import net.almostmc.FiatMachine.GUI.ChestGUIBuilder;
import net.almostmc.FiatMachine.GUI.ChestSize;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        try {
            mainMenu((Player) sender);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void mainMenu(Player player) {
        ItemMeta metaCache;

        ItemStack cancel = new ItemStack(Material.BARRIER);
        metaCache = cancel.getItemMeta();
        metaCache.setDisplayName("Cancel");
        cancel.setItemMeta(metaCache);

        ItemStack sellBlock = new ItemStack(Material.REDSTONE_BLOCK);
        metaCache = sellBlock.getItemMeta();
        metaCache.setDisplayName("Sell Ores");
        sellBlock.setItemMeta(metaCache);

        ItemStack buyBlock = new ItemStack(Material.EMERALD_BLOCK);
        metaCache = buyBlock.getItemMeta();
        metaCache.setDisplayName("Buy Ores");
        buyBlock.setItemMeta(metaCache);

        Inventory menu = new ChestGUIBuilder("Bank", ChestSize.NINE)
                .SetItem(cancel, 0)
                .SetItem(sellBlock, 3)
                .SetItem(buyBlock, 5)
                .RegisterOnClickHandler(event -> {
                    if (event.item.equals(cancel))
                        event.clicker.closeInventory();
                    else if (event.item.equals(sellBlock))
                        sellMenu(event.clicker);
                    else if (event.item.equals(buyBlock))
                        buyMenu(event.clicker);
                }).RegisterOnCloseHandler(ChestGUIBuilder::Destroy)
                .ToInventory();
        player.openInventory(menu);
    }

    public void sellMenu(Player player) {

    }

    public void buyMenu(Player player) {

    }
}
