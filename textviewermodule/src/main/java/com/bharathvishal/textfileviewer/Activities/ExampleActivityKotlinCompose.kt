/**
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
 **/

package com.bharathvishal.textfileviewer.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.Keep
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

/*
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