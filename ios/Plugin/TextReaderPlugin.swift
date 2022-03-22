import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@available(iOS 13.0, *)
@objc(TextReaderPlugin)
public class TextReaderPlugin: CAPPlugin {
    
    @objc func detectText(_ call: CAPPluginCall){
        guard var filepath = call.getString("filename") else{
            call.reject("file not found")
            return
        }
        
        // removeFirst(7) removes the initial "file://"
        filepath.removeFirst(7)
        guard let image = UIImage(contentsOfFile: filepath) else {
            call.reject("file does not contain an image")
            return
        }
        
        TextReader(call: call, image: image).detectText()
    }
}
