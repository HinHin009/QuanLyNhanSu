import java.util.ArrayList;

public class DanhSachNhanVien {
    private ArrayList<NhanVien> dsNhanVien = new ArrayList<>();

    public DanhSachNhanVien() {}

    public void themNhanVien(NhanVien nv) {
        if (nv == null) return;
        // kiểm tra trùng mã
        if (timNhanVien(nv.getMaNV()) != null) {
            System.out.println("Ma NV da ton tai: " + nv.getMaNV());
            return;
        }
        dsNhanVien.add(nv);
    }

    public NhanVien timNhanVien(String maNV) {
        for (NhanVien nv : dsNhanVien) {
            if (nv.getMaNV().equalsIgnoreCase(maNV)) return nv;
        }
        return null;
    }

    public boolean xoaNhanVien(String maNV) {
        for (int i = 0; i < dsNhanVien.size(); i++) {
            if (dsNhanVien.get(i).getMaNV().equalsIgnoreCase(maNV)) {
                dsNhanVien.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<NhanVien> getDanhSach() { return dsNhanVien; }

    public void hienThiTatCaTomTat() {
        System.out.printf("%-10s %-25s %-20s %-10s%n", "MaNV", "Ho Ten", "Chuc Vu", "Loai");
        System.out.println("--------------------------------------------------------------------");
        for (NhanVien nv : dsNhanVien) {
            String loai = nv instanceof NhanVienChinhThuc ? "Fulltime" : "Parttime";
            System.out.printf("%-10s %-25s %-20s %-10s%n", nv.getMaNV(), nv.getHoTen(), nv.getChucVu(), loai);
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Tong so: " + NhanVien.getTongSoNhanVien());
    }

    public void hienThiTatCaChiTiet() {
        for (NhanVien nv : dsNhanVien) {
            nv.hienThiThongTinChiTiet();
            System.out.println("-------------------------------------------------");
        }
    }
}
