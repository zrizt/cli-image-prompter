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
