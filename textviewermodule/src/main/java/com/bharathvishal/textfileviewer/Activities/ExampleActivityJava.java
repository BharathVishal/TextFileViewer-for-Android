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

package com.bharathvishal.textfileviewer.Activities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bharathvishal.textfileviewer.MainComponent.TextReaderModule;
import com.bharathvishal.textfileviewer.R;

/*
 *
 * This class shows the implementation of the TextViewer usage in a Java Activity class
 *
 */
public class ExampleActivityJava extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

        Context mContext = this;
        TextReaderModule obTextReader=new TextReaderModule();
        obTextReader.setContext(mContext).launchTextViewerWithFileChooser();
    }
}
