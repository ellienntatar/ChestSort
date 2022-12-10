package dev.ellienntatar.commands;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import dev.ellienntatar.events.ChestInteractEvent;
import dev.ellienntatar.inventory.InventoryUtil;
import dev.ellienntatar.inventory.InventoryUtil.SortType;
import net.md_5.bungee.api.ChatColor;

public class SortCommand implements CommandExecutor {
    
    private ChestInteractEvent chestInteractEvent;

    public SortCommand(ChestInteractEvent chestInteractEvent) {
        this.chestInteractEvent = chestInteractEvent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players may use this command");
            return true;
        }
        

        Player player = (Player) sender;
        
        if ("sort".equalsIgnoreCase(command.getName())) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Sort type not specified! Usage: /sort <TYPE>");
                return true;
            }
            String sortType = args[0];
            try {
                // see if it's frickin' valid!
                SortType parsedSort = SortType.valueOf(sortType.toUpperCase());
                chestInteractEvent.addPlayer(player.getName(), parsedSort);
                player.sendMessage(ChatColor.AQUA + "Sort mode activated! Sort type: " + ChatColor.WHITE + sortType + ChatColor.AQUA + " | Click on the chest you would like sorted");
            } catch (IllegalArgumentException e) {
                player.sendMessage(ChatColor.RED + "Invalid sort type \"" + sortType + "\" specified.");
                return true;
            }
        }


        return true;
    }

}
