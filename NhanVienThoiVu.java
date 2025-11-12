public class NhanVienThoiVu extends NhanVien {
    private int soGioLam;
    private double tienCong;

    public NhanVienThoiVu(String maNV, String hoTen, String chucVu, int soGioLam, double tienCong) {
        super(maNV, hoTen, chucVu, 0);
        this.soGioLam = soGioLam;
        this.tienCong = tienCong;
    }

    public int getSoGioLam() { return soGioLam; }
    public void setSoGioLam(int soGioLam) { this.soGioLam = soGioLam; }
    public double getTienCong() { return tienCong; }
    public void setTienCong(double tienCong) { this.tienCong = tienCong; }

    @Override
    public double tinhLuong() {
        return soGioLam * tienCong;
    }

    @Override
    public void hienThiThongTinChiTiet() {
        super.hienThiThongTinChiTiet();
        System.out.println("So gio lam: " + soGioLam);
        System.out.printf("Tien cong/1 gio: %,.0f VND%n", tienCong);
        System.out.printf("Luong thuc linh: %,.0f VND%n", tinhLuong());
    }
}
