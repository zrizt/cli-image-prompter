package database;

import models.CustomTemplate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    // 1. CREATE: Simpan hasil output ke tabel history
    public void saveHistory(String finalOutput) {
        String query = "INSERT INTO history (final_output) VALUES (?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, finalOutput);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[ERROR] Gagal menyimpan history: " + e.getMessage());
        }
    }

    // 2. READ: Tampilkan 5 history terakhir
    public void showHistory() {
        String query = "SELECT * FROM history ORDER BY waktu_generate DESC LIMIT 5";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\n=== 5 HISTORY PROMPT TERAKHIR ===");
            while (rs.next()) {
                System.out.println("[" + rs.getTimestamp("waktu_generate") + "]");
                System.out.println(rs.getString("final_output"));
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Gagal membaca history: " + e.getMessage());
        }
    }

    // 3. CREATE: Simpan custom template baru dari user
    public void saveCustomTemplate(String nama, String subject, String camera, String focal, String aperture, String lighting, String color) {
        String query = "INSERT INTO templates (nama_template, subject, camera_model, focal_length, aperture, lighting_type, color_grading) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, subject);
            stmt.setString(3, camera);
            stmt.setString(4, focal);
            stmt.setString(5, aperture);
            stmt.setString(6, lighting);
            stmt.setString(7, color);
            stmt.executeUpdate();
            System.out.println("[SUCCESS] Custom Template '" + nama + "' berhasil disimpan ke Database!");
        } catch (SQLException e) {
            System.out.println("[ERROR] Gagal menyimpan template: " + e.getMessage());
        }
    }

    // 4. READ: Ambil semua custom template dan jadikan Object Java
    public List<CustomTemplate> getCustomTemplates() {
        List<CustomTemplate> templates = new ArrayList<>();
        String query = "SELECT * FROM templates";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Instansiasi object dari database ke bentuk OOP
                templates.add(new CustomTemplate(
                    rs.getInt("id"),
                    rs.getString("nama_template"),
                    rs.getString("subject"),
                    rs.getString("camera_model"),
                    rs.getString("focal_length"),
                    rs.getString("aperture"),
                    rs.getString("lighting_type"),
                    rs.getString("color_grading")
                ));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Gagal mengambil custom templates: " + e.getMessage());
        }
        return templates;
    }
}