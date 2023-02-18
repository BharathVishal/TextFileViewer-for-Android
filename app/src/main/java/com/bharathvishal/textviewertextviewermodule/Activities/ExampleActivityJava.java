package com.bharathvishal.textviewertextviewermodule.Activities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bharathvishal.textviewertextviewermodule.MainComponent.TextReaderModule;
import com.bharathvishal.textviewertextviewermodule.R;

/**
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
