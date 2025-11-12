import java.util.ArrayList;

public class PhongBan {
    private String maPB;
    private String tenPB;
    private String truongPhong;
    private ArrayList<NhanVien> dsNhanVien = new ArrayList<>();

    public PhongBan(String maPB, String tenPB, String truongPhong) {
        this.maPB = maPB;
        this.tenPB = tenPB;
        this.truongPhong = truongPhong;
    }

    public String getMaPB() { return maPB; }
    public String getTenPB() { return tenPB; }
    public String getTruongPhong() { return truongPhong; }

    public void themNhanVien(NhanVien nv) { if (nv != null) dsNhanVien.add(nv); }

    public boolean xoaNhanVien(String maNV) {
        for (int i = 0; i < dsNhanVien.size(); i++) {
            if (dsNhanVien.get(i).getMaNV().equalsIgnoreCase(maNV)) {
                dsNhanVien.remove(i);
                return true;
            }
        }
        return false;
    }

    public void hienThiNhanVien() {
        System.out.println("Phong: " + tenPB + " - Truong phong: " + truongPhong);
        if (dsNhanVien.isEmpty()) {
            System.out.println("  (Chua co nhan vien)");
            return;
        }
        for (NhanVien nv : dsNhanVien) {
            System.out.print("  ");
            nv.hienThiThongTinCoBan();
        }
    }

    public double tinhTongLuong() {
        double s = 0;
        for (NhanVien nv : dsNhanVien) s += nv.tinhLuong();
        return s;
    }
     // ✅ Thêm hàm này
    public ArrayList<NhanVien> getDanhSach() {
        return dsNhanVien;
    }
}
