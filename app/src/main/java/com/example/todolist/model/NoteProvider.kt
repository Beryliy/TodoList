package com.example.todolist.model

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.text.TextUtils
import com.example.todolist.model.TodoContract.TodoEntry
import java.lang.IllegalArgumentException

class NoteProvider: ContentProvider() {
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    init{
        uriMatcher.addURI(TodoContract.CONTENT_AUTHORITY, TodoContract.PATH_TODOS, TODOS)
        uriMatcher.addURI(TodoContract.CONTENT_AUTHORITY, TodoContract.PATH_TODOS + "/#", TODO_ID)
    }
    private lateinit var dbHelper: SQLiteOpenHelper
    override fun onCreate(): Boolean {
        dbHelper = NoteDBHelper(context)
        return true
    }
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbHelper.writableDatabase
        val uriType = uriMatcher.match(uri)
        val id: Long
        when(uriType){
            TODOS -> id = db.insert(TodoEntry.TABLE_NAME, null, values)
            else -> throw IllegalArgumentException("Insert is invalid for uri: $uri")
        }
        context.contentResolver.notifyChange(uri, null)
        return Uri.parse(TodoContract.PATH_TODOS + '/' + id)
    }
    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = TodoEntry.TABLE_NAME
        val uriType = uriMatcher.match(uri)
        when(uriType){
            TODO_ID -> queryBuilder.appendWhere(TodoEntry._ID + "=" + uri.lastPathSegment)
            TODOS -> {}
            else -> throw IllegalArgumentException("Cannot query unknown uri: $uri")
        }
        val cursor = queryBuilder.query(dbHelper.readableDatabase, projection, selection, selectionArgs, null, null, sortOrder)
        cursor.setNotificationUri(context.contentResolver, uri)
        return cursor
    }
    override fun getType(uri: Uri): String? {
        val uriType = uriMatcher.match(uri)
        when(uriType){
            TODOS -> return TodoEntry.CONTENT_LIST_TYPE
            TODO_ID -> return TodoEntry.CONTENT_ITEM_TYPE
            else -> throw IllegalArgumentException("Unknown uri: $uri with type $uriType")
        }
    }
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbHelper.writableDatabase
        val uriType = uriMatcher.match(uri)
        val rowsDeleted: Int
        when(uriType){
            TODO_ID -> {
                if(TextUtils.isEmpty(selection)){
                    rowsDeleted = db.delete(TodoEntry.TABLE_NAME, TodoContract.TodoEntry._ID + "=" + uri.lastPathSegment, null)
                }else{
                    rowsDeleted = db.delete(TodoEntry.TABLE_NAME, TodoContract.TodoEntry._ID + "=" + uri.lastPathSegment + " and " + selection, selectionArgs)
                }}
            TODOS -> {rowsDeleted = db.delete(TodoEntry.TABLE_NAME, selection, selectionArgs)}
            else -> throw IllegalArgumentException("Unknown uri: $uri")
        }
        context.contentResolver.notifyChange(uri, null)
        return rowsDeleted
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbHelper.writableDatabase
        val uriType = uriMatcher.match(uri)
        val rowsUpdated: Int
        when(uriType){
            TODOS -> {rowsUpdated = db.update(TodoEntry.TABLE_NAME, values, selection, selectionArgs)}
            TODO_ID -> {
                if(TextUtils.isEmpty(selection)){
                    rowsUpdated = db.update(TodoEntry.TABLE_NAME, values, TodoEntry._ID + "=" + uri.lastPathSegment, null)
                }else{
                    rowsUpdated = db.update(TodoEntry.TABLE_NAME, values, TodoEntry._ID + "=" + uri.lastPathSegment + " and " + selection, selectionArgs)
                }}
            else -> throw IllegalArgumentException("Unknown uri: $uri")
        }
        context.contentResolver.notifyChange(uri, null)
        return rowsUpdated
    }
    companion object{
        const val TODOS = 1
        const val TODO_ID = 2
    }
}