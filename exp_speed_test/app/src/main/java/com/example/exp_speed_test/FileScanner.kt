package com.example.exp_speed_test

import java.io.File

class FileScanner {

    private var totalFilesCount = 0
    private var scannedFilesCount = 0
    private var progressCallback: ((progress: Int) -> Unit)? = null

    fun setProgressCallback(callback: (progress: Int) -> Unit) {
        progressCallback = callback
    }

    fun scanFolder(directory: File) {
        totalFilesCount = countTotalFiles(directory)
        scannedFilesCount = 0

        scanRecursive(directory)
    }

    private fun scanRecursive(file: File) {
        if (file.isDirectory) {
            val files = file.listFiles()
            if (files != null) {
                for (childFile in files) {
                    scanRecursive(childFile)
                }
            }
        } else {
            scannedFilesCount++
            val progress = (scannedFilesCount.toFloat() / totalFilesCount * 100).toInt()
            progressCallback?.invoke(progress)
        }
    }

    private fun countTotalFiles(directory: File): Int {
        if (directory.isDirectory) {
            val files = directory.listFiles()
            if (files != null) {
                var count = 0
                for (file in files) {
                    count += if (file.isDirectory) {
                        countTotalFiles(file)
                    } else {
                        1
                    }
                }
                return count
            }
        }
        return 0
    }
}
