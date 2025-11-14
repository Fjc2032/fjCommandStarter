package dev.Fjc.fjCommandStarter.event;

import org.bukkit.event.Event;
import org.bukkit.event.server.ServerLoadEvent;

/**
 * An iterable enum that represents different command states. <br>
 * A command state identifies the event that's needed for a set of commands to fire.
 */
@SuppressWarnings("UnstableApiUsage")
public enum CommandState {

    SERVER(new ServerLoadEvent(ServerLoadEvent.LoadType.STARTUP)),
    JOIN,
    LEAVE,
    DEMAND;

    CommandState() {
    }

    private Event event;

    CommandState(Event event) {
        this.event = event;
    }

}
