package correcter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Write a mode (encode, send, decode): ");
        try (Scanner scanner = new Scanner(System.in)) {
            Correcter correcter = null;
            switch (scanner.nextLine()) {
                case "encode":
                    correcter = new Encoder();
                    break;
                case "send":
                    correcter = new Sender();
                    break;
                case "decode":
                    correcter = new Decoder();
                    break;
                default:
                    System.out.println("Action invalid.");
            }
            if (correcter != null) {
                correcter.process();
            }
        } catch (NullPointerException exception) {
            System.out.println("Error: Null pointer exception.");
            exception.printStackTrace();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
