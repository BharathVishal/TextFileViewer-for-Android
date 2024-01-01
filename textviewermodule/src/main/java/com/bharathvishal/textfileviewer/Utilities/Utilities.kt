/**
 *
 * Copyright 2023-2024 Bharath Vishal G
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
 **/

package com.bharathvishal.textfileviewer.Utilities

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns

object Utilities {
    fun getFileNameFromFilePath(stringFileName: String): String {
        var res = "file.txt"
        try {
            val i = stringFileName.lastIndexOf('.')
            val tempStr = stringFileName.substring(i)
            res = tempStr
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return res
    }

    @SuppressLint("Range")
     fun getFileNameFromUriPath(uriOfFile: Uri, mContext: Context): String {
        var res = "file.txt"
        try {
            val mCur: Cursor? = mContext.contentResolver.query(uriOfFile, null, null, null, null)
            mCur?.moveToFirst()
            if (mCur != null)
                res = mCur.getString(mCur.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            mCur?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return res
    }
}