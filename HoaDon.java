import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HoaDon {

    public static void hoaDon(List<HoaDonItem> hoaDonItems) {
        List<Sach> danhSachSach = docDanhSachSachTuFile("Sach.txt");
        Scanner sc = new Scanner(System.in);
        boolean checkBuy = false;
    
        while (true) {
            System.out.println("[1] Thanh toan: ");
            System.out.println("[0] Thoat: ");
            System.out.println("Nhap ten sach can mua: ");
    
            String tenSachMua = sc.nextLine();
            if (tenSachMua.equals("0"))
                break;
            if (tenSachMua.equals("1")) {
                if (checkBuy) {
                    thucHienThanhToan(hoaDonItems);
                } else {
                    System.out.println("Ban khong the thanh toan neu chua mua sach.");
                }
                break;
            }
    
            Sach sachDaChon = null;
    
            for (Sach sach : danhSachSach) {
                if (sach.getTenSach().equalsIgnoreCase(tenSachMua)) {
                    sachDaChon = sach;
                    checkBuy = true; // Đặt biến này thành true nếu khách hàng mua sách
                    break;
                }
            }

    
            if (sachDaChon != null) {
                // Kiểm tra số lượng sách còn đủ hay không
                while (true) {
                    System.out.print("Nhap so luong sach can mua: ");
                    if (sc.hasNextInt()) {
                        int soLuongMua = sc.nextInt();
                        if (soLuongMua > 0 && sachDaChon.getSoLuongSach() >= soLuongMua) {
                            double giaSach = sachDaChon.getGiaBia();
                            double tongTienSach = giaSach * soLuongMua;
                            sc.nextLine();
    
                            // Cập nhật số lượng sách còn lại
                            sachDaChon.setSoLuongSach(sachDaChon.getSoLuongSach() - soLuongMua);
    
                            Date ngayDatSach = new Date();
    
                            // Lấy danh sách tài khoản đã đăng nhập từ class DangNhap
                            List<TaiKhoan> danhSachTaiKhoanDaDangNhap = DangNhap.getDanhSachTaiKhoanDaDangNhap();
    
                            if (!danhSachTaiKhoanDaDangNhap.isEmpty()) {
                                TaiKhoan taiKhoanDaDangNhap = danhSachTaiKhoanDaDangNhap.get(0); // Lấy tài khoản đầu tiên (có thể điều chỉnh)
                                String maKhachHang = taiKhoanDaDangNhap.getUserName();
                                hoaDonItems.add(new HoaDonItem(maKhachHang, ngayDatSach, tenSachMua, soLuongMua, giaSach, tongTienSach));

                                hienThiTatCaHoaDon(hoaDonItems);
                            }
                            break; 

                        } else {
                            System.out.println("Xin loi so luong sach khong du!");
                        }
                    } else {
                        System.out.println("Xin loi, nhap so luong sach la mot so nguyen!");
                        sc.next(); // Consume the invalid input
                    }
                }
            } else {
                System.out.println("Khong tim thay sach trong he thong.");
            }
        }
        //======nho kt lai======
        // luuHoaDonVaoTep(hoaDonItems);
    }
    
    

    public static double tinhTongTien(List<HoaDonItem> hoaDonItems) {
        double tongTien = 0;
        for (HoaDonItem item : hoaDonItems) {
            tongTien += item.getTongTien();
        }
        return tongTien;
    }

    public static List<Sach> docDanhSachSachTuFile(String File) {
        List<Sach> danhSachSach = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(File));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    int maSach = Integer.parseInt(parts[0].trim());
                    String tenSach = parts[1].trim();
                    String tenLinhVuc = parts[2].trim();
                    String tenLoaiSach = parts[3].trim();
                    int giaBia = Integer.parseInt(parts[4].trim());
                    int taiBan = Integer.parseInt(parts[5].trim());
                    String tenNhaXuatBan = parts[6].trim();
                    int namXuatBan = Integer.parseInt(parts[7].trim());
                    int soLuongSach = Integer.parseInt(parts[8].trim());
                    Sach sach = new Sach(maSach, tenSach, tenLinhVuc, tenLoaiSach, giaBia, taiBan, tenNhaXuatBan, namXuatBan,soLuongSach);
                    danhSachSach.add(sach);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return danhSachSach;
    }

    public static void luuHoaDonVaoTep(List<HoaDonItem> hoaDonItems) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("hoadon.txt", true));
            // bw.newLine();
            for (HoaDonItem item : hoaDonItems) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String ngayDatSachStr = dateFormat.format(item.getNgayDatSach());
                bw.write(item.getMaKhachHang() + "," + ngayDatSachStr + "," + item.getTenSach() + "," + item.getSoLuongMua() + "," + item.getGiaSach() + "," + item.getTongTien());
                bw.newLine();                
            }
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void hienThiTatCaHoaDon(List<HoaDonItem> hoaDonItems) {
        if (hoaDonItems.isEmpty()) {
            System.out.println("Khong co hoa don!.");
            return;
        }
    
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        double tongTien = 0;
    
        System.out.println("hoa don tong cua khach hang la:");
        System.out.println("+------------------+------------------+-------------------+---------------+----------+---------------+");
        System.out.println("| Ma khach hang    | Ngay dat sach    | Ten sach          | So luong      | Gia tien | Tong tien     |");
        System.out.println("+------------------+------------------+-------------------+---------------+----------+---------------+");
    
        for (HoaDonItem item : hoaDonItems) {
            String ngayDatSachStr = date.format(item.getNgayDatSach());
            System.out.printf("| %-16s | %-16s | %-17s | %-13d | %-8.1f | %-13.1f |\n", item.getMaKhachHang(), ngayDatSachStr, item.getTenSach(), item.getSoLuongMua(), item.getGiaSach(), item.getTongTien());
            tongTien += item.getTongTien();
        }
    
        System.out.println("+------------------+------------------+-------------------+---------------+----------+---------------+");
        System.out.printf("| %98s |\n", "Tong so tien can thanh toan: " + tongTien + " VND");
        System.out.println("+----------------------------------------------------------------------------------------------------+");
    }


    public static void thucHienThanhToan(List<HoaDonItem> hoaDonItems) {

        // Hiển thị tổng hóa đơn
        hienThiTatCaHoaDon(hoaDonItems);
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Chon hinh thuc thanh toan:");
            System.out.println("[1] Thanh toan bang ngan hang");
            System.out.println("[2] Thanh toan bang Momo");
    
            choice = sc.nextInt();
            sc.nextLine();  // Consume the newline character
    
            switch (choice) {
                case 1:
                    thanhToanNganHang(hoaDonItems);
                    // After successful payment, update the quantity in the file
                    for (HoaDonItem item : hoaDonItems) {
                        capNhatSoLuongSachTrongFile(item.getTenSach(), item.getSoLuongMua());
                    }
                    break;
                case 2:
                    thanhToanMomo(hoaDonItems);
                    // After successful payment, update the quantity in the file
                    for (HoaDonItem item : hoaDonItems) {
                        capNhatSoLuongSachTrongFile(item.getTenSach(), item.getSoLuongMua());
                    }
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
                    continue;
            }
            break;
        }
    }
    

    public static void thanhToanNganHang(List<HoaDonItem> hoaDonItems) {
        Scanner sc = new Scanner(System.in);

        if (hoaDonItems.isEmpty()) {



            System.out.println("Khong co hoa don de thanh toan.");
            return;
        }
        hienThiTatCaHoaDon(hoaDonItems);

        // Chọn ngân hàng thanh toán
        String tenNganHang = "";
        int bankChoice = sc.nextInt();

        do {
        System.out.println("Chon ngan hang thanh toan:");
        System.out.println("[1] BIDV");
        System.out.println("[2] Agribank");
        System.out.println("[3] MB Bank");
        System.out.println("[4] Viettinbank");
        System.out.println("[5] Vietcombank");
        
        sc.nextLine();
        
            switch (bankChoice) {
                case 1:
                    tenNganHang = "BIDV";
                    break;
                case 2:
                    tenNganHang = "Agribank";
                    break;
                case 3:
                    tenNganHang = "MB Bank";
                    break;
                case 4:
                    tenNganHang = "Viettinbank";
                    break;
                case 5:
                    tenNganHang = "Vietcombank";
                    break;
                default:
                    System.out.println("Lua chon ngan hang khong hop le.");
                    continue;
            }
            break;
        }while(bankChoice<1 || bankChoice>5);

        String soTaiKhoan;

        do {
            System.out.print("Nhap so tai khoan (9 so): ");
            soTaiKhoan = sc.nextLine();
            if (soTaiKhoan.matches("\\d{9}") && !soTaiKhoan.matches(".*[a-zA-Z].*")) {
                break;
            } else {
                System.out.println("So tai khoan khong hop le. Vui long nhap lai.");
            }
        } while (true);

        double tongTienHoaDon = tinhTongTien(hoaDonItems);
        System.out.println("Tong tien hoa don: " + tongTienHoaDon + " VND");

        double money;
        do {
            System.out.print("Nhap so tien thanh toan: ");
            money = sc.nextDouble();
            sc.nextLine();

            // Kiểm tra số tiền thanh toán
            if (money != tongTienHoaDon) {
                System.out.println("So tien thanh toan khong dung. Vui long nhap lai.");
            }
        } while (money != tongTienHoaDon);

        System.out.println("Thanh toan thanh cong " + money + "vnd voi ngan hang " + tenNganHang + " tai khoan " + soTaiKhoan);

        luuHoaDonVaoTep(hoaDonItems);
        hoaDonItems.clear();
    }

    public static void thanhToanMomo(List<HoaDonItem> hoaDonItems) {
        Scanner sc = new Scanner(System.in);
        
        if (hoaDonItems.isEmpty()) {
            System.out.println("Khong co hoa don de thanh toan.");
            return;
        }
        
        hienThiTatCaHoaDon(hoaDonItems);
        boolean isValidPhoneNumber = false;
        
        do {
            System.out.print("Nhap so dien thoai Momo: ");
            String soDienThoai = sc.nextLine();
        
            // Kiểm tra số điện thoại Momo

        if (soDienThoai.matches("\\d{10}")) {

                double tongTienHoaDon = tinhTongTien(hoaDonItems);
                System.out.println("Tong tien hoa don: " + tongTienHoaDon + " VND");
                
                double money;
                do {
                    System.out.print("Nhap so tien thanh toan: ");
                    money = sc.nextDouble();
                    sc.nextLine();
        
                    // Kiểm tra số tiền thanh toán
                    if (money == tongTienHoaDon) {
                        System.out.println("Thanh toan thanh cong " + money + "đ qua Momo voi so dien thoai " + soDienThoai);
                        luuHoaDonVaoTep(hoaDonItems);
                        hoaDonItems.clear();
                        isValidPhoneNumber = true;
                    } else {
                        System.out.println("So tien thanh toan khong dung. Vui long nhap lai.");
                    }
                } while (money != tongTienHoaDon);
            } else {
                System.out.println("So dien thoai Momo khong hop le. Vui long nhap lai.");
            }
        } while (!isValidPhoneNumber);

    }

    public static void capNhatSoLuongSachTrongFile(String tenSach, int soLuongMua) {
        List<Sach> danhSachSach = docDanhSachSachTuFile("Sach.txt");
        for (Sach sach : danhSachSach) {
            if (sach.getTenSach().equalsIgnoreCase(tenSach)) {
                // Cập nhật số lượng sách còn lại
                sach.setSoLuongSach(sach.getSoLuongSach() - soLuongMua);
                break;
            }
        }
        luuDanhSachSachVaoFile(danhSachSach, "Sach.txt");
    }

    public static void luuDanhSachSachVaoFile(List<Sach> danhSachSach, String file) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Sach sach : danhSachSach) {
                String line = sach.getMaSach() + "," + sach.getTenSach() + "," + sach.getTenLinhVuc() + "," + sach.getTenLoaiSach() + ","
                        + (int) sach.getGiaBia() + "," + sach.getTaiBan() + "," + sach.getTenNhaXuatBan() + "," + sach.getNamXuatBan() + ","
                        + sach.getSoLuongSach();
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
