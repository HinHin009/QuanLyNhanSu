import java.util.ArrayList;

public class DanhSachPhongBan {
    private ArrayList<PhongBan> dsPhongBan = new ArrayList<>();

    public DanhSachPhongBan() {}

    public void themPhongBan(PhongBan pb) {
        if (pb == null) return;
        if (timPhongBan(pb.getMaPB()) != null) {
            System.out.println("Ma phong ban da ton tai: " + pb.getMaPB());
            return;
        }
        dsPhongBan.add(pb);
    }

    public PhongBan timPhongBan(String maPB) {
        for (PhongBan pb : dsPhongBan) {
            if (pb.getMaPB().equalsIgnoreCase(maPB)) return pb;
        }
        return null;
    }

    public ArrayList<PhongBan> getDanhSach() { return dsPhongBan; }

    public void hienThiTatCa() {
        for (PhongBan pb : dsPhongBan) {
            System.out.println("MaPB: " + pb.getMaPB() + " | Ten: " + pb.getTenPB() + " | Truong: " + pb.getTruongPhong());
        }
    }
}
