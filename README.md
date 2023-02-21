# TextViewerAndroid [![Release](https://jitpack.io/v/BharathVishal/TextFileViewerAndroid.svg)](https://jitpack.io/#BharathVishal/TextFileViewerAndroid)

A Text viewer for Android written in Kotlin. Allows viewing of .txt, .html, .xml, .css file types. Makes use of JetPack Compose's LazyColumn and fully implemented in Jetpack compose using Material You 3.0 dynamic theming. 

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
  	    implementation 'com.github.BharathVishal:TextFileViewerAndroid:1.0'
	}
  
  ```

&nbsp;

### Usage:

*1*. To launch the text viewer with a given Uri/File path: 
```
TextReaderModule().setContext(mContext).setFileUri(uri).launchTextViewer()

TextReaderModule().setContext(mContext).useFilePath().setFilePath(path).launchTextViewer()
```
&nbsp;

*2*. To launch the Document picker intent:
This launches the document chooser intent and then dislays the selected text file with the built-in Text Viewer.
```
TextReaderModule().setContext(mContext).launchTextViewerWithFileChooser()
```

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
- Allows viewing of .txt, .html, .xml, .css and .json types.
- Clean and light weight implementation.
- Supports night mode.
- Has options to toggle line number and line length.
- Loads file using content Uri or File scheme.
- Uses coroutines to load the file asynchronously. 
- Implemented using Jetpack Compose.
- Material You 3.0 design with dynamic theming.


&nbsp;
### Preview : 
![Preview](https://github.com/BharathVishal/TextFileViewerAndroid/blob/master/Preview/PreviewGif.gif)


&nbsp;
### Screenshots : 
![Screenshot 1](https://github.com/BharathVishal/TextFileViewerAndroid/blob/main/Screenshots/1.png?s=20)
![Screenshot 2](https://github.com/BharathVishal/TextFileViewerAndroid/blob/main/Screenshots/2.png?s=20)


&nbsp;
### Year developed : 
2023


&nbsp;
### Usage:


### SDK Info : 
Min SDK : 23  | Target SDK : 33 | Gradle : 7.4.1  | Kotlin | Jetpack Compose

&nbsp;


### Android Studio Version : 
Android Studio Electric Eel | 2022.1.1 


&nbsp;


#### License : 
[Apache License 2.0](https://github.com/TextFileViewerAndroid/App-Manager-Android/blob/master/LICENSE)
&nbsp;

&nbsp;

© 2023. Developed by Bharath Vishal G (https://github.com/BharathVishal).

Thank you. :smile:
