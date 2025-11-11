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

    public String getMaNV() {
        return maNV;
    }

    public static int getTongSoNhanVien() {
        return tongSoNhanVien;
    }

    public abstract double tinhLuong();

    public void hienThiThongTin() {
        System.out.println("Ma NV: " + maNV);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Chuc vu: " + chucVu);
    }
}
