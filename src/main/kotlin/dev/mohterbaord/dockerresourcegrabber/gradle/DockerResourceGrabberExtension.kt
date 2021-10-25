package dev.mohterbaord.dockerresourcegrabber.gradle

import org.gradle.api.Action

open class DockerResourceGrabberExtension(private val projectRootDir: String) {

    val fromImageExtensions = mutableListOf<FromImageExtension>()

    fun fromImage(imageName: String, action: Action<in FromImageExtension>) {
        val fromImageExtension = FromImageExtension(imageName, projectRootDir)
        fromImageExtensions.add(fromImageExtension)
        action.execute(fromImageExtension)
    }

}
