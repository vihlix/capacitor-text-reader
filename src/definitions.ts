export interface TextReaderPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
