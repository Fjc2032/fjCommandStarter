package dev.Fjc.fjCommandStarter.event;

import dev.Fjc.fjCommandStarter.FjCommandStarter;
import dev.Fjc.fjCommandStarter.file.FileLoader;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerLoadEvent;

import java.util.Objects;

public class ServerListener implements Listener {

    private static final FileLoader loader = FjCommandStarter.getLoader();

    @EventHandler
    public void onServerStart(ServerLoadEvent event) {
        if (loader == null) return;

        var instance = loader.getCommands(CommandState.SERVER);
        if (instance == null) return;

        instance.stream()
                .filter(Objects::nonNull)
                .forEachOrdered(action -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), action));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (loader != null) {
            var instance = loader.getCommands(CommandState.JOIN);
            if (instance == null) return;

            instance.stream()
                    .filter(Objects::nonNull)
                    .forEachOrdered(action -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), action));
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (loader != null) {
            var instance = loader.getCommands(CommandState.LEAVE);
            if (instance == null) return;

            instance.stream()
                    .filter(Objects::nonNull)
                    .forEachOrdered(action -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), action));
        }
    }
}
