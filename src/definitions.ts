export interface TextReaderPlugin {
  detectText(options: { filename: string, orientation?: ImageOrientation }): Promise<{ textDetections: TextDetection[] }>;
}

export interface TextDetection {
  // bottomLeft: [number, number]; // [x-coordinate, y-coordinate]
  // bottomRight: [number, number]; // [x-coordinate, y-coordinate]
  // topLeft: [number, number]; // [x-coordinate, y-coordinate]
  // topRight: [number, number]; // [x-coordinate, y-coordinate]
  text: string;
}

export enum ImageOrientation {
  Up = "UP",
  Down = "DOWN",
  Left = "LEFT",
  Right = "RIGHT",
}
