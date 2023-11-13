import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class User extends TaiKhoan{
    private  String UserName;
    private  String Password;
    private  String role;
    private  int msUser;
    // check ma user nhằm tránh sai sót khi có 2 user trở lên với database
    // thêm menu user

    public User(String userName, String password, String role,int msUser) {
        super(userName, password, role);
        this.msUser=msUser;
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

    public int getMsUser() {
        return msUser;
    }
   

    public static void MenuUser(List<Sach> danhSachSach,List<HoaDonItem> hoaDonItems,DanhSachTK ds){
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.println("Chuc nang User");
            System.out.println("[0] THOAT MENU");
<<<<<<< Updated upstream
            System.out.println("[1] Xem danh sach sach");
            System.out.println("[2] Xem thong tin sach can tim");
            System.out.println("[3] Phan loai Sach");
            System.out.println("[4] Mua");
            System.out.println("[5] Chi tiet hoa don");
            System.out.println("[6] xem thong tin tac gia");
=======
            System.out.println("[1] Dang ky tai khoan");
            System.out.println("[2] Xem danh sach sach");
            System.out.println("[3] Xem thong tin sach can tim");
            System.out.println("[4] Phan loai Sach");
            System.out.println("[5] Mua");
            System.out.println("[6] Chi tiet hoa don");
>>>>>>> Stashed changes
            System.out.print("Chon: ");
            boolean shouldExit = false;
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    DangNhap.DangNhaptaikhoan();
                    break;
                case 1:
                    QuyenUser.TaiKhoanUser(ds);
                    break;
                case 2:
                for (Sach sach : danhSachSach) {
                    System.out.println("======"+sach.getTenSach()+"======");
                    }
                    break;
                case 3:
                    QuyenUser.xemThongTinSach(danhSachSach);
                    break;
                case 4:
                    PhanLoai.MenuPhanLoai(danhSachSach);
                    break;
                case 5:
                    HoaDon.hoaDon(danhSachSach,hoaDonItems);
                    break;
                case 6:
                    ChiTietHoaDon.ChiTietHoaDon(danhSachSach,hoaDonItems);
                    break;
            }
        }
    }
}
