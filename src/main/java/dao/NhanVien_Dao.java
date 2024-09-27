package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.HoaDon;
import entity.NhanVien;



public class NhanVien_Dao {

	private static ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();

    public NhanVien_Dao() {
    	getAllNhanVien();
    }

	public static ArrayList<NhanVien> getAllNhanVien() {
			dsNhanVien.clear();
			Connection con = Database.getInstance().getConnection();
			try {
				String sql = "select * from NhanVien";
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					dsNhanVien.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getString(6), rs.getDate(7), rs.getBoolean(8), rs.getBoolean(9), rs.getString(10)));
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return dsNhanVien;
		}
	
	
	public static boolean addNhanVien(NhanVien nv) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement psmt = null;
		int n = 0;
		try {
			psmt = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?,?,?,?,?)");
			psmt.setString(1, nv.getMaNV());
			psmt.setString(2, nv.getTenNV());
			psmt.setString(3, nv.getSdt());
			psmt.setBoolean(4, nv.isGioiTinh());
			psmt.setDouble(5, nv.getLuong());
			psmt.setString(6,nv.getCaTruc());
			psmt.setDate(7, nv.getNgaySinh());
			psmt.setBoolean(8, nv.isTrangThaiLV());
			psmt.setBoolean(9, nv.isChucVu());
			psmt.setString(10, nv.getHinhAnhNhanVien());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
	public static boolean deleteNhanVien(String maNV) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement psmt = null;

		int n = 0;
		try {
			psmt = con.prepareStatement("delete NhanVien where maNV=?");
			psmt.setString(1, maNV);
		
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}
	
	public static boolean updateNhanVien(NhanVien nv) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement psmt = null;
		int n = 0;
		try {
			psmt = con.prepareStatement(
					"update NhanVien set tenNV=?, sdt=?, gioiTinh=?, luong=?, caTruc=?, ngaySinh=?, trangThaiLV=?, hinhAnhNhanVien=? where maNV=?");
				psmt.setString(1, nv.getTenNV());
		        psmt.setString(2, nv.getSdt());
		        psmt.setBoolean(3, nv.isGioiTinh());
		        psmt.setDouble(4, nv.getLuong());
		        psmt.setString(5, nv.getCaTruc());
		        psmt.setDate(6, nv.getNgaySinh());
		        psmt.setBoolean(7, nv.isTrangThaiLV());
		      //  psmt.setBoolean(8, nv.isChucVu());
		        psmt.setString(8, nv.getHinhAnhNhanVien());
		        psmt.setString(9, nv.getMaNV());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return n > 0;
	}

	public static NhanVien getNhanVienTheoMa(String maNV) {
    	for (NhanVien nv : dsNhanVien) {
    		if (maNV.equals(nv.getMaNV()))
    			return nv;
    	}
    	return new NhanVien(maNV); // TRÁNH LỖI
	}

	public static ArrayList<NhanVien> getNhanVienTheoTen(String tenNV) {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		Connection con = Database.getInstance().getConnection();
		try {
			String sql = "SELECT * FROM NhanVien WHERE tenNV LIKE N'%" + tenNV + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsNhanVien.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getString(6), rs.getDate(7), rs.getBoolean(8), rs.getBoolean(9), rs.getString(10)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNhanVien;
	}
	public static NhanVien getNhanVienTheoSDT(String sDT) {
		NhanVien nv = null;
		Connection con = Database.getInstance().getConnection();
		try {
			String sql = "select * from NhanVien where sdt = '" + sDT + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				nv=new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getString(6), rs.getDate(7), rs.getBoolean(8), rs.getBoolean(9), rs.getString(10));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nv;
	}
}