package com.g4meplay.generics;

import discord4j.core.event.domain.message.MessageCreateEvent;

public interface Command { 
    void execute(MessageCreateEvent event); 
}
