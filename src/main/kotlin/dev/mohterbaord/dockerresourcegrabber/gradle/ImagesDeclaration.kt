package dev.mohterbaord.dockerresourcegrabber.gradle

import dev.mohterbaord.dockerresourcegrabber.domain.Grab
import org.gradle.api.Named

class ImagesDeclaration(private val name: String) : Named {

    val grabs: MutableList<Grab> = mutableListOf()

    override fun getName(): String {
        return name
    }

    fun grab(src: String, dst: String) {
        grabs.add(Grab(src, dst))
    }

}
