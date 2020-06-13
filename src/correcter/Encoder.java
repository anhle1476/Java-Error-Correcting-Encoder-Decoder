package correcter;

public class Encoder extends Correcter {
    public Encoder() {
        super("send.txt", "encoded.txt");
    }

    @Override
    void process() {
        readFile();
        encoding();
        writeFile();
    }
    void encoding() {
        ByteArrayWriter byteWriter = new ByteArrayWriter(data.length * 2);
        byte encodeByte = 0b0000_0000;

        int dataBit2 = 0;
        int dataBit4 = 0;
        int dataBit5 = 0;
        int dataBit6 = 0;

        // loop through bytes from data
        for (byte currByte : data) {
            // loop through bits in byte
            for (int i = 0; i < 8; i++) {
                switch (i % 4) {
                    case 0:
                        dataBit2 = Utils.getBit(currByte, i);
                        if (dataBit2 == 1) encodeByte = Utils.writeBit(encodeByte, 2);
                        break;
                    case 1:
                        dataBit4 = Utils.getBit(currByte, i);
                        if (dataBit4 == 1) encodeByte = Utils.writeBit(encodeByte, 4);
                        break;
                    case 2:
                        dataBit5 = Utils.getBit(currByte, i);
                        if (dataBit5 == 1) encodeByte = Utils.writeBit(encodeByte, 5);
                        break;
                    case 3:
                        dataBit6 = Utils.getBit(currByte, i);
                        if (dataBit6 == 1) encodeByte = Utils.writeBit(encodeByte, 6);

                        // calculate & write parity bits (index 0 - 1 - 3) to encodeByte
                        if ((dataBit2 ^ dataBit4 ^ dataBit6) == 1) encodeByte = Utils.writeBit(encodeByte, 0);
                        if ((dataBit2 ^ dataBit5 ^ dataBit6) == 1) encodeByte = Utils.writeBit(encodeByte, 1);
                        if ((dataBit4 ^ dataBit5 ^ dataBit6) == 1) encodeByte = Utils.writeBit(encodeByte, 3);

                        // write encode byte into array
                        byteWriter.writeByte(encodeByte);

                        // reset to write next 4 bits
                        encodeByte = 0b0000_0000;
                        dataBit2 = 0;
                        dataBit4 = 0;
                        dataBit5 = 0;
                        dataBit6 = 0;
                        break;
                }
            }
        }
        result = byteWriter.getBytes();
    }
}
