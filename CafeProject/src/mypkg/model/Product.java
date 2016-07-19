package mypkg.model;

public class Product {
	
	private int pid;
	private String name;
	private int stock;
	private int price;
	private String category;
	private String image;
	private String pcontent;
	private int point;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int mpoint) {
		this.point = point;
	}
	
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", name=" + name + ", stock=" + stock
				+ ", price=" + price + ", category=" + category + ", image="
				+ image + ", pcontent=" + pcontent + ", point=" + point + "]";
	}
	
	
	
}
