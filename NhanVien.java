public abstract class NhanVien implements TinhLuong {
    protected String maNV;
    protected String hoTen;
    protected String chucVu;
    protected double luongCoBan;
    protected static int tongSoNhanVien = 0;

    public NhanVien(String maNV, String hoTen, String chucVu, double luongCoBan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.luongCoBan = luongCoBan;
        tongSoNhanVien++;
    }

    public String getMaNV() { return maNV; }
    public String getHoTen() { return hoTen; }
    public String getChucVu() { return chucVu; }
    public double getLuongCoBan() { return luongCoBan; }

    public static int getTongSoNhanVien() { return tongSoNhanVien; }

    public abstract double tinhLuong();

    public void hienThiThongTinCoBan() {
        System.out.printf("Ma NV: %s | Ho ten: %s | Chuc vu: %s%n", maNV, hoTen, chucVu);
    }

    public void hienThiThongTinChiTiet() {
        hienThiThongTinCoBan();
        System.out.printf("Loai: %s%n", this instanceof NhanVienChinhThuc ? "Full-time" : "Part-time");
    }
}
