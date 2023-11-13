import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DanhSachTK ds = new DanhSachTK();
        List<Sach> danhSachSach = new ArrayList<>();
        List<HoaDonItem> hoaDonItems = new ArrayList<>();
        ds.themTaiKhoan("helo1", "121", "admin");
        ds.themTaiKhoan("helo2", "122", "user");
        ds.themTaiKhoan("helo3", "123", "nhanvien");
        DangNhap dangNhap = new DangNhap();
        QuyenSach QS = new QuyenSach();
        // Thực hiện đăng nhập
        dangNhap.DangNhaptaikhoan();
        // Kiểm tra đăng nhập và quyền admin
        if (QS.isAdmin()) {
            QS.nhapThongTinSachMoi();
        }

        danhSachSach.add(new Sach(1, "Conan","Linh vuc A", "Trinh tham", 10000, 2, "Nha xuat ban A", 2023));
        danhSachSach.add(new Sach(2, "Doraemon","Linh vuc B", "Vui ve", 15000, 3, "Nha xuat ban B", 2022));
        danhSachSach.add(new Sach(3, "One Piece","Linh vuc C", "Gia tuong", 20000, 4, "Nha xuat ban C", 2021));
        danhSachSach.add(new Sach(4,"Sharelock Homes tap 1","Linh vuc D","Trinh tham",25000,5,"Nha xuat ban Kim Dong",2022));        
        danhSachSach.add(new Sach(4,"Sharelock Homes tap 2","Linh vuc D","Trinh tham",30000,5,"Nha xuat ban Kim Dong",2022));
        danhSachSach.add(new Sach(4,"Sharelock Homes tap 3","Linh vuc D","Trinh tham",30000,5,"Nha xuat ban Kim Dong",2022));
        danhSachSach.add(new Sach(4,"Sharelock Homes tap 4","Linh vuc D","Trinh tham",30000,5,"Nha xuat ban Kim Dong",2022));
        danhSachSach.add(new Sach(5,"Sharelock Homes ngoai truyen","Linh vuc D","Trinh tham",30000,5,"Nha xuat ban Kim Dong",2022));
        danhSachSach.add(new Sach(1, "Conan","Linh vuc A", "Trinh tham", 100, 2, "Nha xuat ban A", 2023));
        danhSachSach.add(new Sach(2, "Doraemon","Linh vuc B", "Vui ve", 150, 3, "Nha xuat ban B", 2022));
        danhSachSach.add(new Sach(3, "One Piece","Linh vuc C", "Gia tuong", 200, 4, "Nha xuat ban C", 2021));
        danhSachSach.add(new Sach(4,"Sharelock Homes tap 1","Linh vuc D","Trinh tham",300,5,"Nha xuat ban Kim Dong",2022));        
        danhSachSach.add(new Sach(4,"Sharelock Homes tap 2","Linh vuc D","Trinh tham",300,5,"Nha xuat ban Kim Dong",2022));
        danhSachSach.add(new Sach(4,"Sharelock Homes tap 3","Linh vuc D","Trinh tham",300,5,"Nha xuat ban Kim Dong",2022));
        danhSachSach.add(new Sach(4,"Sharelock Homes tap 4","Linh vuc D","Trinh tham",300,5,"Nha xuat ban Kim Dong",2022));
        danhSachSach.add(new Sach(4,"Sharelock Homes tap 5","Linh vuc D","Trinh tham",300,5,"Nha xuat ban Kim Dong",2022));
        danhSachSach.add(new Sach(7,"Sach day tieng dong vat","Linh vuc E","khoa hoc",101,7,"Nha xuat ban Kim Khanh",2023));
        for (Sach sach : danhSachSach) {
            System.out.println("======"+sach.getTenSach()+"======");
        }
        //QuyenNhanVien.MenuNhanVien(danhSachSach, hoaDonItems);
    }
}