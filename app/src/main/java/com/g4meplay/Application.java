package com.g4meplay;

import com.g4meplay.environment.Environment;
import com.g4meplay.factory.ClientFactory;
import com.g4meplay.factory.CommandManagerFactory;

import discord4j.core.GatewayDiscordClient;

public class Application {

    private static final String TOKEN = Environment.getToken();
    private static final String PREFIX = Environment.getPrefix();
    private static final Runtime applicationProcess = Runtime.getRuntime();

    public static void main(String[] args) throws Exception {
        // Create the Discord client using the bot token
        GatewayDiscordClient client = ClientFactory.create(TOKEN);

        // Register a shutdown hook to cleanly log out the bot when the application is closed
        applicationProcess.addShutdownHook(new Thread(
            () -> client.logout()
                        .block()
        ));

        // Register commands
        CommandManagerFactory.registerCommands(client, PREFIX);

        // Keep the bot running until it is manually disconnected
        client.onDisconnect()
                .block();
    }
}
