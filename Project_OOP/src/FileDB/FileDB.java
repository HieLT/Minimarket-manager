 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template FileDB, choose Tools | Templates
 * and open the template in the editor.
 */
package FileDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.HoaDon;
import model.NhanVien;
import model.SanPham;

/**
 *
 * @author tungb_000
 */
public class FileDB {

    //public static ArrayList<SanPham> ds_SanPham = new ArrayList<SanPham>();
    public static ArrayList<NhanVien> ds_NhanVien = new ArrayList<>(); //type = 0
    public static ArrayList<SanPham> ds_SanPham = new ArrayList<>(); // type = 3
    public static ArrayList<HoaDon> ds_HoaDon = new ArrayList<>();

    Workbook workbook, workbook1, workbook2, workbook3;
    WritableWorkbook workbook_To_Write, workbook_To_Write1, workbook_To_Write2, workbook_To_Write3;
    File file, file1, file2;

    public FileDB() {
        try {
            file = new File("Danh Sach San Pham.xls");
            workbook = Workbook.getWorkbook(file);
            getDanhSachSanPham();
        } catch (Exception e) {
        }
//        } catch (IOException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BiffException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            file1 = new File("Danh Sách Nhân Viên.xls");
            workbook1 = Workbook.getWorkbook(file1);
            getDanhSachNhanVien();
        } catch (Exception e) {
        }
//        } catch (IOException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BiffException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        try {
//            file2 = new File("Danh Sách Thu Chi.xls");
//            workbook2 = Workbook.getWorkbook(file2);
//            getDanhSachThuChi();
//        } catch (Exception e) {
//        }
//        } catch (IOException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BiffException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            file2 = new File("Danh Sách Hóa Đơn.txt");
            getDanhSachHoaDon();
//            for (int i= 0;i<ds_HoaDon.size();i++){
//                System.out.println(ds_HoaDon.get(i).getDs_SanPham().size());
//            }
        } catch (Exception e) {
        }
//        } catch (IOException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BiffException ex) {
//            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public void writeSanPhamToFile() {
        try {
            file = new File("Danh Sach San Pham.xls");
            if (file.exists()) {
                file.delete();
            }
            workbook_To_Write = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook_To_Write.createSheet("Sách", 0);
            sheet1.mergeCells(0, 0, 8, 0);
            sheet1.addCell(new Label(0, 0, "Danh Sách Sản Phẩm", Cell(sheet1, Alignment.CENTRE)));
            sheet1.addCell(new Label(0, 1, "STT", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(1, 1, "Mã Sp", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(2, 1, "Tên Sản Phẩm", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(3, 1, "Loại Sản Phẩm", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(4, 1, "Đơn Vị", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(5, 1, "Vốn", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(6, 1, "Giá Bán", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(7, 1, "Thương Hiệu", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(8, 1, "Số Lượng", Cell(sheet1, Alignment.CENTRE)));

            for (int i = 0; i < ds_SanPham.size(); i++) {
                sheet1.addCell(new jxl.write.Number(0, i + 2, i + 1, Cell(sheet1, Alignment.CENTRE)));
                sheet1.addCell(new Label(1, i + 2, ds_SanPham.get(i).getMaSanPham(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(2, i + 2, ds_SanPham.get(i).getTenSanPham(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(3, i + 2, ds_SanPham.get(i).getLoaiSanPham(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(4, i + 2, ds_SanPham.get(i).getDonVi(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new jxl.write.Number(5, i + 2, ds_SanPham.get(i).getGiaMua(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new jxl.write.Number(6, i + 2, ds_SanPham.get(i).getGiaBan(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(7, i + 2, ds_SanPham.get(i).getThuongHieu(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new jxl.write.Number(8, i + 2, ds_SanPham.get(i).getSoLuong(), Cell(sheet1, Alignment.CENTRE)));
            }
            workbook_To_Write.write();
            workbook_To_Write.close();
        } catch (IOException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getDanhSachSanPham() {
        try {
            ds_SanPham.clear();
            Sheet sheet1 = workbook.getSheet(0);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            for (int row = 2; row < rows; row++) {
                SanPham s = new SanPham(sheet1.getCell(1, row).getContents(), sheet1.getCell(2, row).getContents(),
                        sheet1.getCell(3, row).getContents(), Integer.parseInt(sheet1.getCell(5, row).getContents()),
                        Integer.parseInt(sheet1.getCell(6, row).getContents()), sheet1.getCell(7, row).getContents(),
                        sheet1.getCell(4, row).getContents(), Integer.parseInt(sheet1.getCell(8, row).getContents()));
                ds_SanPham.add(s);
            }
        } 
        catch (Exception e) {
        }
    }

    public void writeNhanVienToFile() {
        file1 = new File("Danh Sách Nhân Viên.xls");
        if (file1.exists()) {
            file1.delete();
        }
        try {
            workbook_To_Write1 = Workbook.createWorkbook(file1);
            WritableSheet sheet1 = workbook_To_Write1.createSheet("Nhân Viên", 0);
            sheet1.mergeCells(0, 0, 11, 0);
            sheet1.addCell(new Label(0, 0, "Danh Sách Nhân Viên", Cell(sheet1, Alignment.CENTRE)));
//            for (int i = 1;i<12;i++){
//                CellView cell = sheet1.getColumnView(i);
//                cell.setAutosize(true);
//                sheet1.setColumnView(i, cell);
//            }
            sheet1.addCell(new Label(0, 1, "STT", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(1, 1, "Mã Nhân Viên", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(2, 1, "Tên Nhân Viên", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(3, 1, "Giới Tính", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(4, 1, "Ngày Sinh", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(5, 1, "Địa Chỉ", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(6, 1, "Chức Vụ", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(7, 1, "Email", Cell(sheet1, jxl.format.Alignment.CENTRE)));
            sheet1.addCell(new Label(8, 1, "Số Điện Thoại", Cell(sheet1, Alignment.CENTRE)));
            sheet1.addCell(new Label(9, 1, "Tên Đăng Nhập", Cell(sheet1, Alignment.CENTRE)));
            sheet1.addCell(new Label(10, 1, "Mật Khẩu", Cell(sheet1, Alignment.CENTRE)));
            sheet1.addCell(new Label(11, 1, "Lương", Cell(sheet1, Alignment.CENTRE)));

            for (int i = 0; i < ds_NhanVien.size(); i++) {
                sheet1.addCell(new jxl.write.Number(0, i + 2, i + 1, Cell(sheet1, Alignment.CENTRE)));
                sheet1.addCell(new Label(1, i + 2, ds_NhanVien.get(i).getMaNhanVien(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(2, i + 2, ds_NhanVien.get(i).getTenNhanVien(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(3, i + 2, ds_NhanVien.get(i).getGioiTinh(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(4, i + 2, ds_NhanVien.get(i).getNgaySinh(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(5, i + 2, ds_NhanVien.get(i).getDiaChi(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(6, i + 2, ds_NhanVien.get(i).getChucVu(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(7, i + 2, ds_NhanVien.get(i).getEmail(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(8, i + 2, ds_NhanVien.get(i).getSoDienThoai(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(9, i + 2, ds_NhanVien.get(i).getTenDangNhap(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new Label(10, i + 2, ds_NhanVien.get(i).getMatKhau(), Cell(sheet1, Alignment.LEFT)));
                sheet1.addCell(new jxl.write.Number(11, i + 2, ds_NhanVien.get(i).getLuongTheoThang(), Cell(sheet1, Alignment.LEFT)));
            }
            workbook_To_Write1.write();
            workbook_To_Write1.close();
        } catch (IOException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getDanhSachNhanVien() {
        try {
            ds_NhanVien.clear();
            Sheet sheet1 = workbook1.getSheet(0);
            int cols = sheet1.getColumns();
            int rows = sheet1.getRows();
            for (int row = 2; row < rows; row++) {
                String maNhanVien = sheet1.getCell(1, row).getContents();
                String tenNhanVien = sheet1.getCell(2, row).getContents();
                String gioiTinh = sheet1.getCell(3, row).getContents();
                String ngaySinh = sheet1.getCell(4, row).getContents();
                String diaChi = sheet1.getCell(5, row).getContents();
                String chucVu = sheet1.getCell(6, row).getContents();
                String email = sheet1.getCell(7, row).getContents();
                String soDienThoai = sheet1.getCell(8, row).getContents();
                String tenDangNhap = sheet1.getCell(9, row).getContents();
                String matKhau = sheet1.getCell(10, row).getContents();
                int luongTheoThang = Integer.parseInt(sheet1.getCell(11, row).getContents());
                NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, diaChi, chucVu, email, soDienThoai, tenDangNhap, matKhau, luongTheoThang);
                ds_NhanVien.add(nv);
            }
        } catch (Exception e) {
        }
    }
    public void writeHoaDonToFile(HoaDon hd) throws IOException {
        //file3 = new File("Danh Sách Hóa Đơn.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file2, true));
        bw.write(hd.getMaHoaDon() + "," + hd.getTenKhachHang() + "," + hd.getSoDienThoai() + "," + hd.getNgay() + "," + hd.getTongTien() + "," + hd.getDs_SanPham().size() +","  );
        for (int i = 0; i < hd.getDs_SanPham().size(); i++) {
            SanPham sp = hd.getDs_SanPham().get(i);
            bw.write(sp.getMaSanPham() +  "," + sp.getTenSanPham() +"," + sp.getDonVi() +"," + sp.getGiaBan() + "," + sp.getSoLuong() + ",");
        }
        bw.newLine();
        bw.close();
    }

    private void getDanhSachHoaDon() throws FileNotFoundException, IOException {
        ds_HoaDon.clear();
        BufferedReader br = new BufferedReader(new FileReader(file2));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] s = line.split(",");
            String maHoaDon = s[0];
            String tenKhachHang=s[1];
            String soDienThoai=s[2];
            String ngay = s[3];
            long tongTien = Long.parseLong(s[4]);
            int soMatHang = Integer.parseInt(s[5]);
            ArrayList<SanPham> listSanPhamTrongHoaDon = new ArrayList<>();
            for (int k = 0; k < soMatHang; k++) {
                String maSP = s[5 * k + 6];
                String tenSanPham = s[5 * k + 7];
                String donVi = s[5 * k + 8];
                int giaBan = Integer.parseInt(s[5 * k + 9]);
                int soLuong = Integer.parseInt(s[5 * k + 10]);
                listSanPhamTrongHoaDon.add(new SanPham(maSP, tenSanPham , donVi , giaBan, soLuong));
            }
//            System.out.println(listSanPhamTrongHoaDon);
            
            ds_HoaDon.add(new HoaDon(maHoaDon,tenKhachHang,soDienThoai, ngay, listSanPhamTrongHoaDon,tongTien));
// ds_HoaDon.add(new HoaDon("1", "1", listSanPhamTrongHoaDon, 1));
        }
    }

    public void updatedanhSachHoaDon() throws IOException {
        file2.delete();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file2));
        for (int i = 0; i < ds_HoaDon.size(); i++) {
            HoaDon hd = ds_HoaDon.get(i);
            bw.write(hd.getMaHoaDon() + "," + hd.getNgay() + "," + hd.getTongTien() + "," + hd.getDs_SanPham().size() + ",");
            for (int k = 0; k < hd.getDs_SanPham().size(); k++) {
                SanPham sp = hd.getDs_SanPham().get(k);
                bw.write(sp.getMaSanPham() + "," + sp.getTenSanPham() + "," + sp.getGiaBan() + "," + sp.getSoLuong() + ",");
            }
            bw.newLine();
        }
        bw.close();
    }

    public ArrayList<HoaDon> get_Ds_Hoa_Don() {
        return ds_HoaDon;
    }

    public ArrayList<NhanVien> get_Ds_NhanVien() {
        return ds_NhanVien;
    }

    public ArrayList<SanPham> get_Ds_SanPham() {
        return ds_SanPham;
    }


    //can le
    public jxl.format.CellFormat Cell(WritableSheet sheet, jxl.format.Alignment alignment) {
        WritableCellFormat cellFormat = new WritableCellFormat();
        try {
            cellFormat.setAlignment(alignment);
            cellFormat.setWrap(true);
        } catch (WriteException ex) {
            Logger.getLogger(FileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cellFormat;
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dates = new Date();
        return dateFormat.format(dates);
    }

}
