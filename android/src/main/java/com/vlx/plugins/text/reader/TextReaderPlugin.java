package com.vlx.plugins.text.reader;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import com.getcapacitor.JSObject;
import com.getcapacitor.Logger;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;

import java.io.Console;
import java.io.IOException;


@CapacitorPlugin(name = "TextReader",
        permissions = {@Permission(alias = "camera", strings = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})})
public class TextReaderPlugin extends Plugin {

    private TextReader implementation = new TextReader();

    @PluginMethod
    public void detectText(PluginCall call) throws IOException {

        String filename = call.getString("filename");
        if (filename == null) {
            call.reject("Filename not specified");
            return;
        }
        String orientation = call.getString("orientation");
        if (orientation == null) {
            orientation = "UP";
        }

        int rotation = this.orientationToRotation(orientation);

        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), Uri.parse(filename));
        if (bitmap == null) {
            call.reject("Could not load image from path");
            return;
        } else {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            Matrix matrix = new Matrix();
            matrix.setRotate((float) rotation);
            Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);

            implementation.detectText(call, rotatedBitmap);
        }
    }

    private int orientationToRotation(String orientation) {
        switch (orientation) {
            case "RIGHT":
                return 90;
            case "DOWN":
                return 180;
            case "LEFT":
                return 270;
            case "UP":
            default:
                return 0;
        }
    }
}
