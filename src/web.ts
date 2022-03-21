import { WebPlugin } from '@capacitor/core';

import type { ImageOrientation, TextDetection, TextReaderPlugin } from './definitions';

export class TextReaderWeb extends WebPlugin implements TextReaderPlugin {
  detectText(options: { filename: string; orientation?: ImageOrientation | undefined; }): Promise<{ textDetections: TextDetection[] }> {
    console.log("Filepath", options.filename);
    console.log("Orientation", options.orientation);
    throw new Error('Method not implemented.');
  }


}
