package dev.mohterbaord.dockerresourcegrabber

import com.github.dockerjava.core.DockerClientBuilder
import dev.mohterbaord.dockerresourcegrabber.domain.Image
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class DockerResourceGrabberUtils {

    companion object {

        fun grabResourcesFromDockerImages(images: Collection<Image>) {
            DockerClientBuilder.getInstance().build().use { dockerClient ->
                images.forEach { image ->
                    val response = dockerClient.createContainerCmd(image.name).exec()
                    image.grabs.forEach { grab ->
                        val copyCmd = dockerClient.copyArchiveFromContainerCmd(response.id, grab.src)
                        TarArchiveInputStream(copyCmd.exec()).use { inputStream ->
                            val dstDir = File(grab.dst).parent
                            if ((dstDir != null) && Files.notExists(Paths.get(dstDir))) {
                                File(dstDir).mkdirs()
                            }
                            TarUtils.unTar(inputStream, File(grab.dst))
                        }
                    }
                    dockerClient.removeContainerCmd(response.id).exec()
                }
            }
        }

    }

}
