plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // Use JUnit test framework.
    testImplementation(libs.junit)
    
    // Discord API
    implementation("com.discord4j:discord4j-core:3.2.8")
    
    // Dotenv
    implementation("io.github.cdimascio:dotenv-java:3.2.0")

    // This dependency is used by the application.
    implementation(libs.guava)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    mainClass = "com.g4meplay.Application"
}
