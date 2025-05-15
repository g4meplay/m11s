package com.g4meplay.environment;

import io.github.cdimascio.dotenv.Dotenv;

public class Environment {
    private static final Dotenv dotenv = Dotenv.load();

    public static String get(String key) {
        String value = dotenv.get(key);
        if (value != null) return value;

        // Exception
        throw new RuntimeException(String.format
            ("Environment variable %s not found in any .env file", key)
        );
    }

    public static String getToken() {
        return get("TOKEN");
    }

    public static String getPrefix() {
        return get("PREFIX");
    }
}
