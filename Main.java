import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QuanLyNhanSu qlns = new QuanLyNhanSu();
        Scanner sc = new Scanner(System.in);
        int chon = -1; // ✅ Khởi tạo giá trị mặc định để tránh lỗi

        do {
            System.out.println("\n===============================");
            System.out.println("===  MENU QUAN LY NHAN SU   ===");
            System.out.println("===============================");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Hien thi danh sach nhan vien (tom tat)");
            System.out.println("3. Them phong ban");
            System.out.println("4. Hien thi phong ban");
            System.out.println("5. Xuat bang luong");
            System.out.println("6. Xem chi tiet mot nhan vien");
            System.out.println("7. Tim kiem nhan vien theo ma");
            System.out.println("8. Xoa nhan vien");
            System.out.println("9. Them/Cap nhat gio cong cho nhan vien part-time");
            System.out.println("10. Xem va xuat danh sach nhan vien part-time va gio cong");
            System.out.println("0. Thoat");
            System.out.println("===============================");
            System.out.print("Chon chuc nang: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Lua chon khong hop le! Vui long nhap so.");
                continue;
            }

            switch (chon) {
                case 1 -> qlns.themNhanVien();
                case 2 -> qlns.hienThiDanhSach();
                case 3 -> qlns.themPhongBan();
                case 4 -> qlns.hienThiPhongBan();
                case 5 -> qlns.xuatBangLuong();
                case 6 -> qlns.xemChiTietNhanVien();
                case 7 -> qlns.timKiemNhanVien();
                case 8 -> qlns.xoaNhanVien();
                case 9 -> qlns.themGioCong();
                case 10 -> qlns.xemGioCongPartTime();
                case 0 -> System.out.println("👋 Tam biet!");
                default -> System.out.println("❌ Lua chon khong hop le!");
            }

        } while (chon != 0);

        sc.close();
    }
}
