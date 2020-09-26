package net.almostmc.FiatMachine.Commands.BankCommand;

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
                        chooseOreMenu(event.clicker, BankTransactionType.SELL);
                    else if (event.item.equals(buyBlock))
                        chooseOreMenu(event.clicker, BankTransactionType.BUY);
                }).RegisterOnCloseHandler(ChestGUIBuilder::Destroy)
                .ToInventory();
        player.openInventory(menu);
    }

    private void chooseOreMenu(Player player, BankTransactionType transactionType) {
        String prefix;
        switch (transactionType) {
            case BUY:
                prefix = "Buy";
                break;
            case SELL:
                prefix = "Sell";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + transactionType);
        }
        chooseOreMenu(player, transactionType, prefix);
    }

    private void chooseOreMenu(Player player, BankTransactionType transactionType, String prefix) {
        ItemMeta metaCache;

        ItemStack cancel = new ItemStack(Material.BARRIER);
        metaCache = cancel.getItemMeta();
        metaCache.setDisplayName("Cancel");
        cancel.setItemMeta(metaCache);

        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        metaCache = iron.getItemMeta();
        metaCache.setDisplayName(prefix + " Iron");
        iron.setItemMeta(metaCache);

        ItemStack gold = new ItemStack(Material.GOLD_INGOT);
        metaCache = gold.getItemMeta();
        metaCache.setDisplayName(prefix + " Gold");
        gold.setItemMeta(metaCache);

        ItemStack diamond = new ItemStack(Material.DIAMOND);
        metaCache = diamond.getItemMeta();
        metaCache.setDisplayName(prefix + " Diamond");
        diamond.setItemMeta(metaCache);

        Inventory menu = new ChestGUIBuilder("Bank", ChestSize.NINE)
                .SetItem(cancel, 0)
                .SetItem(iron, 3)
                .SetItem(gold, 4)
                .SetItem(diamond, 5)
                .RegisterOnClickHandler(event -> {
                    if (event.item.equals(cancel))
                        event.clicker.closeInventory();
                    else if (event.item.equals(iron))
                        oreAmountMenu(event.clicker, transactionType, BankItemType.IRON);
                    else if (event.item.equals(gold))
                        oreAmountMenu(event.clicker, transactionType, BankItemType.GOLD);
                    else if (event.item.equals(diamond))
                        oreAmountMenu(event.clicker, transactionType, BankItemType.DIAMOND);
                }).RegisterOnCloseHandler(ChestGUIBuilder::Destroy)
                .ToInventory();
        player.openInventory(menu);
    }

    private void oreAmountMenu(Player player, BankTransactionType transactionType, BankItemType type) {
        Material oreMat;
        Material oreMatBlock;
        String oreName;
        
        switch (type) {
            case IRON:
                oreMat = Material.IRON_INGOT;
                oreMatBlock = Material.IRON_BLOCK;
                oreName = "Iron";
                break;
            case GOLD:
                oreMat = Material.GOLD_INGOT;
                oreMatBlock = Material.GOLD_BLOCK;
                oreName = "Gold";
                break;
            case DIAMOND:
                oreMat = Material.DIAMOND;
                oreMatBlock = Material.DIAMOND_BLOCK;
                oreName = "Diamond";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        
        ItemMeta metaCache;

        ItemStack cancel = new ItemStack(Material.BARRIER);
        metaCache = cancel.getItemMeta();
        metaCache.setDisplayName("Cancel");
        cancel.setItemMeta(metaCache);
        
        ItemStack ore1 = new ItemStack(oreMat, 1);
        metaCache = ore1.getItemMeta();
        if (type == BankItemType.IRON || type == BankItemType.GOLD)
            metaCache.setDisplayName("1 " + oreName + " Ingot");
        else
            metaCache.setDisplayName("1 " + oreName);
        ore1.setItemMeta(metaCache);
        
        ItemStack ore10 = new ItemStack(oreMat, 10);
        metaCache = ore10.getItemMeta();
        if (type == BankItemType.IRON || type == BankItemType.GOLD)
            metaCache.setDisplayName("10 " + oreName + " Ingots");
        else
            metaCache.setDisplayName("10 " + oreName + "s");
        ore10.setItemMeta(metaCache);

        ItemStack ore64 = new ItemStack(oreMat, 64);
        metaCache = ore64.getItemMeta();
        if (type == BankItemType.IRON || type == BankItemType.GOLD)
            metaCache.setDisplayName("64 " + oreName + " Ingots");
        else
            metaCache.setDisplayName("64 " + oreName + "s");
        ore64.setItemMeta(metaCache);

        ItemStack block1 = new ItemStack(oreMat, 1);
        metaCache = block1.getItemMeta();
        metaCache.setDisplayName("1 " + oreName + " Block");
        block1.setItemMeta(metaCache);

        ItemStack block10 = new ItemStack(oreMat, 10);
        metaCache = block10.getItemMeta();
        metaCache.setDisplayName("10 " + oreName + " Blocks");
        block10.setItemMeta(metaCache);

        Inventory menu = new ChestGUIBuilder("Bank", ChestSize.NINE)
                .SetItem(cancel, 0)
                .SetItem(ore1, 2)
                .SetItem(ore10, 3)
                .SetItem(ore64, 4)
                .SetItem(block1, 5)
                .SetItem(block10, 6)
                .RegisterOnClickHandler(event -> {
                    if (event.item.equals(cancel))
                        event.clicker.closeInventory();
                    else if (event.item.equals(ore1))
                        return; // TODO
                    else if (event.item.equals(ore10))
                        return; // TODO
                    else if (event.item.equals(ore64))
                        return; // TODO
                    else if (event.item.equals(block1))
                        return; // TODO
                    else if (event.item.equals(block10))
                        return; // TODO
                }).RegisterOnCloseHandler(ChestGUIBuilder::Destroy)
                .ToInventory();
        player.openInventory(menu);
    }
}
