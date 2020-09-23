package net.almostmc.FiatMachine.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

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
    }
}
