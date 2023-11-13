import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HoaDon {

    public static void hoaDonSach(List<Sach> danhSachSach) {
        // List<Sach> danhSachSach = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten sach can mua: ");
        String tenSachMua = sc.nextLine();
        double tongBill = 0;

        boolean timThaySach = false;

        for (Sach sach : danhSachSach) {
            if (sach.getTenSach().equalsIgnoreCase(tenSachMua)) {
                timThaySach = true;

                System.out.print("Nhap so luong sach can mua: ");
                int soLuongMua = sc.nextInt();

                double giaSach = sach.getGiaBia();

                double tongTienSach = giaSach * soLuongMua;

                System.out.println("Hoa don cua ban:");
                System.out.println("+------------------------------+---------------+----------+---------------+");
                System.out.println("| Ten sach                     | So luong      | Gia tien | Tong tien     |");
                System.out.println("+------------------------------+---------------+----------+---------------+");
                System.out.printf("| %-17s | %-13d | %-8.1f | %-13.1f |\n", tenSachMua, soLuongMua, giaSach, tongTienSach);
                tongBill += tongTienSach;
                System.out.println("+------------------------------+---------------+----------+---------------+");
                System.out.printf("| %60s |\n", "So tien can thanh toan: " + tongBill+" VND");
                System.out.println("+--------------------------------------------------------------+");
            }
        }
    }

    public static void hoaDon(List<Sach> danhSachSach, List<HoaDonItem> hoaDonItems) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap ten sach can mua (Nhap 'exit' de ket thuc): ");
            String tenSachMua = sc.nextLine();

            if (tenSachMua.equalsIgnoreCase("exit")) {
                break;
            }

            boolean timThaySach = false;

            for (Sach sach : danhSachSach) {
                if (sach.getTenSach().equalsIgnoreCase(tenSachMua)) {
                    timThaySach = true;

                    System.out.print("Nhap so luong sach can mua: ");
                    int soLuongMua = sc.nextInt();

                    double giaSach = sach.getGiaBia();

                    double tongTienSach = giaSach * soLuongMua;

                    hoaDonItems.add(new HoaDonItem(tenSachMua, soLuongMua, giaSach, tongTienSach));

                    System.out.println("Hoa don cua ban:");
                    System.out.println("+-------------------+---------------+----------+---------------+");
                    System.out.println("| Ten sach          | So luong      | Gia tien | Tong tien     |");
                    System.out.println("+-------------------+---------------+----------+---------------+");
                    System.out.printf("| %-17s | %-13d | %-8.1f | %-13.1f |\n", tenSachMua, soLuongMua, giaSach, tongTienSach);
                    System.out.println("+-------------------+---------------+----------+---------------+");
                    System.out.printf("| %60s |\n", "So tien can thanh toan: " + tinhTongTien(hoaDonItems) + " VND");
                    System.out.println("+--------------------------------------------------------------+");
                    break;
                }
            }

            if (!timThaySach) {
                System.out.println("Khong tim thay sach ban can.");
            }
        }

      
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("hoadon.txt", true));
            for (HoaDonItem item : hoaDonItems) {
                bw.write(item.tenSach + "," + item.soLuongMua + "," + item.giaSach + "," + item.tongTien);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double tinhTongTien(List<HoaDonItem> hoaDonItems) {
        double tongTien = 0;
        for (HoaDonItem item : hoaDonItems) {
            tongTien += item.tongTien;
        }
        return tongTien;
    }
}
