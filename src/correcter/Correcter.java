package correcter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

abstract class Correcter {
    private String fileInput;
    private String fileOutput;
    protected byte[] data;
    protected byte[] result;

    public Correcter(String fileInput, String fileOutput) {
        this.fileInput = fileInput;
        this.fileOutput = fileOutput;
    }

    abstract void process();

    protected void readFile() {
        try {
            data = Files.readAllBytes(Path.of(fileInput));
        } catch (IOException exception) {
            System.out.println("Error: File reading failed. " + exception.getMessage());
        } catch (NullPointerException exception) {
            System.out.println("Error: File writing failed (NPE). " + exception.getMessage());
        }
        System.out.println("\n Read data from: " + this.fileInput);
        System.out.println("text view: " + new String(this.data));
        Utils.hexPrinting(this.data);

    }
    protected void writeFile() {
        System.out.println("\nWrite data to: " + this.fileOutput);
        Utils.hexPrinting(this.result);

        try (OutputStream fileWriter = new FileOutputStream(new File(this.fileOutput))) {
            fileWriter.write(this.result);
        } catch (IOException exception) {
            System.out.println("Error: File writing failed (IOE). " + exception.getMessage());
        }
    }
}
