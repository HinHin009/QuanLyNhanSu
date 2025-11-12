public class BangLuong {
    private String maLuong;
    private int ngay;
    private int thang;
    private int nam;
    private NhanVien nhanVien;
    private int soNgayLam; // ✅ chỉ dùng cho Full-time

    public BangLuong(String maLuong, int ngay, int thang, int nam, NhanVien nhanVien) {
        this.maLuong = maLuong;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.nhanVien = nhanVien;
    }

    public String getMaLuong() { return maLuong; }
    public int getNgay() { return ngay; }
    public int getThang() { return thang; }
    public int getNam() { return nam; }
    public NhanVien getNhanVien() { return nhanVien; }

    public void setSoNgayLam(int soNgayLam) {
        this.soNgayLam = soNgayLam;
    }

    public int getSoNgayLam() {
        return soNgayLam;
    }

    // ✅ Tính lương cho từng loại nhân viên
    public double tinhLuong() {
        if (nhanVien instanceof NhanVienChinhThuc nvct) {
            // Full-time: (LuongCoBan / 26) * soNgayLam + phuCap
            double luongTheoNgay = (nvct.getLuongCoBan() / 26.0) * soNgayLam;
            return luongTheoNgay + nvct.getPhuCap();
        } else if (nhanVien instanceof NhanVienThoiVu nvtv) {
            // Part-time: So gio lam * tien cong / gio
            return nvtv.getSoGioLam() * nvtv.getTienCong();
        }
        return 0;
    }

    // ✅ Hiển thị bảng lương chi tiết
    public void hienThiBangLuong() {
        System.out.println("========================================");
        System.out.println("Ma luong: " + maLuong);
        System.out.println("Nhan vien: " + nhanVien.getHoTen());
        System.out.println("Chuc vu: " + nhanVien.getChucVu());

        if (nhanVien instanceof NhanVienChinhThuc nvct) {
            // Hiển thị theo tháng
            System.out.println("Thoi gian: " + thang + "/" + nam);
            System.out.println("Loai: Full-time");
            System.out.println("So ngay lam: " + soNgayLam + " / 26 ngay");
            System.out.printf("Luong co ban: %,.0f VND%n", nvct.getLuongCoBan());
            System.out.printf("Phu cap: %,.0f VND%n", nvct.getPhuCap());
            System.out.printf("👉 Tong luong thuc linh: %,.0f VND%n", tinhLuong());
        } else if (nhanVien instanceof NhanVienThoiVu nvtv) {
            // Hiển thị theo ngày
            System.out.println("Thoi gian: " + ngay + "/" + thang + "/" + nam);
            System.out.println("Loai: Part-time");
            System.out.println("So gio lam: " + nvtv.getSoGioLam());
            System.out.printf("Tien cong/1 gio: %,.0f VND%n", nvtv.getTienCong());
            System.out.printf("👉 Luong thuc linh: %,.0f VND%n", tinhLuong());
        }

        System.out.println("========================================");
    }
}
