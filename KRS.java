import java.util.Scanner;

public class KRS {
    static Scanner input = new Scanner(System.in);
    static String[][] dataKRS = new String[20][5];
    static int count = 0;

    static void menu() {
        String menu[] = { "Tambah Data KRS", "Tampilkan Daftar KRS Mahasiswa", "Analisis Data KRS", "Keluar" };
        int inputMenu = 0;
        while (inputMenu != 4) {
            System.out.println("\n=== Sistem Pemantauan KRS Mahasiswa ===");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + ". " + menu[i]);
            }
            System.out.print("Pilih menu: ");
            inputMenu = input.nextInt();

            switch (inputMenu) {
                case 1:
                    addData();
                    break;
                case 2:
                    tampilData();
                    break;
                case 3:
                    analisisData();
                    break;
                case 4:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Menu tidak tersedia");
                    break;
            }
        }
    }

    static void addData() {
        String pilih;
        int sks, totalSKS = 0;
        System.out.println("\n--- Tambah Data KRS ---");
        System.out.print("Nama Mahasiswa: ");
        String nama = input.nextLine();
        System.out.println("NIM: ");
        String nim = input.nextLine();
        input.nextLine();
        do {
            System.out.print("Kode Mata Kuliah: ");
            String kode = input.nextLine();
            System.out.print("Nama Mata Kuliah: ");
            String matkul = input.nextLine();
            do {
                System.out.print("Jumlah SKS (1-3): ");
                sks = input.nextInt();
                System.out.println("Data mata kuliah berhasil ditambahkan.");
                if (sks < 1 || sks > 3) {
                    System.out.println("Jumlah sks harus antara 1-3. Silahkan input ulang.");
                }
                if (totalSKS >= 24){
                    System.out.println("Total SKS tidak boleh lebih dari 24. Silahkan input ulang.");
                    count--;
                    break;
                }
            } while (sks < 1 || sks > 3);

            dataKRS[count][0] = nim;
            dataKRS[count][1] = nama;
            dataKRS[count][2] = kode;
            dataKRS[count][3] = matkul;
            dataKRS[count][4] = Integer.toString(sks);
            count++;

            totalSKS += sks;
            System.out.print("Tambah mata kuliah lain (y/n): ");
            pilih = input.next();
        } while (pilih.equalsIgnoreCase("y"));


        System.out.println("Total SKS yang diambil: " + totalSKS);
    }

    static void tampilData() {
        String[][] tampil = new String[4][2];
        System.out.println("\n--- Tampilkan Daftar KRS Mahasiswa ---");
        System.out.print("Masukkan NIM mahasiswa: ");
        String cariNIM = input.next();

        System.out.println("\nDaftar KRS");
        System.out.printf("%-10s %-10s %-10s %-25s %s\n", "NIM", "Nama", "Kode MK", "Nama Mata Kuliah", "SKS");
        int totalSKS = 0;
        for (int i = 0; i < count; i++) {
            if (dataKRS[i][0].equals(cariNIM)) {
                System.out.printf("%-10s %-10s %-10s %-25s %s\n", dataKRS[i][0], dataKRS[i][1], dataKRS[i][2], dataKRS[i][3], dataKRS[i][4]);
                totalSKS += Integer.parseInt(dataKRS[i][4]);
            }
        }

        System.out.println("Total SKS: " + totalSKS);
    }

    static void analisisData() {
        System.out.println("\n--- Analisis Data KRS ---");
        int jmlMhsKurang20 = 0;

        for (int i = 0; i < count; i++) {
            String currentNIM = dataKRS[i][0];
            int totalSKS = 0;

            for (int j = 0; j < count; j++) {
                if (dataKRS[j][0].equals(currentNIM)) {
                    totalSKS += Integer.parseInt(dataKRS[j][4]);
                }
            }

            if (totalSKS < 20) {
                jmlMhsKurang20++;
                for (int j = 0; j < count; j++) {
                    if (dataKRS[j][0].equals(currentNIM)) {
                        dataKRS[j][0] = "";
                    }
                }
            }
        }

        System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + jmlMhsKurang20);
    }

    public static void main(String[] args) {
        menu();
    }
}
