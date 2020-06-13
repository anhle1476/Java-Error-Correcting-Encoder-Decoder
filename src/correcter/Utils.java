package correcter;

import java.util.Random;

public class Utils {
    public static int getBit(byte b, int index) {
        return (b & (1 << (7 - index))) == 0 ? 0 : 1;
    }

    public static byte writeBit(byte b, int index) {
        return (byte) (b | (1 << (7 - index)));
    }

    public static byte randomFlip(byte b, Random random) {
        int index = random.nextInt(8);
        return (byte) (b ^ (1 << (7 - index)));
    }

    public static void hexPrinting(byte[] data) {
        StringBuilder printer = new StringBuilder();
        for (byte symbol : data) {
            printer.append(byteToHex(symbol)).append(" ");
        }
        System.out.println("hex view: " + printer.toString());
    }

    public static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits).toUpperCase();
    }
}
