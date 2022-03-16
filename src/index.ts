import { registerPlugin } from '@capacitor/core';

import type { TextReaderPlugin } from './definitions';

const TextReader = registerPlugin<TextReaderPlugin>('TextReader', {
  web: () => import('./web').then(m => new m.TextReaderWeb()),
});

export * from './definitions';
export { TextReader };
