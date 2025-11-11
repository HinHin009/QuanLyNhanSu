import java.io.*;
import java.util.*;

public class QuanLyNhanSu {
    private ArrayList<NhanVien> danhSach = new ArrayList<>();
    private ArrayList<Phongban> dsPhongBan = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private final String FILE_NAME = "nhanvien.csv";

    public QuanLyNhanSu() {
        docTuFile();
    }

    // ================== 1. THEM NHAN VIEN ==================
    public void themNhanVien() {
        try {
            System.out.print("Chon hinh thuc (1. Fulltime | 2. Parttime): ");
            int loai = Integer.parseInt(sc.nextLine());

            System.out.print("Ma NV: ");
            String ma = sc.nextLine();

            // Kiem tra trung ma nhan vien
            for (NhanVien n : danhSach) {
                if (n.getMaNV().equalsIgnoreCase(ma)) {
                    System.out.println("❌ Ma nhan vien bi trung! Khong the them moi.");
                    return;
                }
            }

            System.out.print("Ho ten: ");
            String ten = sc.nextLine();
            System.out.print("Chuc vu: ");
            String cv = sc.nextLine();

            NhanVien nv;

            if (loai == 1) {
                System.out.print("Luong co ban: ");
                double lcb = Double.parseDouble(sc.nextLine().replace(".", "").replace(",", ""));
                System.out.print("Phu cap: ");
                double phuCap = Double.parseDouble(sc.nextLine().replace(".", "").replace(",", ""));
                nv = new NhanVienChinhThuc(ma, ten, cv, lcb, phuCap);
            } else {
                System.out.print("Tien cong/1 gio: ");
                double tc = Double.parseDouble(sc.nextLine().replace(".", "").replace(",", ""));
                nv = new NhanVienThoiVu(ma, ten, cv, 0, tc); // so gio lam = 0 luc moi tao
            }

            danhSach.add(nv);
            System.out.println("✅ Da them nhan vien thanh cong!");

            // Them vao phong ban neu co
            if (!dsPhongBan.isEmpty()) {
                System.out.print("Nhap ma phong ban de them vao (hoac bo qua): ");
                String maPB = sc.nextLine().trim();
                for (Phongban pb : dsPhongBan) {
                    if (pb.getMaPB().equalsIgnoreCase(maPB)) {
                        pb.themNhanVien(nv);
                        System.out.println("👔 Da them vao phong: " + pb.getTenPB());
                        break;
                    }
                }
            }

            ghiVaoFile();
        } catch (Exception e) {
            System.out.println("⚠️ Loi nhap lieu: " + e.getMessage());
        }
    }

    // ================== 2. HIEN THI DANH SACH (TOM TAT) ==================
    public void hienThiDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sach trong!");
            return;
        }

        System.out.println("===== DANH SACH NHAN VIEN =====");
        System.out.printf("%-10s %-25s %-20s %-15s%n", "Ma NV", "Ho Ten", "Chuc Vu", "Loai");
        System.out.println("-----------------------------------------------------------------------");

        for (NhanVien nv : danhSach) {
            String loai = (nv instanceof NhanVienChinhThuc) ? "Full-time" : "Part-time";
            System.out.printf("%-10s %-25s %-20s %-15s%n",
                    nv.getMaNV(), nv.hoTen, nv.chucVu, loai);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Tong so nhan vien: " + NhanVien.getTongSoNhanVien());
    }

    // ================== 3. XEM CHI TIET NHAN VIEN ==================
    public void xemChiTietNhanVien() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sach trong!");
            return;
        }

        System.out.print("Nhap ma nhan vien can xem chi tiet: ");
        String ma = sc.nextLine();

        for (NhanVien nv : danhSach) {
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                System.out.println("\n===== THONG TIN CHI TIET NHAN VIEN =====");
                nv.hienThiThongTin();
                System.out.println("-----------------------------------------");
                return;
            }
        }
        System.out.println("❌ Khong tim thay nhan vien co ma: " + ma);
    }

    // ================== 4. TIM KIEM NHAN VIEN ==================
    public void timKiemNhanVien() {
        System.out.print("Nhap ma nhan vien can tim: ");
        String ma = sc.nextLine();

        for (NhanVien nv : danhSach) {
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                System.out.println("✅ Tim thay nhan vien:");
                System.out.printf("%-10s %-25s %-20s%n", nv.getMaNV(), nv.hoTen, nv.chucVu);
                return;
            }
        }
        System.out.println("❌ Khong tim thay nhan vien co ma: " + ma);
    }

    // ================== 5. XOA NHAN VIEN ==================
    public void xoaNhanVien() {
        System.out.print("Nhap ma nhan vien can xoa: ");
        String ma = sc.nextLine();

        Iterator<NhanVien> iterator = danhSach.iterator();
        while (iterator.hasNext()) {
            NhanVien nv = iterator.next();
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                iterator.remove();
                System.out.println("🗑️ Da xoa nhan vien co ma: " + ma);
                ghiVaoFile();
                return;
            }
        }

        System.out.println("❌ Khong tim thay nhan vien de xoa!");
    }

    // ================== 6. THEM / CAP NHAT GIO CONG ==================
    public void themGioCong() {
        System.out.print("Nhap ma nhan vien (de them/ cap nhat gio cong): ");
        String ma = sc.nextLine();

        for (NhanVien nv : danhSach) {
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                if (nv instanceof NhanVienThoiVu nvtv) {
                    System.out.println("So gio hien tai: " + nvtv.getSoGioLam());
                    System.out.println("Ban muon:");
                    System.out.println("1. Cong them gio cong");
                    System.out.println("2. Thiet lap lai so gio moi");
                    System.out.print("Chon (1 hoac 2): ");

                    int chon;
                    try {
                        chon = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Lua chon khong hop le!");
                        return;
                    }

                    if (chon == 1) {
                        System.out.print("Nhap so gio muon CONG THEM: ");
                        int add = Integer.parseInt(sc.nextLine());
                        nvtv.setSoGioLam(nvtv.getSoGioLam() + add);
                        System.out.println("✅ Da cong them gio cong. Tong gio moi: " + nvtv.getSoGioLam());
                    } else if (chon == 2) {
                        System.out.print("Nhap so gio moi: ");
                        int newGio = Integer.parseInt(sc.nextLine());
                        nvtv.setSoGioLam(newGio);
                        System.out.println("✅ Da thiet lap lai so gio moi: " + nvtv.getSoGioLam());
                    } else {
                        System.out.println("Lua chon khong hop le.");
                        return;
                    }

                    ghiVaoFile();
                    return;
                } else {
                    System.out.println("Nhan vien khong phai part-time. Khong the cap nhat gio cong.");
                    return;
                }
            }
        }
        System.out.println("❌ Khong tim thay nhan vien co ma: " + ma);
    }

    // ================== 7. XEM & XUAT DANH SACH GIO CONG CUA NV PART-TIME ==================
    public void xemGioCongPartTime() {
        boolean coNV = false;
        System.out.println("\n===== DANH SACH NHAN VIEN PART-TIME VA GIO CONG =====");
        System.out.printf("%-10s %-25s %-20s %-15s %-15s%n", "Ma NV", "Ho Ten", "Chuc Vu", "So Gio Lam", "Tien Cong");
        System.out.println("-------------------------------------------------------------------------------");

        StringBuilder sb = new StringBuilder();
        sb.append("===== DANH SACH NHAN VIEN PART-TIME VA GIO CONG =====\n");
        sb.append(String.format("%-10s %-25s %-20s %-15s %-15s%n", "Ma NV", "Ho Ten", "Chuc Vu", "So Gio Lam", "Tien Cong"));
        sb.append("-------------------------------------------------------------------------------\n");

        for (NhanVien nv : danhSach) {
            if (nv instanceof NhanVienThoiVu nvtv) {
                System.out.printf("%-10s %-25s %-20s %-15d %,15.0f%n",
                        nvtv.getMaNV(), nvtv.hoTen, nvtv.chucVu, nvtv.getSoGioLam(), nvtv.getTienCong());
                sb.append(String.format("%-10s %-25s %-20s %-15d %,15.0f%n",
                        nvtv.getMaNV(), nvtv.hoTen, nvtv.chucVu, nvtv.getSoGioLam(), nvtv.getTienCong()));
                coNV = true;
            }
        }

        if (!coNV) {
            System.out.println("Khong co nhan vien part-time nao trong danh sach!");
            sb.append("Khong co nhan vien part-time nao trong danh sach!\n");
        }

        System.out.println("-------------------------------------------------------------------------------");

        // Xuat ra file text
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("gioCongPartTime.txt"))) {
            bw.write(sb.toString());
            System.out.println("📄 Da xuat danh sach part-time vao file: gioCongPartTime.txt");
        } catch (IOException e) {
            System.out.println("❌ Loi khi ghi file gioCongPartTime.txt: " + e.getMessage());
        }
    }

    // ================== 8. THEM PHONG BAN ==================
    public void themPhongBan() {
        System.out.print("Ma phong ban: ");
        String maPB = sc.nextLine();
        System.out.print("Ten phong ban: ");
        String tenPB = sc.nextLine();
        System.out.print("Truong phong: ");
        String truong = sc.nextLine();
        dsPhongBan.add(new Phongban(maPB, tenPB, truong));
        System.out.println("✅ Da them phong ban!");
    }

    // ================== 9. HIEN THI PHONG BAN ==================
    public void hienThiPhongBan() {
        if (dsPhongBan.isEmpty()) {
            System.out.println("Chua co phong ban nao!");
            return;
        }
        for (Phongban pb : dsPhongBan) {
            System.out.println("\nPhong: " + pb.getTenPB() + " - Truong phong: " + pb.getTruongPhong());
            pb.hienThiNhanVien();
            System.out.printf("-> Tong luong phong: %,12.0f VND%n", pb.tinhTongLuong());
        }
    }

    // ================== 10. XUAT BANG LUONG ==================
    public void xuatBangLuong() {
        System.out.print("Nhap thang: ");
        int thang = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap nam: ");
        int nam = Integer.parseInt(sc.nextLine());

        Bangluong bl = new Bangluong(thang, nam);
        for (NhanVien nv : danhSach) {
            bl.themNhanVien(nv);
        }
        bl.hienThiBangLuong();
    }

    // ================== 11. GHI FILE ==================
    private void ghiVaoFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (NhanVien nv : danhSach) {
                if (nv instanceof NhanVienChinhThuc nvct) {
                    bw.write(String.join(",", "Fulltime", nvct.getMaNV(), nvct.hoTen, nvct.chucVu,
                            String.valueOf(nvct.luongCoBan), String.valueOf(nvct.getPhuCap())));
                } else if (nv instanceof NhanVienThoiVu nvtv) {
                    bw.write(String.join(",", "Parttime", nvtv.getMaNV(), nvtv.hoTen, nvtv.chucVu,
                            String.valueOf(nvtv.getSoGioLam()), String.valueOf(nvtv.getTienCong())));
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Loi ghi file: " + e.getMessage());
        }
    }

    // ================== 12. DOC FILE ==================
    private void docTuFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p[0].equals("Fulltime")) {
                    danhSach.add(new NhanVienChinhThuc(p[1], p[2], p[3],
                            Double.parseDouble(p[4]), Double.parseDouble(p[5])));
                } else if (p[0].equals("Parttime")) {
                    danhSach.add(new NhanVienThoiVu(p[1], p[2], p[3],
                            Integer.parseInt(p[4]), Double.parseDouble(p[5])));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Loi doc file: " + e.getMessage());
        }
    }
}
