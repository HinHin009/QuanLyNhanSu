import java.util.ArrayList;

public class DanhSachBangLuong {
    private ArrayList<BangLuong> dsBangLuong = new ArrayList<>();

    public DanhSachBangLuong() {}

    public void themBangLuong(BangLuong bl) {
        if (bl == null) return;
        dsBangLuong.add(bl);
    }

    public BangLuong timBangLuong(String maLuong) {
        for (BangLuong bl : dsBangLuong) {
            if (bl.getMaLuong().equalsIgnoreCase(maLuong)) return bl;
        }
        return null;
    }

    public ArrayList<BangLuong> getDanhSach() { return dsBangLuong; }

    public void hienThiTatCa() {
        for (BangLuong bl : dsBangLuong) {
            bl.hienThiBangLuong();
            System.out.println("-------------------------------------------------");
        }
    }
}
