package dev.Fjc.fjCommandStarter.file;

import dev.Fjc.fjCommandStarter.FjCommandStarter;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Config {

    private final FjCommandStarter plugin;

    private File file;

    private final FileConfiguration reference;

    public Config(FjCommandStarter plugin) {
        this.plugin = plugin;

        this.file = new File(plugin.getDataFolder(), "config.yml");
        this.reference = plugin.getConfig();
    }

    public void loadDefaults() {
        reference.addDefault("isEnabled", true);
        reference.addDefault("commands", new ArrayList<>());


        reference.options().copyDefaults(true);
    }

    public List<?> getCommandList() {
        return reference.getList("commands", List.of()).stream()
                .filter(Objects::nonNull)
                .filter(obj -> obj instanceof String)
                .toList();
    }

    public void reload() throws IOException {
        reference.save(file);
    }

}
