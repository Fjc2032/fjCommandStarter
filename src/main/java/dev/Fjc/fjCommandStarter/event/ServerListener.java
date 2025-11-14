package dev.Fjc.fjCommandStarter.event;

import dev.Fjc.fjCommandStarter.FjCommandStarter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

import java.util.Objects;

public class ServerListener implements Listener {

    @EventHandler
    public void onServerStart(ServerLoadEvent event) {
        if (FjCommandStarter.getLoader() == null) return;

        var instance = FjCommandStarter.getLoader().getCommandList();
        if (instance == null) return;

        instance.stream()
                .filter(Objects::nonNull)
                .forEachOrdered(action -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), action));
    }
}
