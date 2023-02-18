package com.bharathvishal.textviewertextviewermodule.MainComponent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bharathvishal.textviewertextviewermodule.Activities.TextViewMainActivity
import com.bharathvishal.textviewertextviewermodule.Constants.Constants

class TextReaderModule() {
    private var mContext: Context? = null
    private var isFileUri: Boolean = false
    private var fileUri: Uri = Uri.EMPTY
    private var filePath: String = "-"
    private var shouldShowLineNumber: Boolean = true
    private var shouldShowLineLength: Boolean = true
    private var shouldOpenFileChooserIntent: Boolean = false

    fun setContext(con: Context): TextReaderModule {
        mContext = con
        return this
    }

    fun shouldShowLineNumber(value: Boolean): TextReaderModule {
        shouldShowLineNumber = value
        return this
    }

    fun shouldShowLineLength(value: Boolean):TextReaderModule {
        shouldShowLineLength = value
        return this
    }

    fun setFileUri(uriT: Uri): TextReaderModule {
        fileUri = uriT
        return this
    }

    fun useFileUri(): TextReaderModule {
        isFileUri = true
        return this
    }

    fun useFilePath(): TextReaderModule {
        isFileUri = false
        return this
    }

    fun setFilePath(path: String): TextReaderModule {
        filePath = filePath
        return this
    }

    fun shouldOpenFileChooserIntent(): TextReaderModule {
        shouldOpenFileChooserIntent = true
        return this
    }

    fun launchTextViewerWithFileChooser()
    {
        try {
            val mIntent = Intent(mContext, TextViewMainActivity::class.java)
            val mBundle = Bundle()
            mBundle.putBoolean(Constants.FILE_SHOULD_SHOW_LINE_NUMBER, shouldShowLineNumber)
            mBundle.putBoolean(Constants.FILE_SHOULD_SHOW_LINE_LENGTH, shouldShowLineLength)
            mBundle.putBoolean(
                Constants.SHOULD_OPEN_TEXT_FILE_SELECTOR,
                true
            )
            mIntent.putExtras(mBundle)
            mContext?.startActivity(mIntent);
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun launchTextViewer() {
        try {
            val mIntent = Intent(mContext, TextViewMainActivity::class.java)
            val mBundle = Bundle()
            mBundle.putBoolean(Constants.FILE_SHOULD_SHOW_LINE_NUMBER, shouldShowLineNumber)
            mBundle.putBoolean(Constants.FILE_SHOULD_SHOW_LINE_LENGTH, shouldShowLineLength)
            mBundle.putBoolean(
                Constants.SHOULD_OPEN_TEXT_FILE_SELECTOR,
                shouldOpenFileChooserIntent
            )

            if (isFileUri) {
                mBundle.putString(Constants.FILE_PATH_URI_FROM_BUNDLE, fileUri.toString())
                mBundle.putBoolean(Constants.FILE_PATH_TYPE_URI, true)
            } else {
                mBundle.putString(Constants.FILE_PATH_FILE_FROM_BUNDLE, filePath)
                mBundle.putBoolean(Constants.FILE_PATH_TYPE_URI, false)
            }

            mIntent.putExtras(mBundle)
            mContext?.startActivity(mIntent);
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}