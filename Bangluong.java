import java.util.ArrayList;

public class Bangluong {
    private int thang;
    private int nam;
    private ArrayList<NhanVien> dsNhanVien = new ArrayList<>();

    public Bangluong(int thang, int nam) {
        this.thang = thang;
        this.nam = nam;
    }

    public void themNhanVien(NhanVien nv) {
        dsNhanVien.add(nv);
    }

    public void hienThiBangLuong() {
        System.out.println("\n===== BẢNG LƯƠNG THÁNG " + thang + "/" + nam + " =====");
        double tongLuong = 0;
        for (NhanVien nv : dsNhanVien) {
            System.out.printf("%-10s %-20s %-15s %,12.0f VND%n",
                    nv.getMaNV(), nv.hoTen, nv.chucVu, nv.tinhLuong());
            tongLuong += nv.tinhLuong();
        }
        System.out.println("---------------------------------------------");
        System.out.printf("TỔNG LƯƠNG TOÀN CÔNG TY: %,12.0f VND%n", tongLuong);
    }
}
