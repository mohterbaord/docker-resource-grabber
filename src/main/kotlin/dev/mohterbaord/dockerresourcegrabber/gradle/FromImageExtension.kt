package dev.mohterbaord.dockerresourcegrabber.gradle

import dev.mohterbaord.dockerresourcegrabber.domain.Grab
import dev.mohterbaord.dockerresourcegrabber.domain.Image
import java.io.File
import java.nio.file.Paths

open class FromImageExtension(imageName: String, private val projectRootDir: String) {

    val image = Image(imageName, mutableListOf())

    fun grab(src: String, dst: String) {
        image.grabs.add(Grab(src, Paths.get(projectRootDir, dst, File(src).name).toString()))
    }

}
