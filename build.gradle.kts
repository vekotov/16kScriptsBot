import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "6.1.0"
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