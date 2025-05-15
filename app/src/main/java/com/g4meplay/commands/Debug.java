package com.g4meplay.commands;

import com.g4meplay.annotations.CommandInfo;
import com.g4meplay.generics.Command;

import discord4j.core.event.domain.message.MessageCreateEvent;

@CommandInfo(name = "debug")
public class Debug implements Command {
    
    @Override
    public void execute(MessageCreateEvent event) {
        event.getMessage().getChannel()
                .flatMap(channel -> channel.createMessage("Hello, world!"))
                .subscribe();
    }
}
