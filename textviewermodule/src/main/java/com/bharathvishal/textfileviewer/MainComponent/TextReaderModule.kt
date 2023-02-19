package com.bharathvishal.textfileviewer.MainComponent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bharathvishal.textfileviewer.Activities.TextViewMainActivity
import com.bharathvishal.textfileviewer.Constants.Constants

/*
 *
 * Copyright 2023 Bharath Vishal G
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/*
 *
 * Main Initialization Class for TextReaderModule
 *
 */
public class TextReaderModule() {
    private var mContext: Context? = null
    private var isFileUri: Boolean = false
    private var fileUri: Uri = Uri.EMPTY
    private var filePath: String = "-"
    private var shouldShowLineNumber: Boolean = true
    private var shouldShowLineLength: Boolean = true
    private var shouldOpenFileChooserIntent: Boolean = false

    /**
     * This method sets the Context.
     * @return TextReaderModule
     */
    fun setContext(con: Context): TextReaderModule {
        mContext = con
        return this
    }

    /**
     *
     * This method enables/disables displaying of line numbers in the TextViewer.
     *
     * @param value of Boolean type. Set to true to show line numbers in the TextViewer.
     * @return TextReaderModule
     */
    fun shouldShowLineNumber(value: Boolean): TextReaderModule {
        shouldShowLineNumber = value
        return this
    }


    /**
     *
     * This method enables/disables displaying of line content length in the TextViewer.
     *
     * @param value of Boolean type. Set to true to show line content length in the TextViewer.
     * @return TextReaderModule
     */
    fun shouldShowLineLength(value: Boolean):TextReaderModule {
        shouldShowLineLength = value
        return this
    }


    /**
     *
     * This method sets the URI of the file to be displayed by the TextViewer.
     *
     * @param uriT of Uri type. The file's Content Uri.
     * @return TextReaderModule
     */
    fun setFileUri(uriT: Uri): TextReaderModule {
        fileUri = uriT
        return this
    }

    /**
     *
     * This method enables the TextViewer module to use content URI scheme instead of file paths.
     *
     * @return TextReaderModule
     */
    fun useFileUri(): TextReaderModule {
        isFileUri = true
        return this
    }


    /**
     *
     * This method enables the TextViewer module to use file paths scheme instead of content URI's.
     *
     * @return TextReaderModule
     */
    fun useFilePath(): TextReaderModule {
        isFileUri = false
        return this
    }


    /**
     *
     * This method sets the file path of the file to be displayed by the TextViewer.
     *
     * @param path of String type. The file's absolute path in string format.
     * @return TextReaderModule
     */
    fun setFilePath(path: String): TextReaderModule {
        filePath = filePath
        return this
    }


    /**
     *
     * This method sets if the file chooser intent is to be displayed on launching the TextViewer.
     *
     * @return TextReaderModule
     */
    fun shouldOpenFileChooserIntent(): TextReaderModule {
        shouldOpenFileChooserIntent = true
        return this
    }


    /**
     *
     * This method Initialises and launches the TextViewer.
     * The user is presented with an document chooser intent to choose the text file.
     *
     */
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


    /**
     *
     * This method Initialises and launches the TextViewer.
     * The text file is loaded using the file path (filePath)
     * or the content uri (fileUri) initialized earlier in this class.
     *
     */
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
            mContext?.startActivity(mIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}