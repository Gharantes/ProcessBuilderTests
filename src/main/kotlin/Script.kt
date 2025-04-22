package org.example

import java.io.BufferedReader
import java.io.InputStreamReader

class Script(
    vararg command: String,
    whenDone: (Int, String) -> Unit
) {
    init {
        val processBuilder = ProcessBuilder(command.toList())
        val process = processBuilder.start()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val output = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            output.appendLine(line)
        }
        val exitCode = process.waitFor()
        whenDone(exitCode, output.toString())
    }
}