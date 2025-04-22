package org.example

import java.io.BufferedReader
import java.io.InputStreamReader

class Script(
    processBuilder: ProcessBuilder,
    whenDone: (Int) -> Unit
) {
    companion object {
        fun exec(
            vararg command: String,
            whenDone: (Int) -> Unit = {}
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
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            println(line)
        }
        val exitCode = process.waitFor()
        whenDone(exitCode)
    }
}