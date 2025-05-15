package com.g4meplay.util;

import com.g4meplay.annotations.CommandInfo;
import com.g4meplay.generics.Command;

public class CommandUtils {
    public static String getCommandName(Class<? extends Command> commandClass) {
        CommandInfo info = commandClass.getAnnotation(CommandInfo.class);
        if (info != null) return info.name();

        // Exception
        throw new IllegalArgumentException(String.format
            ("Command class %s does not have @CommandInfo", commandClass.getName())
        );
    }
}
