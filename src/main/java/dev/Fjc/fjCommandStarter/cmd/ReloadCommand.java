package dev.Fjc.fjCommandStarter.cmd;

import dev.Fjc.fjCommandStarter.FjCommandStarter;
import dev.Fjc.fjCommandStarter.file.FileLoader;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReloadCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!sender.isOp()) {
            sender.sendMessage("You need proper permissions to use this command.");
            return false;
        }

        if (args.length == 1) {
            FileLoader fileLoader = FjCommandStarter.getLoader();
            if (fileLoader != null) fileLoader.reload();
            return true;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1) return List.of("reload");
        return List.of();
    }
}
