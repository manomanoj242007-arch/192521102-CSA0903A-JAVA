# Task A2 – Don’t Read What You Don’t Need (5 marks)

## A2.1 Predict the cost
50 MiB contains 52,428,800 bytes. A one-byte-at-a-time loop makes approximately 52,428,800 calls to FileInputStream.read(), plus one final EOF read (depending on counting convention). Each call incurs Java/native and often operating-system overhead, so it is slow despite O(N) total byte complexity.

## A2.2 Buffered redesign
```java
try (BufferedInputStream in =
         new BufferedInputStream(new FileInputStream(path))) {
    int lines = 0;
    int value;
    while ((value = in.read()) != -1) {
        if (value == '\\n') lines++;
    }
}
```
A more efficient byte-array loop reads chunks:
```java
byte[] buffer = new byte[64 * 1024];
int count;
while ((count = in.read(buffer)) != -1) {
    for (int i = 0; i < count; i++) {
        if (buffer[i] == '\\n') lines++;
    }
}
```
The loop still examines O(N) bytes, but requests roughly ceil(N / bufferSize) bulk reads rather than N individual read calls.

## A2.3 When buffering helps little
If the source is already an in-memory byte array, or a device/API already returns large blocks, another buffer adds copying and little benefit. It may also not help when processing time dominates I/O.

## Alternative: Files.readAllLines
Time is O(N), but memory is O(N) plus per-line object overhead; a 50 MB file may require much more than 50 MB heap. Buffered streaming uses O(B) memory, where B is buffer size. Use readAllLines for small files needing random line access; use streaming for large logs.
