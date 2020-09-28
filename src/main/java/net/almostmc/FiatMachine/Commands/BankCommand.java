package net.almostmc.FiatMachine.Commands;

import net.almostmc.FiatMachine.BankItemType;
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
            chooseOreMenu((Player) sender);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void chooseOreMenu(Player player) {
        ItemMeta metaCache;

        ItemStack cancel = new ItemStack(Material.BARRIER);
        metaCache = cancel.getItemMeta();
        metaCache.setDisplayName("Cancel");
        cancel.setItemMeta(metaCache);

        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        metaCache = iron.getItemMeta();
        metaCache.setDisplayName("Trade in Iron");
        iron.setItemMeta(metaCache);

        ItemStack gold = new ItemStack(Material.GOLD_INGOT);
        metaCache = gold.getItemMeta();
        metaCache.setDisplayName("Trade in Gold");
        gold.setItemMeta(metaCache);

        ItemStack diamond = new ItemStack(Material.DIAMOND);
        metaCache = diamond.getItemMeta();
        metaCache.setDisplayName("Trade in Diamonds");
        diamond.setItemMeta(metaCache);

        Inventory menu = new ChestGUIBuilder("Bank", ChestSize.NINE)
                .SetItem(cancel, 0)
                .SetItem(iron, 3)
                .SetItem(gold, 4)
                .SetItem(diamond, 5)
                .RegisterOnClickHandler(event -> {
                    if (event.item == null) return;

                    if (event.item.equals(cancel))
                        event.clicker.closeInventory();
                    else if (event.item.equals(iron))
                        oreAmountMenu(event.clicker, BankItemType.IRON);
                    else if (event.item.equals(gold))
                        oreAmountMenu(event.clicker, BankItemType.GOLD);
                    else if (event.item.equals(diamond))
                        oreAmountMenu(event.clicker, BankItemType.DIAMOND);
                }).RegisterOnCloseHandler(ChestGUIBuilder::Destroy)
                .ToInventory();
        player.openInventory(menu);
    }

    private void oreAmountMenu(Player player, BankItemType type) {
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
                    if (event.item == null) return;

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