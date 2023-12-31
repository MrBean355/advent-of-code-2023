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

tasks.register("generateDayTemplate") {
    doLast {
        val day = findProperty("day")?.toString()?.toInt()
            ?: error("Missing day, use -Pday=x")

        copyTemplate("main", "Day%d.kt", "main_source.txt", day)
        copyTemplate("test", "Day%dTest.kt", "test_source.txt", day)
        createEmptyResources(day)
    }
}

fun copyTemplate(dir: String, file: String, template: String, day: Int) {
    val path = "src/$dir/kotlin/com/github/mrbean355/aoc/day$day/".also {
        file(it).mkdirs()
    }

    val output = file("$path/${file.format(day)}")
    if (!output.exists()) {
        val content = file("templates/$template").readText()
        output.writeText(content.replace("\$DAY$", day.toString()))
    }
}

fun createEmptyResources(day: Int) {
    val resources = file("src/test/resources/day$day/").apply { mkdirs() }
    file("$resources/example.txt").takeIf { !it.exists() }?.writeText("")
    file("$resources/puzzle.txt").takeIf { !it.exists() }?.writeText("")
}