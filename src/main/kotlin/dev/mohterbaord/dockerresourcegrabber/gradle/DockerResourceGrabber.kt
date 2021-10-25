package dev.mohterbaord.dockerresourcegrabber.gradle

import dev.mohterbaord.dockerresourcegrabber.DockerResourceGrabberUtils
import org.gradle.api.Plugin
import org.gradle.api.Project

class DockerResourceGrabber : Plugin<Project> {

    companion object {

        const val EXTENSION_NAME = "dockerResourceGrabber"

        const val GRAB_TASK_NAME = "grabDockerResources"

    }

    override fun apply(project: Project) {
        project.tasks.create(GRAB_TASK_NAME) { task ->
            val dockerResourceGrabberExtension = project.extensions.create(
                EXTENSION_NAME,
                DockerResourceGrabberExtension::class.java,
                project.rootDir.absolutePath
            )
            task.doLast {
                DockerResourceGrabberUtils.grabResourcesFromDockerImages(
                    dockerResourceGrabberExtension.fromImageExtensions.map(FromImageExtension::image)
                )
            }
        }.apply {
            group = "Build"
            description = "Copies resources from Docker images"
        }
    }

}
