plugins {
    `java-gradle-plugin`
    `maven-publish`
    kotlin("jvm") version "1.4.32"
    id("com.gradle.plugin-publish") version "0.14.0"
}

group = "dev.mohterbaord.docker-resource-grabber"
version = "0.1.0"

gradlePlugin {
    plugins {
        create("docker-resource-grabber") {
            id = "dev.mohterbaord.docker-resource-grabber"
            displayName = "Docker Resource Grabber"
            description = "Copies resources from Docker containers"
            implementationClass = "dev.mohterbaord.dockerresourcegrabber.gradle.DockerResourceGrabber"
        }
    }
}

pluginBundle {
    website = "https://github.com/mohterbaord/docker-resource-grabber"
    vcsUrl = "https://github.com/mohterbaord/docker-resource-grabber"
    tags = listOf("docker")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.docker-java:docker-java:3.2.12")
    implementation("org.apache.commons:commons-compress:1.21")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
