package dev.Fjc.fjCommandStarter.file;

import dev.Fjc.fjCommandStarter.FjCommandStarter;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        reference.addDefault("commands", new ArrayList<>());


        reference.options().copyDefaults(true);
    }

    public @Nullable List<String> getCommandList() {
        return reference.getStringList("commands");
    }

    public void reload() {
        plugin.saveConfig();
    }

}
