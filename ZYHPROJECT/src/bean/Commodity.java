package bean;

public class Commodity {

	 private  String ID;
	 private  String Name;
	 private  String Date;
	 private  String Price;
	 private  String Producer;
	 private String type;
	 public Commodity(){
	
		
	 }
	public Commodity(String ID,String Name,String Date,String Price,String Producer,String type){
		this.ID = ID;
		this.Name = Name;
		this.Date = Date;
		this.Price = Price;
		this.Producer = Producer;
		this.type = type;
	}
	public  String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public  String getName() {
		return Name;
	}
	public  void setName(String name) {
		Name = name;
	}
	public  String getDate() {
		return Date;
	}
	public  void setDate(String date) {
		Date = date;
	}
	public  String getPrice() {
		return Price;
	}
	public  void setPrice(String price) {
		Price = price;
	}
	public  String getProducer() {
		return Producer;
	}
	public  void setProducer(String producer) {
		Producer = producer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString(){  //该方法返回一个对象的字符串表示
		return ID+":"+Name+":"+Date+":"+Price+":"+Producer+":"+type;
	}
}