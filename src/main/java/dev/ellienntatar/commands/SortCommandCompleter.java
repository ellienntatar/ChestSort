package dev.ellienntatar.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class SortCommandCompleter implements TabCompleter {
    private Map<String, List<String>> arguments = new HashMap<>();

    public SortCommandCompleter() {
        // set up command fills
        arguments.put("quantity", new ArrayList<>());
        arguments.put("category", Arrays.asList(
            "blocks", "decoration", "redstone", "transportation", "food", "tools", "combat", "brewing", "misc"));
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] senderArguments) {
        List<String> resultsList = new ArrayList<>();
        if (senderArguments.length >= 1) {
            for (var entry : arguments.entrySet()) {
                if (senderArguments.length == 1) {
                    if (entry.getKey().startsWith(senderArguments[0].toLowerCase())) {
                        resultsList.add(entry.getKey());
                    }
                } else {
                    // sub command arguments i.e /sort category *blocks*
                    if (entry.getKey().equalsIgnoreCase(senderArguments[0])) {
                        for (String commandArgument : entry.getValue()) {
                            if (commandArgument.startsWith(senderArguments[1].toLowerCase())) {
                                resultsList.add(commandArgument);
                            }
                        }
                    }
                }
            }
        }

        return resultsList;
    }
}
