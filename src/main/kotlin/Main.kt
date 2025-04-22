package org.example

import java.io.IOException
import kotlin.io.path.createTempDirectory
import kotlin.io.path.createTempFile
import kotlin.io.path.name

fun main() {
    val filename = "test.rar"
    try {
        when (getRARVersion(filename)) {
            5 -> extractV5RAR(filename)
            4 -> println("RAR 4.0")
            null -> println("Unknown version")
            else -> println("Unknown version")
        }
        getRARVersion("v4.rar")
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: InterruptedException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun getRARVersion(filename: String): Int? {
    val regex = Regex("""RAR archive data,\s*v(\d+)""")
    var version: Int? = null
    Script.exec("file", "./$filename") { _, o ->
        version = regex
            .find(o)
            ?.groupValues
            ?.get(1)
            ?.toIntOrNull()
    }
    return version
}
fun extractV5RAR(filename: String) {
    val destination = "paths"
    val dirf = createTempDirectory(destination).toFile()
    Script.exec("unrar", "x", "-v", "./$filename", "$dirf") { ec, o ->
        println(o)
    }
}
