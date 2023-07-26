import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TempatNgopi tempatNgopi = new TempatNgopi();

        // Menambahkan daftar minuman
        tempatNgopi.tambahMenu(1, new Minuman("Kopi", 15000));
        tempatNgopi.tambahMenu(2, new Minuman("Teh", 12000));
        tempatNgopi.tambahMenu(3, new Minuman("Cappuccino", 20000));
        tempatNgopi.tambahMenu(4, new Minuman("Espresso", 18000));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama pembeli: ");
        String namaPembeli = scanner.nextLine();
        tempatNgopi.setNamaPembeli(namaPembeli);

        System.out.println("\nMenu Minuman:");
        for (Integer nomorMenu : tempatNgopi.getDaftarMenu().keySet()) {
            Minuman minuman = tempatNgopi.getDaftarMenu().get(nomorMenu);
            System.out.println(nomorMenu + ". " + minuman.getNama() + " (Rp " + minuman.getHarga() + ")");
        }

        List<Integer> pesananList = new ArrayList<>();
        double totalPembayaran = 0;

        // Loop untuk memilih menu hingga admin mengetik "S"
        while (true) {
            System.out.print("Masukkan nomor menu yang ingin dipesan (ketik 'S' untuk selesai): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("S")) {
                break;
            }

            try {
                int nomorMenu = Integer.parseInt(input);
                if (tempatNgopi.getDaftarMenu().containsKey(nomorMenu)) {
                    pesananList.add(nomorMenu);
                    totalPembayaran += tempatNgopi.getDaftarMenu().get(nomorMenu).getHarga();
                } else {
                    System.out.println("Nomor menu tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
            }
        }

        // Mencetak struk pembayaran
        System.out.println("\n======== Struk Pembayaran ========");
        for (Integer nomorMenu : pesananList) {
            Minuman minuman = tempatNgopi.getDaftarMenu().get(nomorMenu);
            if (minuman != null) {
                System.out.println(minuman.getNama() + "\t\tRp " + minuman.getHarga());
            }
        }
        System.out.println("<<<<<<<< Terima Kasih >>>>>>>>");
        System.out.println("Total Pembayaran\tRp " + totalPembayaran);

        // Meminta input pembayaran dari kasir
        System.out.print("Masukkan uang yang diberikan pembeli: Rp ");
        double uangDiberikan = scanner.nextDouble();

        // Menghitung kembalian
        double kembalian = uangDiberikan - totalPembayaran;
        if (kembalian >= 0) {
            System.out.println("Kembalian\t\tRp " + kembalian);
        } else {
            System.out.println("Uang yang diberikan kurang. Pembayaran gagal.");
        }

        // Menyimpan data transaksi dalam file transaksi.txt
        tempatNgopi.hitungTotalPembayaran(pesananList);
        tempatNgopi.simpanTransaksi(uangDiberikan, kembalian);
    }
}
