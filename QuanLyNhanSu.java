    import java.io.*;
    import java.util.Scanner;

    public class QuanLyNhanSu {
        private DanhSachNhanVien dsNhanVien = new DanhSachNhanVien();
        private DanhSachPhongBan dsPhongBan = new DanhSachPhongBan();
        private DanhSachBangLuong dsBangLuong = new DanhSachBangLuong();

        private final String FILE_NV = "nhanvien.csv";
        private final String FILE_PB = "phongban.csv";
        private final String FILE_BL = "bangluong.csv";

        private Scanner sc = new Scanner(System.in);

        public QuanLyNhanSu() {
            docTuFile();
        }

        public void menu() {
            int chon = -1;
            do {
                System.out.println("\n===== MENU QUAN LY NHAN SU =====");
                System.out.println("1. Them nhan vien");
                System.out.println("2. Hien thi danh sach (tom tat)");
                System.out.println("3. Hien thi danh sach (chi tiet)");
                System.out.println("4. Them phong ban");
                System.out.println("5. Hien thi phong ban");
                System.out.println("6. Tao bang luong cho 1 nhan vien");
                System.out.println("7. Hien thi tat ca bang luong");
                System.out.println("8. Cap nhat gio cong cho part-time");
                System.out.println("9. Xem danh sach part-time va gio cong");
                System.out.println("10. Xoa nhan vien");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");

                try {
                    chon = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Nhap sai, vui long nhap lai!");
                    continue;
                }

                switch (chon) {
                    case 1 -> themNhanVien();
                    case 2 -> dsNhanVien.hienThiTatCaTomTat();
                    case 3 -> dsNhanVien.hienThiTatCaChiTiet();
                    case 4 -> themPhongBan();
                    case 5 -> hienThiPhongBanVaNhanVien();
                    case 6 -> taoBangLuong();
                    case 7 -> dsBangLuong.hienThiTatCa();
                    case 8 -> capNhatGioCong();
                    case 9 -> xuatGioCongPartTime();
                    case 10 -> xoaNhanVien();
                    case 0 -> {
                        System.out.println("Luu du lieu va thoat chuong trinh...");
                        ghiVaoFile();
                    }
                    default -> System.out.println("Lua chon khong hop le!");
                }
            } while (chon != 0);
        }

        // ===== THEM NHAN VIEN =====
        private void themNhanVien() {
            try {
                System.out.print("Ma NV: ");
                String ma = sc.nextLine().trim();
                if (dsNhanVien.timNhanVien(ma) != null) {
                    System.out.println("⚠️ Ma NV da ton tai!");
                    return;
                }

                System.out.print("Ho ten: ");
                String ten = sc.nextLine();
                System.out.print("Chuc vu: ");
                String cv = sc.nextLine();
                System.out.print("Loai (1-Fulltime, 2-Parttime): ");
                int loai = Integer.parseInt(sc.nextLine());

                if (loai == 1) {
                    System.out.print("Luong co ban: ");
                    double lcb = Double.parseDouble(sc.nextLine().replace(",", "").replace(".", ""));
                    System.out.print("Phu cap: ");
                    double pc = Double.parseDouble(sc.nextLine().replace(",", "").replace(".", ""));
                    NhanVien nv = new NhanVienChinhThuc(ma, ten, cv, lcb, pc);
                    dsNhanVien.themNhanVien(nv);
                    System.out.println("✅ Da them nhan vien Full-time.");
                } else {
                    System.out.print("Tien cong/1 gio: ");
                    double tc = Double.parseDouble(sc.nextLine().replace(",", "").replace(".", ""));
                    NhanVien nv = new NhanVienThoiVu(ma, ten, cv, 0, tc);
                    dsNhanVien.themNhanVien(nv);
                    System.out.println("✅ Da them nhan vien Part-time (so gio lam = 0).");
                }

                System.out.print("Ban muon them vao phong nao? (Nhap maPB hoac bo qua): ");
                String mapb = sc.nextLine().trim();
                if (!mapb.isEmpty() && !mapb.equalsIgnoreCase("bo qua")) {
                    PhongBan pb = dsPhongBan.timPhongBan(mapb);
                    if (pb != null) {
                        NhanVien nv = dsNhanVien.timNhanVien(ma);
                        pb.themNhanVien(nv);
                        System.out.println("✅ Da them vao phong: " + pb.getTenPB());
                    } else {
                        System.out.println("⚠️ Khong tim thay phong ban co ma: " + mapb);
                    }
                } else {
                    System.out.println("Bo qua them phong ban.");
                }

                ghiVaoFile();
            } catch (Exception e) {
                System.out.println("Loi nhap lieu: " + e.getMessage());
            }
        }

        // ===== THEM PHONG BAN =====
        private void themPhongBan() {
            System.out.print("Ma PB: ");
            String ma = sc.nextLine().trim();
            if (dsPhongBan.timPhongBan(ma) != null) {
                System.out.println("⚠️ Ma PB da ton tai!");
                return;
            }
            System.out.print("Ten PB: ");
            String ten = sc.nextLine();
            System.out.print("Truong phong: ");
            String truong = sc.nextLine();
            dsPhongBan.themPhongBan(new PhongBan(ma, ten, truong));
            System.out.println("✅ Da them phong ban moi.");
            ghiVaoFile();
        }

        // ===== HIEN THI PHONG BAN & NHAN VIEN =====
        private void hienThiPhongBanVaNhanVien() {
            for (PhongBan pb : dsPhongBan.getDanhSach()) {
                pb.hienThiNhanVien();
                System.out.printf("-> Tong luong phong: %,.0f VND%n", pb.tinhTongLuong());
                System.out.println("-------------------------------------------------");
            }
        }

        // ===== TAO BANG LUONG =====
        private void taoBangLuong() {
            System.out.print("Nhap ma NV: ");
            String ma = sc.nextLine().trim();
            NhanVien nv = dsNhanVien.timNhanVien(ma);
            if (nv == null) {
                System.out.println("⚠️ Khong tim thay nhan vien!");
                return;
            }

            try {
                System.out.print("Ngay: ");
                int ngay = Integer.parseInt(sc.nextLine());
                System.out.print("Thang: ");
                int thang = Integer.parseInt(sc.nextLine());
                System.out.print("Nam: ");
                int nam = Integer.parseInt(sc.nextLine());

                String maBL = "BL" + ma + "_" + thang + "_" + nam;
                BangLuong bl = new BangLuong(maBL, ngay, thang, nam, nv);

                if (nv instanceof NhanVienChinhThuc) {
                    System.out.print("Nhap so ngay lam trong thang: ");
                    int ngayLam = Integer.parseInt(sc.nextLine());
                    bl.setSoNgayLam(ngayLam);
                }

                dsBangLuong.themBangLuong(bl);
                System.out.println("✅ Da tao bang luong cho nhan vien.");
                bl.hienThiBangLuong();
                ghiVaoFile();
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }

        // ===== CAP NHAT GIO CONG =====
        private void capNhatGioCong() {
            System.out.print("Nhap ma NV part-time: ");
            String ma = sc.nextLine().trim();
            NhanVien nv = dsNhanVien.timNhanVien(ma);
            if (nv == null) { System.out.println("⚠️ Khong tim thay nhan vien."); return; }
            if (!(nv instanceof NhanVienThoiVu)) { System.out.println("⚠️ Nhan vien nay khong phai part-time."); return; }

            NhanVienThoiVu p = (NhanVienThoiVu) nv;
            System.out.println("So gio hien tai: " + p.getSoGioLam());
            System.out.println("1. Cong them");
            System.out.println("2. Thiet lap moi");
            System.out.print("Chon: ");
            int k = Integer.parseInt(sc.nextLine());
            if (k == 1) {
                System.out.print("Nhap so gio muon cong: ");
                int add = Integer.parseInt(sc.nextLine());
                p.setSoGioLam(p.getSoGioLam() + add);
            } else {
                System.out.print("Nhap so gio moi: ");
                int newGio = Integer.parseInt(sc.nextLine());
                p.setSoGioLam(newGio);
            }
            System.out.println("✅ Da cap nhat gio cong thanh cong.");
            ghiVaoFile();
        }

        // ===== XUAT GIO CONG PART-TIME =====
        private void xuatGioCongPartTime() {
            System.out.println("===== DANH SACH NHAN VIEN PART-TIME =====");
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-10s %-25s %-20s %-10s %-15s%n", "MaNV", "HoTen", "ChucVu", "SoGio", "TienCong"));
            for (NhanVien nv : dsNhanVien.getDanhSach()) {
                if (nv instanceof NhanVienThoiVu p) {
                    System.out.printf("%-10s %-25s %-20s %-10d %,15.0f%n",
                            p.getMaNV(), p.getHoTen(), p.getChucVu(), p.getSoGioLam(), p.getTienCong());
                    sb.append(String.format("%-10s %-25s %-20s %-10d %,15.0f%n",
                            p.getMaNV(), p.getHoTen(), p.getChucVu(), p.getSoGioLam(), p.getTienCong()));
                }
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("gioCongPartTime.txt"))) {
                bw.write(sb.toString());
                System.out.println("✅ Da xuat file gioCongPartTime.txt");
            } catch (IOException e) {
                System.out.println("❌ Loi ghi file: " + e.getMessage());
            }
        }

        // ===== XOA NHAN VIEN =====
        private void xoaNhanVien() {
            System.out.print("Nhap ma NV can xoa: ");
            String ma = sc.nextLine().trim();
            boolean ok = dsNhanVien.xoaNhanVien(ma);
            if (ok) {
                for (PhongBan pb : dsPhongBan.getDanhSach()) pb.xoaNhanVien(ma);
                System.out.println("✅ Da xoa nhan vien thanh cong.");
                ghiVaoFile();
            } else System.out.println("⚠️ Khong tim thay nhan vien.");
        }

        // ===== DOC / GHI FILE =====
        private void ghiVaoFile() {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NV))) {
                for (NhanVien nv : dsNhanVien.getDanhSach()) {
                    if (nv instanceof NhanVienChinhThuc a)
                        bw.write(String.join(",", "Fulltime", a.getMaNV(), a.getHoTen(), a.getChucVu(),
                                String.valueOf(a.getLuongCoBan()), String.valueOf(a.getPhuCap())));
                    else if (nv instanceof NhanVienThoiVu p)
                        bw.write(String.join(",", "Parttime", p.getMaNV(), p.getHoTen(), p.getChucVu(),
                                String.valueOf(p.getSoGioLam()), String.valueOf(p.getTienCong())));
                    bw.newLine();
                }
            } catch (IOException e) { System.out.println("❌ Loi ghi file nhanvien: " + e.getMessage()); }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PB))) {
                for (PhongBan pb : dsPhongBan.getDanhSach()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(pb.getMaPB()).append(",").append(pb.getTenPB()).append(",").append(pb.getTruongPhong()).append(",");
                    boolean first = true;
                    for (NhanVien nv : pb.getDanhSach()) {
                        if (!first) sb.append(";");
                        sb.append(nv.getMaNV());
                        first = false;
                    }
                    bw.write(sb.toString()); bw.newLine();
                }
            } catch (IOException e) { System.out.println("❌ Loi ghi file phongban: " + e.getMessage()); }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_BL))) {
                for (BangLuong bl : dsBangLuong.getDanhSach()) {
                    bw.write(String.join(",", bl.getMaLuong(), bl.getNhanVien().getMaNV(),
                            String.valueOf(bl.getNgay()), String.valueOf(bl.getThang()), String.valueOf(bl.getNam())));
                    bw.newLine();
                }
            } catch (IOException e) { System.out.println("❌ Loi ghi file bangluong: " + e.getMessage()); }
        }

        private void docTuFile() {
            // Đọc nhân viên
            File f = new File(FILE_NV);
            if (f.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.trim().isEmpty()) continue;
                        String[] p = line.split(",");
                        if (p[0].equalsIgnoreCase("Fulltime") && p.length >= 6)
                            dsNhanVien.themNhanVien(new NhanVienChinhThuc(p[1], p[2], p[3],
                                    Double.parseDouble(p[4]), Double.parseDouble(p[5])));
                        else if (p[0].equalsIgnoreCase("Parttime") && p.length >= 6)
                            dsNhanVien.themNhanVien(new NhanVienThoiVu(p[1], p[2], p[3],
                                    Integer.parseInt(p[4]), Double.parseDouble(p[5])));
                    }
                } catch (IOException e) { System.out.println("❌ Loi doc file nhanvien: " + e.getMessage()); }
            }

            // Đọc phòng ban
            File f2 = new File(FILE_PB);
            if (f2.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(f2))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.trim().isEmpty()) continue;
                        String[] p = line.split(",", 4);
                        PhongBan pb = new PhongBan(p[0], p[1], p[2]);
                        if (p.length == 4 && !p[3].trim().isEmpty())
                            for (String maNV : p[3].split(";")) {
                                NhanVien nv = dsNhanVien.timNhanVien(maNV);
                                if (nv != null) pb.themNhanVien(nv);
                            }
                        dsPhongBan.themPhongBan(pb);
                    }
                } catch (IOException e) { System.out.println("❌ Loi doc file phongban: " + e.getMessage()); }
            }

            // Đọc bảng lương
            File f3 = new File(FILE_BL);
            if (f3.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(f3))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.trim().isEmpty()) continue;
                        String[] p = line.split(",");
                        NhanVien nv = dsNhanVien.timNhanVien(p[1]);
                        if (nv != null)
                            dsBangLuong.themBangLuong(new BangLuong(p[0],
                                    Integer.parseInt(p[2]), Integer.parseInt(p[3]),
                                    Integer.parseInt(p[4]), nv));
                    }
                } catch (IOException e) { System.out.println("❌ Loi doc file bangluong: " + e.getMessage()); }
            }
        }
    }
