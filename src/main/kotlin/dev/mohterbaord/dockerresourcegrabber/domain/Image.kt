package dev.mohterbaord.dockerresourcegrabber.domain

data class Image(val name: String, val grabs: MutableList<Grab>)
