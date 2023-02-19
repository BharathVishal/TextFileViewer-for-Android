package com.bharathvishal.textfileviewer.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bharathvishal.textfileviewer.MainComponent.TextReaderModule
import com.bharathvishal.textfileviewer.ui.theme.TextViewerAndroidTheme


/**
 *
 * This class shows the implementation of the TextViewer usage in a Kotlin Activity class
 *
 */
class ExampleActivityKotlinCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TextViewerAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    TextViewHere()
                }
            }
        }

        var mContext = this
        TextReaderModule().setContext(mContext).launchTextViewerWithFileChooser ()
    }


    @Composable
    fun TextViewHere() {
        Text(
            text = "Hello",
            modifier = Modifier
                .wrapContentHeight()
                .padding(1.dp),
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1
        )
    }
}