package dev.Fjc.fjCommandStarter.file;

import dev.Fjc.fjCommandStarter.FjCommandStarter;
import dev.Fjc.fjCommandStarter.event.CommandState;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileLoader {

    private final FjCommandStarter plugin;

    private final File file;

    private final FileConfiguration reference;

    public FileLoader(FjCommandStarter plugin) {
        this.plugin = plugin;

        this.file = new File(plugin.getDataFolder(), "config.yml");
        this.reference = plugin.getConfig();
    }

    public void build() {
        if (!file.exists()) plugin.saveDefaultConfig();
    }

    public void loadDefaults() {
        reference.addDefault("isEnabled", true);
        Arrays.stream(CommandState.values())
                .map(state -> state.toString().toLowerCase())
                .forEach(action -> reference.addDefault("commands" + action, new ArrayList<>()));


        reference.options().copyDefaults(true);
    }

    public @Nullable List<String> getCommandList() {
        return reference.getStringList("commands");
    }


    public List<String> getCommands(CommandState state) {

        String parse = state.toString().toLowerCase();

        return reference.getStringList("commands" + parse);
    }

    public void reload() {
        plugin.saveConfig();
    }

}
