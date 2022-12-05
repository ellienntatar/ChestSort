package dev.ellienntatar.commands;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import dev.ellienntatar.events.ChestInteractEvent;
import dev.ellienntatar.inventory.ChestSorter;
import dev.ellienntatar.inventory.SelectionScreen;

public class SortCommand implements CommandExecutor {
    
    private ChestInteractEvent chestInteractEvent;

    public SortCommand(ChestInteractEvent chestInteractEvent) {
        this.chestInteractEvent = chestInteractEvent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may use this command");
            return true;
        }


        Player player = (Player) sender;
        
        if ("sort".equalsIgnoreCase(command.getName())) {
            if (args.length == 0) {
                player.sendMessage("Sort type not specified! Usage: /sort <TYPE>");
                return true;
            }

            String sortType = args[0];
            if (!ChestSorter.isImplementedSortType(sortType)) {
                player.sendMessage("Invalid sort type specified!");
                return true;
            }

            chestInteractEvent.addPlayer(player.getName(), sortType);
            player.sendMessage("Sort mode activated! Sort type: " + sortType + " | Click on the chest you would like sorted");
        }


        return true;
    }

}
