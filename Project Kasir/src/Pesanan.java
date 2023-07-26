public class Pesanan {
    private String namaMenu;
    private double hargaMenu;

    public Pesanan(String namaMenu, double hargaMenu) {
        this.namaMenu = namaMenu;
        this.hargaMenu = hargaMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public double getHargaMenu() {
        return hargaMenu;
    }
}
