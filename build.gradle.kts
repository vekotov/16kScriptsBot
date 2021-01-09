import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    kotlin("plugin.serialization") version "1.4.20"
}

group = "ru.16k_scripts"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    val vkSdkVersion = "0.0.7"
    testImplementation(kotlin("test-junit"))
    implementation("com.petersamokhin.vksdk:core:$vkSdkVersion")
    implementation("com.petersamokhin.vksdk:http-client-jvm-okhttp:$vkSdkVersion")
    implementation("br.com.devsrsouza:redissed:1.1.0")
    implementation("io.lettuce:lettuce-core:6.0.1.RELEASE")
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "BotKt"
    }
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}