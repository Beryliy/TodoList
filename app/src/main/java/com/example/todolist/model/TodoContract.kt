package com.example.todolist.model

import android.content.ContentResolver
import android.net.Uri
import android.provider.BaseColumns

object TodoContract {
    const val CONTENT_AUTHORITY = "com.example.todolist"
    val BASE_CONTENT_URI = Uri.parse("content:// $CONTENT_AUTHORITY")
    const val PATH_TODOS = "todos"
    object TodoEntry: BaseColumns{
        val CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TODOS)

        //MIME types
        const val CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                "/$CONTENT_AUTHORITY/$PATH_TODOS"
        const val CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                "/$CONTENT_AUTHORITY/$PATH_TODOS"

        const val TABLE_NAME = "todos"
        //table columns
        const val _ID = BaseColumns._ID
        const val creationTime = "creationTime"
        const val body = "body"
    }
}