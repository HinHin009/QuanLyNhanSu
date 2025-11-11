package quanlynhansu;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyNhanSu {
    private ArrayList<NhanVien> danhSach = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private final String FILE_NAME = "nhanvien.csv";

    public QuanLyNhanSu() {
        docTuFile(); // 🔹 Tự động load khi khởi tạo
    }

    // ===== Thêm nhân viên =====
    public void themNhanVien() {
        try {
            System.out.print("Chon hinh thuc (1. Fulltime | 2. Partime): ");
            int loai = Integer.parseInt(sc.nextLine());

            System.out.print("Ma NV: ");
            String ma = sc.nextLine();
            System.out.print("Ho ten: ");
            String ten = sc.nextLine();
            System.out.print("Chuc vu: ");
            String cv = sc.nextLine();

            if (loai == 1) {
                System.out.print("Luong co ban: ");
                double lcb = Double.parseDouble(sc.nextLine().replace(".", ""));
                System.out.print("Phu cap: ");
                double phuCap = Double.parseDouble(sc.nextLine().replace(".", ""));
                danhSach.add(new NhanVienChinhThuc(ma, ten, cv, lcb, phuCap));
            } else {
                System.out.print("So gio lam: ");
                int gio = Integer.parseInt(sc.nextLine());
                System.out.print("Tien cong/1 gio: ");
                double tc = Double.parseDouble(sc.nextLine().replace(".", ""));
                danhSach.add(new NhanVienThoiVu(ma, ten, cv, gio, tc));
            }
            System.out.println("✅ Da them nhan vien thanh cong!");
            ghiVaoFile();
        } catch (Exception e) {
            System.out.println("⚠️ Du lieu nhap khong hop le. Vui long thu lai!");
        }
    }

    // ===== Hiển thị danh sách =====
    public void hienThiDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sach trong!");
            return;
        }
        System.out.println("===== DANH SACH NHAN VIEN =====");
        for (NhanVien nv : danhSach) {
            nv.hienThiThongTin();
            System.out.println("-----------------------------");
        }
    }

    // ===== Tìm kiếm nhân viên =====
    public void timKiemNhanVien() {
        System.out.print("Nhap ma nhan vien can tim: ");
        String ma = sc.nextLine();
        for (NhanVien nv : danhSach) {
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                nv.hienThiThongTin();
                return;
            }
        }
        System.out.println("Khong tim thay nhan vien.");
    }

    // ===== Xóa nhân viên =====
    public void xoaNhanVien() {
        System.out.print("Nhap ma nhan vien can xoa: ");
        String ma = sc.nextLine();
        if (danhSach.removeIf(nv -> nv.getMaNV().equalsIgnoreCase(ma))) {
            System.out.println("✅ Da xoa nhan vien.");
            ghiVaoFile();
        } else {
            System.out.println("Khong tim thay nhan vien de xoa.");
        }
    }

    // ===== Cập nhật thông tin =====
    public void capNhatNhanVien() {
        System.out.print("Nhap ma nhan vien can cap nhat: ");
        String ma = sc.nextLine();
        for (NhanVien nv : danhSach) {
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                System.out.print("Nhap ten moi: ");
                nv.hoTen = sc.nextLine();
                System.out.print("Nhap chuc vu moi: ");
                nv.chucVu = sc.nextLine();

                if (nv instanceof NhanVienChinhThuc nvct) {
                    System.out.print("Nhap luong co ban moi: ");
                    nvct.luongCoBan = Double.parseDouble(sc.nextLine().replace(".", ""));
                    System.out.print("Nhap phu cap moi: ");
                    nvct.setPhuCap(Double.parseDouble(sc.nextLine().replace(".", "")));
                } else if (nv instanceof NhanVienThoiVu nvtv) {
                    System.out.print("Nhap so gio lam moi: ");
                    nvtv.setSoGioLam(Integer.parseInt(sc.nextLine()));
                    System.out.print("Nhap tien cong moi: ");
                    nvtv.setTienCong(Double.parseDouble(sc.nextLine().replace(".", "")));
                }

                System.out.println("✅ Da cap nhat thong tin nhan vien!");
                ghiVaoFile();
                return;
            }
        }
        System.out.println("Khong tim thay nhan vien can cap nhat.");
    }

    // ===== Ghi file =====
    private void ghiVaoFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (NhanVien nv : danhSach) {
                if (nv instanceof NhanVienChinhThuc nvct) {
                    bw.write(String.join(",",
                            "Fulltime",
                            nvct.maNV,
                            nvct.hoTen,
                            nvct.chucVu,
                            String.valueOf(nvct.luongCoBan),
                            String.valueOf(nvct.getPhuCap())));
                } else if (nv instanceof NhanVienThoiVu nvtv) {
                    bw.write(String.join(",",
                            "Parttime",
                            nvtv.maNV,
                            nvtv.hoTen,
                            nvtv.chucVu,
                            String.valueOf(nvtv.getSoGioLam()),
                            String.valueOf(nvtv.getTienCong())));
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Loi khi ghi file: " + e.getMessage());
        }
    }

    // ===== Đọc file =====
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
            System.out.println("❌ Loi khi doc file: " + e.getMessage());
        }
    }
}
