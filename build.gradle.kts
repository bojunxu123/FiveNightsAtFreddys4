plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.register("sortFiles",JavaExec::class) {
    group = "build setup"
    description = "sorts all the files in a numerically order that the frame parser can use ;)"
    classpath = sourceSets.test.get().runtimeClasspath;
    mainClass.set("SortFiles")
}

tasks.register("run",JavaExec::class) {
    group = "build"
    description = "runs the game without building it dawg"
    classpath = sourceSets.main.get().runtimeClasspath;
    mainClass.set("org.fivenightsatfreddys4.Main")
}
