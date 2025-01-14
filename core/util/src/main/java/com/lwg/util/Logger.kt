package com.lwg.util

import android.util.Log
import com.lwg.utils.BuildConfig
import kotlin.math.min

object Logger : AndroidLogger {

    private const val TAG = "movie"
    private const val MAX_LOG_LENGTH = 1000
    private val IS_DEBUG = BuildConfig.DEBUG

    override fun e(msg: String) {
        if (IS_DEBUG) logLongMessage(Log.ERROR, buildLogMsg(msg))
    }

    override fun w(msg: String) {
        if (IS_DEBUG) logLongMessage(Log.WARN, buildLogMsg(msg))
    }

    override fun i(msg: String) {
        if (IS_DEBUG) logLongMessage(Log.INFO, buildLogMsg(msg))
    }

    override fun d(msg: String) {
        if (IS_DEBUG) logLongMessage(Log.DEBUG, buildLogMsg(msg))
    }

    override fun v(msg: String) {
        if (IS_DEBUG) logLongMessage(Log.VERBOSE, buildLogMsg(msg))
    }

    private fun logLongMessage(logType: Int, message: String) {
        val logMessageBuilder = StringBuilder()
        var i = 0

        while (i < message.length) {
            val end = min(message.length, i + MAX_LOG_LENGTH)
            val part = message.substring(i, end)

            logMessageBuilder.append(part)

            if (end < message.length) {
                logMessageBuilder.append("\n")
            }

            i += MAX_LOG_LENGTH
        }

        val finalLogMessage = logMessageBuilder.toString()
        when (logType) {
            Log.ERROR -> Log.e(TAG, finalLogMessage)
            Log.WARN -> Log.w(TAG, finalLogMessage)
            Log.INFO -> Log.i(TAG, finalLogMessage)
            Log.DEBUG -> Log.d(TAG, finalLogMessage)
            Log.VERBOSE -> Log.v(TAG, finalLogMessage)
        }
    }

    private fun buildLogMsg(msg: String): String {
        val ste = Thread.currentThread().stackTrace[4]
        val sb = StringBuilder().apply {
            append("[")
            append(ste.fileName.replace(".java", ""))
            append("::")
            append(ste.methodName)
            append("] ")
            append(msg)
        }
        return sb.toString()
    }
}
