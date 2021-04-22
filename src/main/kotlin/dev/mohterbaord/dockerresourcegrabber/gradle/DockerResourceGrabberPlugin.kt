package dev.mohterbaord.dockerresourcegrabber.gradle

import dev.mohterbaord.dockerresourcegrabber.DockerResourceGrabberUtils
import dev.mohterbaord.dockerresourcegrabber.domain.Image
import org.gradle.api.Plugin
import org.gradle.api.Project

class DockerResourceGrabberPlugin : Plugin<Project> {

    companion object {

        const val EXTENSION_NAME = "dockerResourceGrabberPlugin"

        const val GRAB_TASK_NAME = "grabDockerResources"

    }

    override fun apply(project: Project) {
        val dockerResourcesExt = project.extensions.create(
            EXTENSION_NAME,
            DockerResourceGrabberPluginExtension::class.java,
            project
        )
        project.task(GRAB_TASK_NAME) {
            it.doLast {
                val images = dockerResourcesExt.imagesDeclaration.asMap.map { entry ->
                    Image(entry.key, entry.value.grabs)
                }
                DockerResourceGrabberUtils.grabResourcesFromDockerImages(images)
            }
        }
    }

}
