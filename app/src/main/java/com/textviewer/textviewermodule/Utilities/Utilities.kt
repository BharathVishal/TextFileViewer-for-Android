package com.textviewer.textviewermodule.Utilities

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