package dev.Fjc.fjCommandStarter.cmd;

import dev.Fjc.fjCommandStarter.FjCommandStarter;
import dev.Fjc.fjCommandStarter.event.CommandState;
import dev.Fjc.fjCommandStarter.file.FileLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class GetCommandsCommand implements TabExecutor {

    private final FileLoader loader = FjCommandStarter.getLoader();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!sender.isOp()) {
            sender.sendMessage("You are lacking proper permissions for this command.");
            return false;
        }

        if (args.length == 1) {

            if (loader == null) {
                sender.sendMessage("The class responsible for this mechanism is currently disabled.");
                return false;
            }
            switch (args[0]) {
                case ("server") -> loader.getCommands(CommandState.SERVER)
                        .forEach(sender::sendMessage);

                case ("join") -> loader.getCommands(CommandState.JOIN).forEach(sender::sendMessage);
                case ("leave") -> loader.getCommands(CommandState.LEAVE).forEach(sender::sendMessage);
                case ("all") -> Arrays.stream(CommandState.values())
                        .forEachOrdered(consumer -> loader.getCommands(consumer).forEach(sender::sendMessage));

                case ("reload") -> {
                    loader.reload();
                    sender.sendMessage("The configuration was reloaded.");
                }

                default -> {
                    return false;
                }

            }
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1) return Arrays.stream(CommandState.values())
                    .map(String::valueOf)
                    .map(String::toLowerCase)
                    .toList();


        return List.of();
    }
}
