plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.github.mrbean355"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}