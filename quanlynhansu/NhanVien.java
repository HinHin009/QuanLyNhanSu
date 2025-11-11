package quanlynhansu;

public abstract class NhanVien {
    protected String maNV;
    protected String hoTen;
    protected String chucVu;
    protected double luongCoBan;

    public NhanVien(String maNV, String hoTen, String chucVu, double luongCoBan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.luongCoBan = luongCoBan;
    }

    public String getMaNV() {
        return maNV;
    }

    public abstract double tinhLuong();

    public void hienThiThongTin() {
        System.out.println("Ma NV: " + maNV);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("Chuc vu: " + chucVu);
    }
}
