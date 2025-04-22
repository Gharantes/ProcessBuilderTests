package org.example

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun main() {
    try {
        Script.exec("file", "./test.rar")
        Script.exec("mkdir", "-p", "./output")
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: InterruptedException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}