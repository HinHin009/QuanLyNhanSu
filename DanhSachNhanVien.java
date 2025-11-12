import java.util.ArrayList;
import java.util.Scanner;

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

    public void suaThongTinNhanVien(String maNV) {
    NhanVien nv = timNhanVien(maNV);
    if (nv == null) {
        System.out.println("Không tìm thấy nhân viên có mã: " + maNV);
        return;
    }

    Scanner sc = new Scanner(System.in);
    System.out.println("=== Sửa thông tin nhân viên ===");

    System.out.print("Mã nhân viên mới (" + nv.getMaNV() + "): ");
    String maMoi = sc.nextLine().trim();
    if (!maMoi.isEmpty()) nv.setMaNV(maMoi);

    System.out.print("Tên nhân viên mới (" + nv.getHoTen() + "): ");
    String tenMoi = sc.nextLine().trim();
    if (!tenMoi.isEmpty()) nv.setHoTen(tenMoi);

    System.out.print("Phòng ban mới (" + nv.getPhongBan() + "): ");
    String phongBanMoi = sc.nextLine().trim();
    if (!phongBanMoi.isEmpty()) nv.setPhongBan(phongBanMoi);

    System.out.println("✅ Cập nhật thông tin nhân viên thành công!");
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
