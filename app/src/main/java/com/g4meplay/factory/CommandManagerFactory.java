package com.g4meplay.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.g4meplay.Application;
import com.g4meplay.generics.Command;
import com.g4meplay.util.CommandUtils;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class CommandManagerFactory {
    private static final String BASE_PACKAGE = Application.class.getPackageName();
    private static final String COMMANDS_PACKAGE = BASE_PACKAGE + ".commands";
    private static final Map<String, Command> commands = new HashMap<>();

    public static void registerCommands(GatewayDiscordClient client, String prefix) throws Exception {
        loadCommands();
        registerMessageListener(client, prefix);
    }

    private static void loadCommands() throws Exception {
        Reflections reflections = new Reflections(COMMANDS_PACKAGE);
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

        for (Class<? extends Command> commandClass : classes) {
            String name = CommandUtils.getCommandName(commandClass);
            Command command = commandClass.getDeclaredConstructor().newInstance();
            commands.put(name, command);
        }
    }

    private static void registerMessageListener(GatewayDiscordClient client, String prefix) {
        client.on(MessageCreateEvent.class).subscribe(event -> {
            String content = event.getMessage().getContent();
            if (!content.startsWith(prefix)) return;

            String commandName = extractCommandName(content, prefix);
            Command command = commands.get(commandName);
            if (command != null) command.execute(event);
        });
    }

    private static String extractCommandName(String content, String prefix) {
        String[] parts = content.substring(prefix.length()).split("\\s+");
        return parts.length > 0 ? parts[0] : "";
    }
}
