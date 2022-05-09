package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;



public class DAO {
	private String server;
	private String user;
	private String pass;
	private String db;
	private int port;
	private String selectall = "select * from Sach";
	private String sqlInsert = "Insert into Sach (tenSach, soLuong,nam) values (?,?,?)";
	private String sqlUpdate = "update v set tenSach=?,soLuong=?,nam=? where id=?";
	private String sqlDelete = "delete from Sach where maSach=?";
	private String sqlFindByName = "select * from Sach  where tenSach=?";
	private String getSVByID = "select * from Sach  where maSach=?";

	public DAO(String server, String user, String pass, String db, int port) {
		this.server = server;
		this.user = user;
		this.pass = pass;
		this.db = db;
		this.port = port;
	}

	private Connection getConnection() {
		SQLServerDataSource dt = new SQLServerDataSource();
		dt.setUser(user);
		dt.setPassword(pass);
		dt.setDatabaseName(db);
		dt.setServerName(server);
		dt.setPortNumber(port);
		try {
			return dt.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static void closeConnec(Connection con) {
		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public ArrayList<Sach> getList() {
		Connection con = getConnection();
		Statement st = null;
		ArrayList<Sach> listAll = new ArrayList<Sach>();
		ResultSet rs = null;
		if (con != null) {
			try {
				st = con.createStatement();
				rs = st.executeQuery(selectall);
				while (rs.next()) {
					Sach hh = new Sach(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
					listAll.add(hh);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnec(con);
				closeStatement(st);
			}
		}
		return listAll;
	}

	public Sach getDT(int ID) {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getConnection();
			stmt = con.prepareStatement(getSVByID);
			stmt.setInt(1, ID);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Sach hh = new Sach(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				rs.close();
				return hh;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			closeConnec(con);
		}
	}

	public void insert(Sach hh) {
		Connection con = getConnection();
		PreparedStatement pr = null;
		if (con != null) {
			try {
				pr = con.prepareStatement(sqlInsert);

				pr.setString(1, hh.getTen());
				pr.setInt(2, hh.getSl());
				pr.setInt(3, hh.getNam());

				pr.executeUpdate();
				pr.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnec(con);
			}
		}
	}

	public void update(Sach hh) {
		Connection con = getConnection();
		PreparedStatement pr = null;
		try {
			pr = con.prepareStatement(sqlUpdate);

			pr.setString(1, hh.getTen());
			pr.setInt(2, hh.getSl());
			pr.setInt(3, hh.getNam());
			pr.setInt(4, hh.getId());

			pr.executeUpdate();
			pr.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			closeConnec(con);
		}
	}

	public boolean Delete(Sach hh) {
		Connection con = getConnection();
		PreparedStatement pr = null;
		int k = 0;
		try {
			pr = con.prepareStatement(sqlDelete);
			pr.setInt(1, hh.getId());
			k = pr.executeUpdate();
			pr.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			closeConnec(con);
		}
		if (k > 0) {
			return true;
		}
		return false;
	}

	public ArrayList<Sach> findByName(String ma) {
		Connection con = null;
		PreparedStatement stmt = null;
		ArrayList<Sach> listAll = new ArrayList<Sach>();
		try {
			con = getConnection();
			stmt = con.prepareStatement(sqlFindByName);
			stmt.setString(1, ma);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Sach hh = new Sach(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				listAll.add(hh);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			closeConnec(con);
		}
		return listAll;
	}
}
