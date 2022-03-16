import { WebPlugin } from '@capacitor/core';

import type { TextReaderPlugin } from './definitions';

export class TextReaderWeb extends WebPlugin implements TextReaderPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
