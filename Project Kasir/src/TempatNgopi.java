import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempatNgopi {
    private Map<Integer, Minuman> daftarMenu;
    private List<Pesanan> daftarPesanan;
    private double totalPembayaran;
    private String namaPembeli;

    public TempatNgopi() {
        daftarMenu = new HashMap<>();
        daftarPesanan = new ArrayList<>();
        totalPembayaran = 0;
        namaPembeli = "";
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void tambahMenu(int nomor, Minuman minuman) {
        daftarMenu.put(nomor, minuman);
    }

    public Map<Integer, Minuman> getDaftarMenu() {
        return daftarMenu;
    }

    public List<Pesanan> getDaftarPesanan() {
        return daftarPesanan;
    }

    public void hitungTotalPembayaran(List<Integer> menuDipesan) {
        totalPembayaran = 0;
        daftarPesanan.clear();

        for (Integer nomorMenu : menuDipesan) {
            Minuman minuman = daftarMenu.get(nomorMenu);
            if (minuman != null) {
                totalPembayaran += minuman.getHarga();
                tambahPesanan(new Pesanan(minuman.getNama(), minuman.getHarga()));
            }
        }
    }

    private void tambahPesanan(Pesanan pesanan) {
        daftarPesanan.add(pesanan);
    }

    public double getTotalPembayaran() {
        return totalPembayaran;
    }

    public void simpanTransaksi(double uangDiberikan, double kembalian) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("transaksi.txt", true));
            writer.write("Nama Pembeli: " + namaPembeli + "\n");
            writer.write("======== Pesanan =======\n");
            for (Pesanan pesanan : daftarPesanan) {
                writer.write(pesanan.getNamaMenu() + "\t\tRp " + pesanan.getHargaMenu() + "\n");
            }
            writer.write("<<<<<<<< ============ >>>>>>>>\n");
            writer.write("Total Pembayaran\tRp " + totalPembayaran + "\n");
            writer.write("Uang Diberikan\t\tRp " + uangDiberikan + "\n");
            writer.write("Kembalian\t\tRp " + kembalian + "\n");
            writer.write("<<<<<<<< Terima Kasih >>>>>>>>\n");
            writer.close();
            System.out.println("Transaksi berhasil disimpan dalam file transaksi.txt.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan transaksi.");
            e.printStackTrace();
        }
    }
}
