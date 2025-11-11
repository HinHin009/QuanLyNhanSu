import java.util.ArrayList;

public class Phongban {
    private String maPB;
    private String tenPB;
    private String truongPhong;
    private ArrayList<NhanVien> dsNhanVien = new ArrayList<>();

    public Phongban(String maPB, String tenPB, String truongPhong) {
        this.maPB = maPB;
        this.tenPB = tenPB;
        this.truongPhong = truongPhong;
    }

    public String getMaPB() {
        return maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public String getTruongPhong() {
        return truongPhong;
    }

    public void themNhanVien(NhanVien nv) {
        dsNhanVien.add(nv);
    }

    public void hienThiNhanVien() {
        System.out.println("\n📋 Danh sach nhan vien phong: " + tenPB);
        for (NhanVien nv : dsNhanVien) {
            nv.hienThiThongTin();
            System.out.println("-----------------------------");
        }
    }

    public double tinhTongLuong() {
        double tong = 0;
        for (NhanVien nv : dsNhanVien)
            tong += nv.tinhLuong();
        return tong;
    }
}
