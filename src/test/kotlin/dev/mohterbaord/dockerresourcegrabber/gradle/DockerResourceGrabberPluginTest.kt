package dev.mohterbaord.dockerresourcegrabber.gradle

import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

class DockerResourceGrabberPluginTest {

    @Test
    fun test() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("dev.mohterbaord.docker-resource-grabber")
    }

}