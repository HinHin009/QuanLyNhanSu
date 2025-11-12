import java.util.ArrayList;

public class QuanLyBangLuong {
    private ArrayList<BangLuong> dsBangLuong = new ArrayList<>();

    public void themBangLuong(BangLuong bl) {
        dsBangLuong.add(bl);
    }

    // ✅ Hiển thị bảng lương của 1 nhân viên bất kỳ theo mã nhân viên
    public void hienThiBangLuongTheoNhanVien(String maNV) {
        boolean found = false;
        for (BangLuong bl : dsBangLuong) {
            if (bl.getNhanVien().getMaNV().equalsIgnoreCase(maNV)) {
                bl.hienThiBangLuong();
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ Không tìm thấy bảng lương cho nhân viên có mã: " + maNV);
        }
    }
}
