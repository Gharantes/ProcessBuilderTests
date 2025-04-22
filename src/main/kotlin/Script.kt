package org.example

import java.io.BufferedReader
import java.io.InputStreamReader

class Script(
    processBuilder: ProcessBuilder,
    whenDone: (Int, String) -> Unit
) {
    companion object {
        fun exec(
            vararg command: String,
            whenDone: (Int, String) -> Unit = { _, _ -> }
        ): Script {
            return Script(
                ProcessBuilder(command.toList()),
                whenDone
            )
        }
    }
    init {
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