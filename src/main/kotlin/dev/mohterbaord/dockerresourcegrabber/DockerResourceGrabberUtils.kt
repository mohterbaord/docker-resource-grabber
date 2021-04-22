package dev.mohterbaord.dockerresourcegrabber

import com.github.dockerjava.core.DockerClientBuilder
import dev.mohterbaord.dockerresourcegrabber.domain.Image
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import java.io.File

class DockerResourceGrabberUtils {

    companion object {

        fun grabResourcesFromDockerImages(images: Collection<Image>) {
            DockerClientBuilder.getInstance().build().use { dockerClient ->
                images.forEach { image ->
                    val response = dockerClient.createContainerCmd(image.name).exec()
                    image.grabs.forEach { grab ->
                        val copyCmd = dockerClient.copyArchiveFromContainerCmd(response.id, grab.src)
                        TarArchiveInputStream(copyCmd.exec()).use { inputStream ->
                            TarUtils.unTar(inputStream, File(grab.dst))
                        }
                    }
                    dockerClient.removeContainerCmd(response.id).exec()
                }
            }
        }

    }

}
