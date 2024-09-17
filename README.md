# Text File Viewer for Android [![Release](https://jitpack.io/v/BharathVishal/TextFileViewerAndroid.svg)](https://jitpack.io/#BharathVishal/TextFileViewerAndroid)

A Text viewer for Android written in Kotlin. Allows viewing of .txt, .html, .xml, .css and .csv file types. Makes use of JetPack Compose's LazyColumn and fully implemented in Jetpack compose using Material You 3.0 dynamic theming. 

&nbsp;
### Adding Dependency:

*Step 1*. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  
*Step 2*. Add the dependency

```gradle
	dependencies {
  	    implementation 'com.github.BharathVishal:TextFileViewerAndroid:1.4.1'
	}
  
  ```

&nbsp;

### Usage:

*1*. To launch the Text viewer with a given Content Uri/File path: 
```
TextReaderModule().setContext(mContext).setFileUri(uri).launchTextViewer()

TextReaderModule().setContext(mContext).useFilePath().setFilePath(path).launchTextViewer()
```
&nbsp;

*2*. To launch the Document picker intent. This launches the document chooser intent and then dislays the selected text file using the built-in Text Viewer.
```
TextReaderModule().setContext(mContext).launchTextViewerWithFileChooser()
```
&nbsp;
- To display line length
```
TextReaderModule().shouldShowLineLength(true).setFileUri(uri).launchTextViewer()
```

- To display line number
```
TextReaderModule().shouldShowLineNumber(true).setFileUri(uri).launchTextViewer()
```

- To use File path instead of Content Uri
```
TextReaderModule().useFilePath().setFilePath(path).launchTextViewer()
```

- To use Content Uri instead of File path
```
TextReaderModule().setFileUri(uri).launchTextViewer()
```

&nbsp;

### Features :
- Allows viewing of .txt, .html, .xml, .css and .csv types.
- Clean and light weight implementation.
- Supports night mode.
- Has options to toggle line number and line length.
- Loads file using content Uri or File scheme.
- Uses coroutines to load the file asynchronously. 
- Implemented using Jetpack Compose.
- Material You 3.0 design with dynamic theming.


&nbsp;
### Preview : 
![Preview](https://github.com/BharathVishal/TextFileViewerforAndroid/blob/main/PreviewGif/PreviewGif.gif)


&nbsp;
### Screenshots : 
![Screenshot 1](https://github.com/BharathVishal/TextFileViewerAndroid/blob/main/Screenshots/1.png?s=20)
![Screenshot 2](https://github.com/BharathVishal/TextFileViewerAndroid/blob/main/Screenshots/2.png?s=20)


&nbsp;
#### Year developed : 
2023


&nbsp;

#### SDK Info : 
Min SDK : 22  | Target SDK : 35 | Gradle : 8.6.0  | Kotlin | Jetpack Compose

&nbsp;


#### Android Studio Version : 
Android Studio Koala Feature Drop | 2024.1.2


&nbsp;


#### License : 
[Apache License 2.0](https://github.com/BharathVishal/TextFileViewerAndroid/blob/main/LICENSE)
&nbsp;

&nbsp;
####
Android is a trademark of Google LLC. 

&nbsp;
&nbsp;

© 2023-2024. Developed by Bharath Vishal G (https://github.com/BharathVishal).

Thank you. :slightly_smiling_face:
