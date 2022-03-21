package com.vlx.plugins.text.reader;

import android.graphics.Bitmap;

import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextReader {
    InputImage image;
    ArrayList<Map<String, Object>> detectedText;

    public void detectText(PluginCall call, Bitmap bitmap) {
        try {
            image = InputImage.fromBitmap(bitmap, 0);
            detectedText = new ArrayList<>();
            TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
            recognizer.process(image).addOnSuccessListener((detectedBlocks) -> {
                for (Text.TextBlock block : detectedBlocks.getTextBlocks()) {
                    for (Text.Line line : block.getLines()) {
                        Map<String, Object> textDetection = new HashMap<>();
                        textDetection.put("text", line.getText());
                        detectedText.add(textDetection);
                    }
                }
                call.resolve(new JSObject().put("textDetections", new JSONArray(detectedText)));

            }).addOnFailureListener(e ->
                    call.reject("TextRecognizer couldn't process the given image", e)
            );

        } catch (Exception e) {
            e.printStackTrace();
            call.reject(e.getLocalizedMessage(), e);
        }
    }
}
