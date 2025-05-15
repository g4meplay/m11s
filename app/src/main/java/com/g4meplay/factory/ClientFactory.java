package com.g4meplay.factory;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;

public class ClientFactory {
    public static GatewayDiscordClient create(String token) {
        return DiscordClientBuilder.create(token)
                                    .build()
                                    .login()
                                    .block();
    }
}
