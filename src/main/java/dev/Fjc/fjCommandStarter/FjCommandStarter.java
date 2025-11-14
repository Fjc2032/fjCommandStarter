package dev.Fjc.fjCommandStarter;

import dev.Fjc.fjCommandStarter.cmd.GetCommandsCommand;
import dev.Fjc.fjCommandStarter.cmd.ReloadCommand;
import dev.Fjc.fjCommandStarter.event.ServerListener;
import dev.Fjc.fjCommandStarter.file.FileLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public final class FjCommandStarter extends JavaPlugin {

    private static FjCommandStarter instance;

    private static FileLoader loader;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        init();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void init() {
        loader = new FileLoader(this);
        getServer().getPluginManager().registerEvents(new ServerListener(), this);

        var obj = getServer().getPluginCommand("fjCommandStarter");
        if (obj == null) {
            getLogger().warning("Something went wrong while attempting to initialize the command!");
            getLogger().warning("The PluginCommand instance is null for some reason...");
            getLogger().warning("Either a /reload or incompatible version?");
        } else obj.setExecutor(new GetCommandsCommand());
        loader.build();
        loader.loadDefaults();
    }

    public static @Nullable FileLoader getLoader() {
        return loader;
    }

    public static @Nullable FjCommandStarter getInstance() {
        return instance;
    }
}
