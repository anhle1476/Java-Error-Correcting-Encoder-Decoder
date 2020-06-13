package correcter;

public class Decoder extends Correcter {
    public Decoder() {
        super("received.txt", "decoded.txt");
    }

    @Override
    void process() {
            readFile();
            decoding();
            writeFile();
    }

    private void decoding() {
        int originalByte = data.length / 2;
        ByteArrayWriter byteWriter = new ByteArrayWriter(originalByte);

        for (byte currByte : data) {
            int[] fixByte = byteCorrecter(currByte);
            byteWriter.write(fixByte[3]);
            byteWriter.write(fixByte[5]);
            byteWriter.write(fixByte[6]);
            byteWriter.write(fixByte[7]);
        }

        result = byteWriter.getBytes();
    }

    private int[] byteCorrecter(byte thisByte) {
        // make parity bit be the leftmost bit & convert to array to find error easier
        byte shiftByte = (byte) (thisByte >> 1);
        int[] bits = new int[8];
        for (int i = 0; i < 8; i++) {
            bits[i] = Utils.getBit(shiftByte, i);
        }

        // find error bit by sum of the failed parity bits
        int failedIndex = 0;
        if (bits[1] != (bits[3] ^ bits[5] ^ bits[7])) failedIndex += 1;
        if (bits[2] != (bits[3] ^ bits[6] ^ bits[7])) failedIndex += 2;
        if (bits[4] != (bits[5] ^ bits[6] ^ bits[7])) failedIndex += 4;
        //invert the failed bit
        bits[failedIndex] = bits[failedIndex] == 1 ? 0 : 1;
        return bits;
    }
}
