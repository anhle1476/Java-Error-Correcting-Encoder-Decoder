package correcter;

import java.util.Arrays;

public class ByteArrayWriter {
    private byte[] bytes;
    private int bitIndex = 0;
    private int byteIndex = 0;
    private int lastByteIndex;

    public ByteArrayWriter(int length) {
        this.bytes = new byte[length];
        Arrays.fill(bytes, (byte) 0b0000_0000);
        lastByteIndex = bytes.length - 1;
    }

    public void write(int bit) throws NullPointerException {
        if (byteIndex > lastByteIndex) return;
        if (bit == 1) {
            bytes[byteIndex] = Utils.writeBit(bytes[byteIndex], bitIndex);
        }
        if (++bitIndex > 7) {
            bitIndex = 0;
            byteIndex++;
        }
    }

    public void writeByte(byte byteToWrite) {
        bytes[byteIndex++] = byteToWrite;
        bitIndex = 0;
    }

    public byte[] getBytes() {
        return this.bytes;
    }
}
