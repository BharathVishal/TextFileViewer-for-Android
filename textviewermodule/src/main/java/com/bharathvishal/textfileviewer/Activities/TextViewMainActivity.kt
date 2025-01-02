/**
 *
 * Copyright 2023-2025 Bharath Vishal G
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

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.bharathvishal.textfileviewer.Classes.TextLineRepresentation
import com.bharathvishal.textfileviewer.Constants.Constants
import com.bharathvishal.textfileviewer.Utilities.Utilities
import com.bharathvishal.textfileviewer.ui.theme.TextViewerAndroidTheme
import com.google.android.material.color.DynamicColors
import kotlinx.coroutines.*
import java.io.*
import java.lang.ref.WeakReference

class TextViewMainActivity : ComponentActivity(), CoroutineScope by MainScope() {
    var textLineListSnapShot = SnapshotStateList<TextLineRepresentation>()
    private lateinit var mContext: Context
    private var showLineNumber: Boolean = true
    private var showLineLength: Boolean = true
    private var isFileTypeUri: Boolean = true
    private var fileToOpen: String = "-"
    private var fileToOpenUri: Uri = Uri.EMPTY

    private var fileNameTextFile = mutableStateOf("File")
    private var showProgressBar = mutableStateOf(true)

    val resultLauncherPickTextFile =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    if (result?.data != null) {
                        val inputStream: InputStream? =
                            result?.data!!.data?.let {
                                mContext.contentResolver.openInputStream(
                                    it
                                )
                            }

                        val selectedTextFileUri: Uri? = result?.data!!.data
                        Log.d("curUri1", "cur uri : " + selectedTextFileUri.toString())
                        if (selectedTextFileUri != null) {
                            fileToOpenUri = selectedTextFileUri
                        }
                        isFileTypeUri = true

                        //Coroutine to open the text file
                        loadTextFile(mContext, false, isFileTypeUri)
                    } else
                        Toast.makeText(
                            mContext!!,
                            "Error selecting text file, please try again.",
                            Toast.LENGTH_SHORT
                        ).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Applies Material dynamic theming
        try {
            DynamicColors.applyToActivityIfAvailable(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        textLineListSnapShot = SnapshotStateList()

        mContext = this

        if (savedInstanceState == null) {
            try {
                if (intent.extras != null) {
                    isFileTypeUri = intent.extras?.getBoolean(Constants.FILE_PATH_TYPE_URI, true)!!

                    if (isFileTypeUri) {
                        val tempOb =
                            intent.extras?.getString(Constants.FILE_PATH_URI_FROM_BUNDLE).toString()
                        fileToOpenUri = tempOb.toUri()
                    } else
                        fileToOpen =
                            intent.extras?.getString(Constants.FILE_PATH_FILE_FROM_BUNDLE)
                                .toString()

                    showLineNumber =
                        intent.extras?.getBoolean(Constants.FILE_SHOULD_SHOW_LINE_NUMBER, true)!!
                    showLineNumber =
                        intent.extras?.getBoolean(Constants.FILE_SHOULD_SHOW_LINE_LENGTH, true)!!

                    val temp1 =
                        intent.extras?.getBoolean(Constants.SHOULD_OPEN_TEXT_FILE_SELECTOR, false)!!

                    if (temp1) {
                        showLineNumber = true
                        showLineLength = true
                        openFileTxt()
                    } else {
                        //Coroutine to open the text file
                        loadTextFile(mContext, false, isFileTypeUri)
                    }
                } else {
                    showLineNumber = true
                    showLineLength = true
                    //Only for debug implementation
                    openFileTxt()
                }
            } catch (e: Exception) {
                showLineNumber = true
                showLineLength = true
                e.printStackTrace()
            }
        } else {
            try {
                //if saved instance state is not null
                showLineNumber =
                    savedInstanceState.getBoolean(Constants.SAVED_INSTANCE_SHOW_LINE_NUMBER_SETTING)
                showLineLength =
                    savedInstanceState.getBoolean(Constants.SAVED_INSTANCE_SHOW_LINE_LENGTH_SETTING)
                showProgressBar.value = true
                val temp1 = savedInstanceState.getString(Constants.SAVED_INSTANCE_FILE_URI_LOCATION)
                fileToOpenUri = temp1?.toUri()!!

                //Coroutine to open the text file
                loadTextFile(mContext, false, isFileTypeUri)
            } catch (e: Exception) {
                showLineNumber = true
                showLineLength = true
            }
        }

        setContent {
            TextViewerAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainViewImplementation()
                }
            }
        }
    }


    //Only for debug build
    //To test pickuping up of files
    fun openFileTxt() {
        try {
            val intent = Intent()
            intent.type = "*/*"
            intent.action = Intent.ACTION_OPEN_DOCUMENT
            val extraMimeTypes = arrayOf(
                "text/*", "text/css", "text/javascript", "text/html", "text/plain", "text/xml",
                "application/xml", "text/csv", "application/json"
            )
            intent.putExtra(Intent.EXTRA_MIME_TYPES, extraMimeTypes)
            resultLauncherPickTextFile.launch(Intent.createChooser(intent, "Select Text File"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun loadTextFile(
        context: Context,
        isCalledOnResume: Boolean,
        isFileTypeUriVar: Boolean
    ) {
        val contextRef: WeakReference<Context> = WeakReference(context)
        showProgressBar.value = true

        launch(Dispatchers.Default) {
            try {
                val context1 = contextRef.get()

                var tListTemp: MutableList<TextLineRepresentation>? = null
                tListTemp = ArrayList()

                var text1 = StringBuilder()
                var txtNameToLoad1 = "-"

                if (!isFileTypeUriVar) {
                    val txtfilepath1 = fileToOpen
                    txtNameToLoad1 = Utilities.getFileNameFromFilePath(txtfilepath1)

                    if (txtfilepath1 != null) {
                        val file = File(txtfilepath1)
                        var mlineNumber: Int = 0

                        try {
                            val br = BufferedReader(FileReader(file))
                            var line: String?

                            while (br.readLine().also { line = it } != null) {
                                val tempTextLineCustom = TextLineRepresentation()
                                tempTextLineCustom.lineNumber = mlineNumber.toString()
                                tempTextLineCustom.textLine = line.toString()
                                tempTextLineCustom.textNoOfCharacters = "" + line?.length
                                mlineNumber++
                                tListTemp?.add(tempTextLineCustom)
                            }

                            br.close()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                } else {
                    val uripath1 = fileToOpenUri
                    txtNameToLoad1 = Utilities.getFileNameFromUriPath(uripath1, context1!!)

                    Log.d("file1", "file : $txtNameToLoad1")

                    if (uripath1 != null) {
                        var inputStream: InputStream? = null
                        try {
                            inputStream = contentResolver.openInputStream(uripath1)
                        } catch (e: FileNotFoundException) {
                            e.printStackTrace()
                        }
                        var mlineNumber: Int = 0
                        try {
                            val br = BufferedReader(InputStreamReader(inputStream))
                            var line: String?

                            while (br.readLine().also { line = it } != null) {
                                val tempTextLineCustom = TextLineRepresentation()
                                tempTextLineCustom.lineNumber = mlineNumber.toString()
                                tempTextLineCustom.textLine = line.toString()
                                tempTextLineCustom.textNoOfCharacters = "" + line?.length
                                mlineNumber++
                                tListTemp?.add(tempTextLineCustom)
                            }
                            br.close()
                            try {
                                inputStream!!.close()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }//end of else part

                //UI Thread
                withContext(Dispatchers.Main)
                {
                    try {
                        fileNameTextFile.value = txtNameToLoad1
                        textLineListSnapShot?.addAll(tListTemp)
                        showProgressBar.value = false
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }//end of withContextUI
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBarMain() {
        TopAppBar(
            title = { Text(fileNameTextFile.value) }, colors = TopAppBarDefaults.topAppBarColors(
                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            navigationIcon = {
                IconButton(onClick = {
                    try {
                        finish()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "icon")
                }
            }
        )
    }

    @Composable
    fun ViewMain() {
        Column {
            Spacer(modifier = Modifier.padding(top = 1.dp))
            Column(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(top = 1.dp))
                LazyColumnView()
            }//end of column
        }//end of outer column
    }//end of card view main


    @Composable
    fun ComposableCardViewApp(
        tLineContent: String,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(1.dp, 1.dp, 1.dp, 1.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SelectionContainer(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {
                    Text(
                        text = tLineContent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(4.dp, 0.dp, 4.dp, 0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1
                    )
                }
            }
        }
    }

    @Composable
    fun ComposableCardViewAppCustom(
        tLineContent: String,
        tlinelen: String,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(1.dp, 1.dp, 1.dp, 1.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SelectionContainer(
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f)
                ) {
                    Text(
                        text = tLineContent,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(4.dp, 0.dp, 4.dp, 0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1
                    )
                }
                Text(
                    text = tlinelen,
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline,
                    maxLines = 1
                )
            }
        }
    }

    @Composable
    fun ComposableCardViewApp(
        tlineNo: String,
        tLineContent: String,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(1.dp, 1.dp, 1.dp, 1.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tlineNo,
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline,
                    maxLines = 1
                )
                SelectionContainer(
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f)
                ) {
                    Text(
                        text = tLineContent,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(4.dp, 0.dp, 4.dp, 0.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1
                    )
                }
            }
        }
    }

    @Composable
    fun ComposableCardViewApp(
        tlineNo: String,
        tLineContent: String,
        tLineLen: String,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(1.dp, 1.dp, 1.dp, 1.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tlineNo,
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline,
                    maxLines = 1
                )
                SelectionContainer(
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f)
                ) {
                    Text(
                        text = tLineContent,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(4.dp, 0.dp, 4.dp, 0.dp),
                    )
                }
                Text(
                    text = tLineLen,
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline,
                    maxLines = 1
                )
            }
        }
    }


    @Composable
    fun LazyColumnView() {
        val state = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentPadding = PaddingValues(1.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            state = state
        ) {
            if (textLineListSnapShot != null) {
                items(items = textLineListSnapShot) { item ->
                    if (item != null)
                        if (showLineLength && showLineNumber) {
                            ComposableCardViewApp(
                                item.lineNumber,
                                item.textLine,
                                item.textNoOfCharacters
                            )
                        } else if (showLineNumber && !showLineLength) {
                            ComposableCardViewApp(
                                item.lineNumber,
                                item.textLine,
                            )
                        } else if (!showLineNumber && !showLineLength) {
                            ComposableCardViewApp(
                                item.textLine,
                            )
                        } else if (!showLineNumber && showLineLength) {
                            ComposableCardViewAppCustom(
                                item.textLine,
                                item.textNoOfCharacters
                            )
                        }
                }
            }
        }
    }


    @Composable
    fun showProgressBar() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(5.dp, 0.dp, 5.dp, 0.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = "Loading file",
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(1.dp),
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1
            )
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(1.dp, 5.dp, 1.dp, 1.dp)
                    .wrapContentWidth()
                    .wrapContentHeight()
            )
        }
    }

    @Composable
    fun MainViewImplementation() {
        Column {
            TopAppBarMain()
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                if (showProgressBar.value)
                    showProgressBar()
                else
                    ViewMain()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        TextViewerAndroidTheme {
            MainViewImplementation()
            //ComposableCardViewApp("1,", "hello how are you", "100")
        }
    }


    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        try {
            savedInstanceState.putBoolean("SAVED_INSTANCE_SHOW_LINE_NUMBER_SETTING", showLineNumber)
            savedInstanceState.putBoolean("SAVED_INSTANCE_SHOW_LINE_LENGTH_SETTING", showLineLength)

            if (isFileTypeUri)
                savedInstanceState.putString(
                    "SAVED_INSTANCE_FILE_URI_LOCATION",
                    fileToOpenUri.toString()
                )
            else
                savedInstanceState.putString("SAVED_INSTANCE_FILE_PATH_LOCATION", fileToOpen)

            savedInstanceState.putString("SAVED_INSTANCE_FILE_NAME", fileNameTextFile.value)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}