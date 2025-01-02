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

package com.bharathvishal.textfileviewer.Constants

import androidx.annotation.Keep

/*
 *
 * Constants
 *
 */

@Keep
object Constants {
    const val SYMBOL_HYPHEN = "-"
    const val STRING_EMPTY = " "
    const val FILE_PATH_TYPE_URI = "FILE_PATH_TYPE_URI"

    const val FILE_PATH_URI_FROM_BUNDLE = "FILE_PATH_URI_FROM_BUNDLE"
    const val FILE_PATH_FILE_FROM_BUNDLE = "FILE_PATH_FILE_FROM_BUNDLE"
    const val FILE_SHOULD_SHOW_LINE_NUMBER = "FILE_SHOULD_SHOW_LINE_NUMBER"
    const val FILE_SHOULD_SHOW_LINE_LENGTH = "FILE_SHOULD_SHOW_LINE_LENGTH"

    const val SHOULD_OPEN_TEXT_FILE_SELECTOR = "SHOULD_OPEN_TEXT_FILE_SELECTOR"

    const val SAVED_INSTANCE_SHOW_LINE_NUMBER_SETTING = "SAVED_INSTANCE_SHOW_LINE_NUMBER_SETTING"
    const val SAVED_INSTANCE_SHOW_LINE_LENGTH_SETTING = "SAVED_INSTANCE_SHOW_LINE_LENGTH_SETTING"
    const val SAVED_INSTANCE_FILE_URI_LOCATION = "SAVED_INSTANCE_FILE_URI_LOCATION"
    const val SAVED_INSTANCE_FILE_PATH_LOCATION = "SAVED_INSTANCE_FILE_PATH_LOCATION"
    const val SAVED_INSTANCE_FILE_NAME = "SAVED_INSTANCE_FILE_NAME"
    const val SAVED_INSTANCE_SCROLL_RC_SETTING ="SAVED_INSTANCE_SCROLL_RC_SETTING"
}