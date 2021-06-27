package com.example.map.utils

import android.Manifest
import android.Manifest.permission.*
import com.github.euzee.permission.PermissionCallback
import com.github.euzee.permission.PermissionUtil
import android.app.Activity
import android.content.Context



object CheckPermissionUtil {

    private val LOCATION_PERMISSION_REQ_CODE = 200
    private val LOCATION_PERMISSION_REQ_CODE2 = 2003
    private val WRITE_SD_REQ_CODE = 201
    private val WRITE_SD_REQ_CODE2 = 20122
    private val PICK_CONTACT = 34
    private val PICK_CAMERA = 334
    private val AUDIO_RECORD = 4849


    fun checkLocation(
        context: Context,
        callback: PermissionCallback
    ) {
        PermissionUtil.locationBoth(context, callback)

    }
    fun hasFineLocation(
        activity: Activity
    ):Boolean {
       return  com.baurine.permissionutil.PermissionUtil.hasPermission(
            activity,
            ACCESS_FINE_LOCATION
        )
    }

    fun hasContact(
        activity: Activity
    ):Boolean {
        return  com.baurine.permissionutil.PermissionUtil.hasPermission(
            activity,
            READ_CONTACTS
        )
    }

    fun hasCourseLocation(
        activity: Activity
    ):Boolean {
        return  com.baurine.permissionutil.PermissionUtil.hasPermission(
            activity,
            ACCESS_COARSE_LOCATION
        )
    }




    @JvmStatic
    fun camera(
        context: Context,
        callback: PermissionCallback
    ) {
        PermissionUtil.camera(context,callback)
    }
    @JvmStatic
    fun checkWriteSd(
        context: Context,
        callback: PermissionCallback
    ) {
        PermissionUtil.storageRW(context,callback)

    }
    @JvmStatic
    fun readWriteSd(
        context: Context,
        callback: PermissionCallback
    ) {

        PermissionUtil.storageRW(context, callback)
    }
    @JvmStatic
    fun readWriteSdAndCamera(
        context: Context,
        callback: PermissionCallback
    ) {

        PermissionUtil.checkGroup(context,callback, arrayOf(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }
    fun checkContacts(
        context: Context,
        callback: PermissionCallback
    ) {
        PermissionUtil.contactsRead(context, callback)


    }
    fun write(
        context: Context,
        callback: PermissionCallback
    ) {
        PermissionUtil.contactsWrite(context,callback)
    }

}
