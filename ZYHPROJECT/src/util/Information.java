package util;

public class Information {
	 private static String Head;
	 private static String Mode;
	 private static String ID;
	 private static String Name;
	 private static String Date;
	 private static String Price;
	 private static String Producer;
	 private static String type;
	 private static String Tail;
	
	
	public String serialize(String Mode,String id,String name,String date,String price,String producer,String type){
		String value="YH"+":"+Mode+":"+id+":"+name+":"+date+":"+price+":"+producer+":"+type+":"+"\\r\\n";
		return value;	//这里会输出包的内容
	}
	
	public void unserialize(String value){
		
		String val[]=value.split(":");
		this.Head = val[0];
		this.Mode = val[1];
		this.ID=val[2];
		this.Name=val[3];
		this.Date=val[4];
		this.Price=val[5];
		this.Producer=val[6];
		this.type = val[7];
		this.Tail = val[8];
	}

	public static String getHead() {
		return Head;
	}

	public static void setHead(String head) {
		Head = head;
	}

	public static String getMode() {
		return Mode;
	}

	public static void setMode(String mode) {
		Mode = mode;
	}

	public static String getID() {
		return ID;
	}

	public static void setID(String iD) {
		ID = iD;
	}

	public static String getName() {
		return Name;
	}

	public static void setName(String name) {
		Name = name;
	}

	public static String getDate() {
		return Date;
	}

	public static void setDate(String date) {
		Date = date;
	}

	public static String getPrice() {
		return Price;
	}

	public static void setPrice(String price) {
		Price = price;
	}

	public static String getProducer() {
		return Producer;
	}

	public static void setProducer(String producer) {
		Producer = producer;
	}

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		Information.type = type;
	}

	public static String getTail() {
		return Tail;
	}

	public static void setTail(String tail) {
		Tail = tail;
	}	
}

