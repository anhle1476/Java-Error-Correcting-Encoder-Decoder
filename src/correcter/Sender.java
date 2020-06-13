package correcter;

import java.util.Random;

public class Sender extends Correcter {

    public Sender() {
        super("encoded.txt", "received.txt");
    }

    @Override
    void process() {
        readFile();
        sending();
        writeFile();
    }

    private void sending() {
        Random random = new Random();
        int len = data.length;

        result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Utils.randomFlip(data[i], random);
        }
    }
}
