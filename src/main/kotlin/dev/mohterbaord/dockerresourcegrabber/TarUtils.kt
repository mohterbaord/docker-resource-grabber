package dev.mohterbaord.dockerresourcegrabber

import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.utils.IOUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class TarUtils {

    companion object {

        @Throws(IOException::class)
        fun unTar(tis: TarArchiveInputStream, destFile: File) {
            var tarEntry = tis.nextTarEntry
            while (tarEntry != null) {
                if (tarEntry.isDirectory) {
                    if (!destFile.exists()) {
                        if (destFile.mkdirs()) {
                            throw RuntimeException("Cannot create directory")
                        }
                    }
                } else {
                    FileOutputStream(destFile).use { fos -> IOUtils.copy(tis, fos) }
                }
                tarEntry = tis.nextTarEntry
            }
        }

    }

}
