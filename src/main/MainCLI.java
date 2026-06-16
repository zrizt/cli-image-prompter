package main;

import database.DBManager;
import core.PromptGenerator;
import models.CustomTemplate;
import models.SystemTemplate;
import utils.FileExporter;

import java.util.Scanner;
import java.util.List;
public class MainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DBManager dbManager = new DBManager();
        boolean isRunning = true;

        SystemTemplate cinematic = new SystemTemplate("Cinmatic Movie", "", "ARRI Alexa 65", "35mm", "f/1.4", "Volumetric Fog, Golden Hour", "Teal and Orange");
        SystemTemplate digicam = new SystemTemplate("Retro Digicam", "", "Sony Cyber-shot 2005", "10mm", "f/5.6", "Harsh Flash, Direct Sunlight", "Muted, Low Contrast");

        System.out.println("====================================================");
        System.out.println("             DRR Image Prompt Generator!            ");
        System.out.println("====================================================");
        
        while (isRunning) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Use System Template");
            System.out.println("2. Custom Prompt Builder");
            System.out.println("3. Load Custom Templates (Database)");
            System.out.println("4. View History");
            System.out.println("5. Exit");
            System.out.print("Pilih menu [1-5]: ");

            String inputMenu = scanner.nextLine();
            switch (inputMenu) {
                case "1":
                    System.out.println("\n--- SYSTEM TEMPLATES ---");
                    System.out.println("1. Cinematic Movie");
                    System.out.println("2. Retro Digicam");
                    System.out.print("Pilih template [1-2]: ");
                    String pilihTemp = scanner.nextLine();

                    System.out.print("Masukkan Subject (misal: a cyberpunk ninja in tokyo): ");
                    String subject1 = scanner.nextLine();

                    String hasilJson1 = "";
                    if (pilihTemp.equals("1")) {
                        // Menggunakan Polymorphism, mengubah subject pada objek baru
                        SystemTemplate t1 = new SystemTemplate("Cinematic", subject1, cinematic.getCameraModel(), cinematic.getFocalLength(), cinematic.getAperture(), cinematic.getLightingType(), cinematic.getColorGrading());
                        hasilJson1 = PromptGenerator.generateJson(t1);
                    } else if (pilihTemp.equals("2")) {
                        SystemTemplate t2 = new SystemTemplate("Digicam", subject1, digicam.getCameraModel(), digicam.getFocalLength(), digicam.getAperture(), digicam.getLightingType(), digicam.getColorGrading());
                        hasilJson1 = PromptGenerator.generateJson(t2);
                    } else {
                        System.out.println("[ERROR] Pilihan tidak valid!");
                        continue;
                    }

                    System.out.println("\n[OUTPUT PROMPT]");
                    System.out.println(hasilJson1);
                    dbManager.saveHistory(hasilJson1);

                    exportPrompt(scanner, hasilJson1);
                    break;

                case "2":
                    System.out.println("\n--- CUSTOM PROMPT BUILDER ---");
                    System.out.print("Subject        : "); String subject2 = scanner.nextLine();
                    System.out.print("Camera Model   : "); String cam = scanner.nextLine();
                    System.out.print("Focal Length   : "); String focal = scanner.nextLine();
                    System.out.print("Aperture       : "); String aperture = scanner.nextLine();
                    System.out.print("Lighting Type  : "); String light = scanner.nextLine();
                    System.out.print("Color Grading  : "); String color = scanner.nextLine();

                    // Instansiasi sementara sebagai SystemTemplate tanpa nama style khusus
                    SystemTemplate customTemp = new SystemTemplate("Manual Custom", subject2, cam, focal, aperture, light, color);
                    String hasilJson2 = PromptGenerator.generateJson(customTemp);
                    
                    System.out.println("\n[OUTPUT PROMPT]");
                    System.out.println(hasilJson2);
                    dbManager.saveHistory(hasilJson2);

                    System.out.print("Simpan konfigurasi ini sebagai Template di Database? (Y/N): ");
                    if (scanner.nextLine().equalsIgnoreCase("Y")) {
                        System.out.print("Nama Template Baru: ");
                        String namaTemp = scanner.nextLine();
                        dbManager.saveCustomTemplate(namaTemp, "", cam, focal, aperture, light, color);
                    }

                    exportPrompt(scanner, hasilJson2);
                    break;

                case "3":
                    System.out.println("\n--- CUSTOM TEMPLATES (DB) ---");
                    List<CustomTemplate> templates = dbManager.getCustomTemplates();
                    
                    if (templates.isEmpty()) {
                        System.out.println("Belum ada template tersimpan.");
                    } else {
                        // Tampilkan daftar template
                        for (CustomTemplate t : templates) {
                            System.out.println("[" + t.getId() + "] " + t.getTemplateName() + " (Lens: " + t.getFocalLength() + ")");
                        }
                        
                        // Tambahan Logika Interaktif
                        System.out.print("\nPilih ID Template untuk digunakan (atau ketik 0 untuk batal): ");
                        String idInput = scanner.nextLine();
                        
                        if (!idInput.equals("0")) {
                            try {
                                int selectedId = Integer.parseInt(idInput);
                                CustomTemplate selectedTemplate = null;
                                
                                // Cari template yang sesuai dengan ID
                                for (CustomTemplate t : templates) {
                                    if (t.getId() == selectedId) {
                                        selectedTemplate = t;
                                        break;
                                    }
                                }
                                
                                // Jika template ditemukan, proses generate
                                if (selectedTemplate != null) {
                                    System.out.print("Masukkan Subject baru untuk template ini: ");
                                    String newSubject = scanner.nextLine();
                                    
                                    // Instansiasi object baru dengan subject dari user, tapi parameter dari database
                                    CustomTemplate templateToGenerate = new CustomTemplate(
                                        selectedTemplate.getId(),
                                        selectedTemplate.getTemplateName(),
                                        newSubject,
                                        selectedTemplate.getCameraModel(),
                                        selectedTemplate.getFocalLength(),
                                        selectedTemplate.getAperture(),
                                        selectedTemplate.getLightingType(),
                                        selectedTemplate.getColorGrading()
                                    );
                                    
                                    String hasilJson3 = PromptGenerator.generateJson(templateToGenerate);
                                    
                                    System.out.println("\n[OUTPUT PROMPT]");
                                    System.out.println(hasilJson3);
                                    
                                    dbManager.saveHistory(hasilJson3);
                                    exportPrompt(scanner, hasilJson3);
                                    
                                } else {
                                    System.out.println("[ERROR] ID Template tidak ditemukan di database.");
                                }
                                
                            } catch (NumberFormatException e) {
                                System.out.println("[ERROR] Input ID harus berupa angka yang valid.");
                            }
                        }
                    }
                    break;

                case "4":
                    dbManager.showHistory();
                    break;

                case "5":
                    System.out.println("Menutup sistem... Sampai jumpa!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("[ERROR] Masukkan angka 1 sampai 5.");
            }
    
        }
        scanner.close();
    }
    // helper method untuk export file agar tidak duplikasi code
    private static void exportPrompt(Scanner scanner, String content) {
        System.out.print("Export prompt ini ke file? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Masukkan nama file (contoh: hasil.txt atau prompt.json): ");
            String filename = scanner.nextLine();
            FileExporter.saveToFile(content, filename);
        }
    }
}
