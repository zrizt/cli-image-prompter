package utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileExporter {
    
    // Method untuk menulis teks ke dalam file (.txt atau .json)
    public static void saveToFile(String content, String filename) {
        // Menggunakan try-with-resources agar file otomatis tertutup
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
            System.out.println("[SUCCESS] Berhasil diekspor ke file: " + filename);
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menulis file: " + e.getMessage());
        }
    }
}