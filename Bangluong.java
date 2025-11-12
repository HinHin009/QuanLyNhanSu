public class BangLuong {
    private String maLuong;
    private int ngay, thang, nam;
    private NhanVien nhanVien;
    private double tongLuong;

    public BangLuong(String maLuong, int ngay, int thang, int nam, NhanVien nhanVien) {
        this.maLuong = maLuong;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
        this.nhanVien = nhanVien;
        this.tongLuong = nhanVien != null ? nhanVien.tinhLuong() : 0;
    }

    public String getMaLuong() { return maLuong; }
    public NhanVien getNhanVien() { return nhanVien; }
    public int getNgay() { return ngay; }
    public int getThang() { return thang; }
    public int getNam() { return nam; }

    public double tinhLuong() {
        // Cập nhật theo trạng thái nhân viên nếu cần
        tongLuong = nhanVien != null ? nhanVien.tinhLuong() : 0;
        return tongLuong;
    }

    public void hienThiBangLuong() {
        System.out.println("Ma luong: " + maLuong);
        if (nhanVien != null) {
            System.out.println("Ma NV: " + nhanVien.getMaNV() + " | Ten: " + nhanVien.getHoTen());
        }
        System.out.printf("Ngay: %d/%d/%d%n", ngay, thang, nam);
        System.out.printf("Tong luong: %,.0f VND%n", tinhLuong());
    }
}
