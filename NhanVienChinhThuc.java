public class NhanVienChinhThuc extends NhanVien {
    private double phuCap;

    public NhanVienChinhThuc(String maNV, String hoTen, String chucVu, double luongCoBan, double phuCap) {
        super(maNV, hoTen, chucVu, luongCoBan);
        this.phuCap = phuCap;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    @Override
    public double tinhLuong() {
        return luongCoBan + phuCap;
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("Loai: Nhan vien chinh thuc (Full-time)");
        System.out.printf("Luong co ban: %,.0f VND%n", luongCoBan);
        System.out.printf("Phu cap: %,.0f VND%n", phuCap);
        System.out.printf("Luong thuc linh: %,.0f VND%n", tinhLuong());
    }
}
