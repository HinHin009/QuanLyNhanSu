package quanlynhansu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QuanLyNhanSu qlns = new QuanLyNhanSu();
        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("\n===== MENU QUAN LY NHAN SU =====");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Hien thi danh sach");
            System.out.println("3. Tim kiem nhan vien");
            System.out.println("4. Xoa nhan vien");
            System.out.println("5. Cap nhat thong tin nhan vien");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            chon = sc.nextInt();
            sc.nextLine(); // xóa bộ đệm

            switch (chon) {
                case 1 -> qlns.themNhanVien();
                case 2 -> qlns.hienThiDanhSach();
                case 3 -> qlns.timKiemNhanVien();
                case 4 -> qlns.xoaNhanVien();
                case 5 -> qlns.capNhatNhanVien();
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);

        sc.close();
    }
}
