package Database;

public class Sach {
	private int id;
	private String ten;
	private int sl;
	private int nam;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}

	public Sach(int id, String ten, int sl, int nam) {
		super();
		this.id = id;
		this.ten = ten;
		this.sl = sl;
		this.nam = nam;
	}

	public Sach() {
		super();
	}

}
