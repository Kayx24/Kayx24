
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class QuanLy extends TaiKhoan {
    private  String UserName;
    private  String Password;
    private  String role;
    private String MaQL;
    private String TenQL;
    private String Duoi;

    public QuanLy (){

    }
  
    public QuanLy(String MaQL, String userName, String password, String role){
    super(userName,password,role);
    this.MaQL=MaQL;
}

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMaQL() {
        return MaQL;
    }

    public void setMaQL(String maQL) {
        MaQL = maQL;
    }

    public String getTenQL() {
        return TenQL;
    }

    public void setTenQL(String tenQL) {
        TenQL = tenQL;
    }

    public String getDuoi() {
        return Duoi;
    }

    public void setDuoi(String duoi) {
        Duoi = duoi;
    }


 public static void MenuQuanly(List<Sach> danhSachSach,List<HoaDonItem> hoaDonItems,DanhSachTK ds,List<NhanVien> dNhanViens,String tenFile){
        Scanner sc = new Scanner(System.in);
        int choice;
        QuyenSach quyenSach = new QuyenSach();
        QuyenNhanVien qnv = new QuyenNhanVien();
        NhanVien nv = new NhanVien();
        User us = new User();
        ChiTietHoaDon cthd =new ChiTietHoaDon();
        QuyenQuanLy qql=new QuyenQuanLy() {
            @Override
            public String getRole() {
                Scanner sc = new Scanner(System.in);
                System.out.println("Chon vai tro cho tai khoan");
                System.out.println("[1]: user");
                System.out.println("[2] NhanVien");
                System.out.println("[0] Thoat");
                int choose = sc.nextInt();
                switch (choose) {
                    case 1:
                        return "user";
                    case 2:
                        return "NhanVien";
                    default:
                        return "";
                }
            };
            @Override
            public boolean CoTheXoa(String tenTaiKhoan, DanhSachTK ds) {
                TaiKhoan tk = ds.layTaiKhoan(tenTaiKhoan);
                if (tk != null && tk.getRole().equals("admin")) {
                    return false; // Account with role "Admin" cannot be deleted
                }
                return true; // Allow deletion for other roles
            }
        };
        while (true) {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.println("Chuc nang Quanly");
            System.out.println("[0] THOAT MENU");
            System.out.println("[1] Lua chon cac chuc nang User");
            System.out.println("[2] Lua chon cac chuc nang Nhan vien");
            System.out.println("[3] Them sach");
            System.out.println("[4] Sua Thong tin sach");
            System.out.println("[5] Chinh sua nhan vien");
            System.out.println("[6] Them Tai Khoan");
            System.out.println("[7] Xoa Tai Khoan");
            System.out.println("[8] In Danh Sach Tai Khoan");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                DangNhap.DangNhaptaikhoan();
                break;
                case 1:
                    us.MenuUser(danhSachSach, hoaDonItems, ds, tenFile);
                    break;
                case 2:
                    nv.MenuNhanVien(danhSachSach, hoaDonItems, ds, tenFile);
                    break;
                case 3:
                    quyenSach.nhapThongTinSachMoi();
                    break;
                case 4:
                    quyenSach.chinhSuaThongTinSach();
                    break;
                case 5:
                    qnv.runQuyenNhanVien(ds,dNhanViens);
                   break;
                case 6:
                    qql.QuanLyThemTaiKhoan(ds);
                    break;
                case 7:
                    qql.QuanLyXoaTaiKhoan(ds);
                    break;
                case 8:
                    QuyenQuanLy.QuanLyInTaiKhoan(ds);
                    break;
            }
        }
    }
}


