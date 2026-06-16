# CLI-IMAGE-PROMTER

Proyek CLI berbasis Java untuk membuat prompt gambar menggunakan template sistem, template kustom, dan penyimpanan riwayat ke database.
Proyek ini diinisiasi oleh kelompok guna memenuhi tugas besar mata kuliah Pemrograman 1.

## Kelompok

- Dea Amellya       24101140
- Muhammad Rafli    24101140
- Rizki Ramadani    241011400098

## Struktur Folder

```text
CLI-IMAGE-PROMTER/
│
├── .vscode/
│   └── Konfigurasi Visual Studio Code
│
├── bin/
│   └── Hasil kompilasi (.class)
│
├── lib/
│   └── Library atau dependency eksternal
│
├── src/
│   │
│   ├── core/
│   │   └── PromptGenerator.java
│   │       └── Logika utama pembentukan prompt
│   │
│   ├── database/
│   │   ├── DatabaseConfig.java
│   │   │   └── Konfigurasi koneksi database
│   │   │
│   │   └── DBManager.java
│   │       └── Operasi CRUD database
│   │
│   ├── main/
│   │   └── MainCLI.java
│   │       └── Entry point aplikasi CLI
│   │
│   ├── models/
│   │   ├── CustomTemplate.java
│   │   │   └── Model template kustom
│   │   │
│   │   ├── PromptTemplate.java
│   │   │   └── Model dasar template prompt
│   │   │
│   │   └── SystemTemplate.java
│   │       └── Model template bawaan sistem
│   │
│   └── utils/
│       └── FileExporter.java
│           └── Export hasil prompt ke file
│
├── .gitignore
│   └── Daftar file/folder yang diabaikan Git
│
└── README.md
    └── Dokumentasi proyek
```

## Arsitektur Singkat

### Main Layer
- `MainCLI.java`
- Menampilkan menu CLI.
- Menerima input pengguna.
- Menghubungkan seluruh komponen aplikasi.

### Core Layer
- `PromptGenerator.java`
- Membentuk prompt berdasarkan template dan parameter yang diberikan.

### Model Layer
- `PromptTemplate.java`
- `SystemTemplate.java`
- `CustomTemplate.java`

Menyimpan struktur data yang digunakan aplikasi.

### Database Layer
- `DatabaseConfig.java`
- `DBManager.java`

Mengelola:
- Koneksi database
- Penyimpanan history prompt
- Penyimpanan template kustom
- Operasi CRUD

### Utility Layer
- `FileExporter.java`

Mengekspor hasil prompt ke:
- `.txt`
- `.json`

## Alur Kerja

1. Aplikasi dijalankan melalui `MainCLI`.
2. Sistem mencoba koneksi database.
3. User memilih menu.
4. Prompt dibentuk oleh `PromptGenerator`.
5. Data disimpan ke database melalui `DBManager`.
6. Hasil dapat diekspor menggunakan `FileExporter`.

## Flowchart

<img width="1295" height="1022" alt="flowchart" src="https://github.com/user-attachments/assets/b377f8e9-166d-4fa2-8041-a56fab65495e" />

## Test Case Table

| ID Test | Skenario Uji (Test Case) | Input Pengguna (Tindakan) | Output yang Diharapkan (Ekspektasi) |  Status  |
|----------|--------------------------|----------------------------|--------------------------------------|-----------|
| TC-01 | Normal Case: Membuat prompt dari System Template. | Pilih Menu 1 → Pilih Template 2 → Subject: *A futuristic city* → Ekspor: N | Mencetak JSON dengan subjek yang benar dan kembali ke Menu Utama. | <ul><li>- [x] Pass</li><li>- [ ] Failed</li></ul> |
| TC-02 | Normal Case: Membuat Custom Template & Simpan ke DB. | Pilih Menu 2 → Isi semua parameter → Simpan: Y → Nama: *TestStyle* | Mencetak JSON, ada notifikasi sukses simpan DB, dan tampil di Menu 3. | <ul><li>- [x] Pass</li><li>- [ ] Failed</li></ul> |
| TC-03 | Invalid Input: Memilih menu yang tidak ada. | Pada Main Menu, ketik angka **9** atau huruf **A**. | Menampilkan pesan **[ERROR]** dan mengulang Main Menu tanpa crash. | <ul><li>- [x] Pass</li><li>- [ ] Failed</li></ul> |
| TC-04 | Invalid Input: Memilih ID template yang salah. | Pilih Menu 3 → Masukkan ID **999** (yang belum ada di DB). | Menampilkan pesan **[ERROR] ID Template tidak ditemukan** tanpa crash. | <ul><li>- [x] Pass</li><li>- [ ] Failed</li></ul> |
| TC-05 | Edge Case: Input String kosong. | Pilih Menu 2 → Langsung tekan Enter (kosong) di isian Subject dan Camera. | Sistem tetap menerima (variabel berisi string kosong `""`) dan tidak crash. | <ul><li>- [x] Pass</li><li>- [ ] Failed</li></ul> |
