package dev.mohterbaord.dockerresourcegrabber.gradle

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

open class DockerResourceGrabberPluginExtension(project: Project) {

    val imagesDeclaration: NamedDomainObjectContainer<ImagesDeclaration> = project.container(ImagesDeclaration::class.java)

}
