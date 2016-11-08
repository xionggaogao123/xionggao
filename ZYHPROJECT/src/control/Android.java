package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import util.Information;
import main.Server;
import factory.CommodityDaoFactory;
import factory.UserDaoFactory;
import bean.Commodity;
import bean.User;

public class Android extends Thread{
	private Socket android_socket;
	private BufferedReader in ;
	//private BufferedWriter out;
	PrintWriter out;
	boolean hasInsert = true;
	boolean hasFind = true;
	boolean hasRegiter = true;
	List<Commodity> Acommodity;
	List<Commodity> Bcommodity;
	List<Commodity> Ccommodity;
	List<Commodity> Dcommodity;
	int count;
	int Acount;
	int Bcount;
	int Ccount;
	int Dcount;
	Information information = new Information();
	Commodity commodity = new Commodity();
	User user = new User();
	 
	public Android(Socket ss) {
		// TODO Auto-generated constructor stub
		android_socket=ss;
		try {
			in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(ss.getOutputStream(),"UTF-8"),true);
		//	out = new BufferedWriter(new OutputStreamWriter(ss.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void run(){
		try {
			String Amessage = in.readLine();
			System.out.println("android said: "+Amessage);
			Information information = new Information();
		    information.unserialize(Amessage);
		    char chs[]=Amessage.toCharArray();
			
		    if(chs[3]=='1'&&chs[4]=='1'){ //11�Ű�
		    	if(chs[6]=='0')  Server.javabean.setAndroid_Message_Control("3210");
		    	else if(chs[6]=='1') Server.javabean.setAndroid_Message_Control("3211");
		    }else if(chs[3]=='3'&&chs[4]=='0'){ //30�Ű�����ȡ���µ��¶ȣ�ʪ�ȣ��߶�,�ֿ��̻�����
		    	String HT = Server.javabean.getZigbee_Message_HT(); //�õ��¶ȡ�ʪ��
		    	String Hight = Server.javabean.getZigbee_Message_Hight(); //�õ��߶�
		    	try {
					count = CommodityDaoFactory.getCommodityDaoInstance().doCount();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	System.out.println("��ǰ�ֿ���̻�������Ϊ��"+count);
		    	String Fan= Server.javabean.getZigbee_Message_Fan_state();
		    	String Work_Model=Server.javabean.getZigbee_Message_Work_model();
		    	System.out.println("HT_hight: "+HT +" "+Hight);
		    	out.write("YH"+":"+HT+":"+Hight+":"+count+":"+Fan+":"+Work_Model+":"+"\\r\\n");   //�������׿��
		    }else if(chs[3]=='3'&&chs[4]==':'){
		    	Server.javabean.setAndroid_min_T(Amessage.substring(5,7));
		    	Server.javabean.setAndroid_max_T(Amessage.substring(8,10));
		    	Server.javabean.setAndroid_min_H(Amessage.substring(11,13));
		    	Server.javabean.setAndroid_min_H(Amessage.substring(14,16));
		    }else if(chs[3]=='1'&&chs[4]=='3'){
		    	Server.javabean.setZigbee_Message_Work_model("13");
		    	String str="34"+
		    			Server.javabean.getAndroid_min_T()+
				    	Server.javabean.getAndroid_max_T()+
				    	Server.javabean.getAndroid_min_H()+
				    	Server.javabean.getAndroid_max_H();
		    	System.out.println("Android set max_min HT:"+str);
		    	Server.javabean.setAndroid_Message_Control(str);
		    }else if(chs[3]=='1'&&chs[4]=='2'){
		    	Server.javabean.setAndroid_Message_Control("33");
		    }else if(chs[3]=='1'&&chs[4]=='0'){
		    	out.write("YH"+":"+Server.javabean.getAndroid_min_T()+":"+
		    			Server.javabean.getAndroid_max_T()+":"+
		    			Server.javabean.getAndroid_min_H()+":"+
		    			Server.javabean.getAndroid_max_H()+":"+"\\r\\n");
		    }else if(chs[3]=='1'&&chs[4]==':'){
		    	String ID = information.getID();
				String Name = information.getName();
				String Date = information.getDate();
				String Price = information.getPrice();
				String Producer = information.getProducer();
				String Types = information.getType();
				System.out.println("�ͷ��˷�������ϢΪ"+ID+Name+Date+Price+Producer+Types);
				commodity.setID(ID);
				commodity.setName(Name);
				commodity.setDate(Date);
				commodity.setPrice(Price);
				commodity.setProducer(Producer);
				commodity.setType(Types);
				try {
					hasInsert = CommodityDaoFactory.getCommodityDaoInstance().doAdd(commodity);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("�������Ʒ");
				if(hasInsert){
					hasInsert = false;
					System.out.println("������Ϣ~");                         				      
					out.write("YH:�����Ʒ�ɹ�~:0:0:0:0:\\r\\n");
					System.out.println("end");
				}else{
					out.write("YH:�����Ʒʧ��~:0:0:0:0:\\r\\n");
				}		
		    }else if(chs[3]=='0'&&chs[4]==':'){
		    	String id = information.getID();
			    try {
					commodity = CommodityDaoFactory.getCommodityDaoInstance().doFindById(id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}          //��ѯ��Ʒ����ô�����ǲ��ҵ�����ô���ͷ���
				System.out.println("��ѯ��Ʒ����ϢΪ:"+commodity);  
				if(commodity==null){
					out.write("YH:0:0:����ʧ��:0:0:\\r\\n");
				}else{
					out.write("YH"+":"+commodity+":"+"\\r\\n");					
				}
		    }else if(chs[3]=='1'&&chs[4]=='4'){
		    	String name = information.getID();
		    	String password = information.getName();
		    	System.out.println("��׿��¼����ϢΪ"+name+"��"+password);
		    	user.setName(name);
		    	user.setPassword(password);
		    	try {
					hasFind = UserDaoFactory.getUserDaoInstance().login(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	if(hasFind){
		    		hasFind = false;
		    		out.write("YH:1:0:0:0:0:\\r\\n");
		    	}else{
		    		out.write("YH:0:0:0:0:0:\\r\\n");
		    	}
		    }else if(chs[3]=='1'&&chs[4]=='5'){
		    	String name = information.getID();
		    	String password = information.getName();
		    	System.out.println("��׿ע��ĵ���ϢΪ"+name+"��"+password);
		    	user.setName(name);
		    	user.setPassword(password);
		    	try {
					hasRegiter = UserDaoFactory.getUserDaoInstance().register(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	if(hasRegiter){
		    		hasRegiter = false;
		    		out.write("YH:1:0:0:0:0:\\r\\n");
		    	}else{
		    		out.write("YH:0:0:0:0:0:\\r\\n");
		    	}
		    }else if(chs[3]=='1'&&chs[4]=='6'){//ͳ��A,B,C,D�̻���������
		    	try {
					Acount = CommodityDaoFactory.getCommodityDaoInstance().doACount();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					Bcount = CommodityDaoFactory.getCommodityDaoInstance().doBCount();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					Ccount = CommodityDaoFactory.getCommodityDaoInstance().doCCount();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					Dcount = CommodityDaoFactory.getCommodityDaoInstance().doDCount();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	System.out.println("AƷ�ֵ��̻�����Ϊ:"+Acount);
		    	System.out.println("BƷ�ֵ��̻�����Ϊ:"+Bcount);
		    	System.out.println("CƷ�ֵ��̻�����Ϊ:"+Ccount);
		    	System.out.println("DƷ�ֵ��̻�����Ϊ:"+Dcount);
		    	out.write("YH"+":"+Acount+":"+Bcount+":"+Ccount+":"+Dcount+":"+"\\r\\n");
		    	
		    }else if(chs[3]=='1'&&chs[4]=='7'){
				try {
					Acommodity = CommodityDaoFactory.getCommodityDaoInstance().findAllA();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Iterator<Commodity>iter = Acommodity.iterator();
		    	while(iter.hasNext()){
		    		Commodity commodity = iter.next();
		    		System.out.println(commodity);
		    		out.write("YH"+":"+commodity+":"+"\\r\\n"+";");		
		    	}
		    }else if(chs[3]=='1'&&chs[4]=='8'){
		    	try {
					Bcommodity = CommodityDaoFactory.getCommodityDaoInstance().findAllB();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Iterator<Commodity>iter = Bcommodity.iterator();
		    	while(iter.hasNext()){
		    		Commodity commodity = iter.next();
		    		System.out.println(commodity);
		    		out.write("YH"+":"+commodity+":"+"\\r\\n"+";");		
		    	}
		    }else if(chs[3]=='1'&&chs[4]=='9'){
		    	try {
		    		Ccommodity = CommodityDaoFactory.getCommodityDaoInstance().findAllC();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Iterator<Commodity>iter = Ccommodity.iterator();
		    	while(iter.hasNext()){
		    		Commodity commodity = iter.next();
		    		System.out.println(commodity);
		    		out.write("YH"+":"+commodity+":"+"\\r\\n"+";");		
		    	}
		    }else if(chs[3]=='2'&&chs[4]=='0'){
		    	try {
		    		Dcommodity = CommodityDaoFactory.getCommodityDaoInstance().findAllD();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	Iterator<Commodity>iter = Dcommodity.iterator();
		    	while(iter.hasNext()){
		    		Commodity commodity = iter.next();
		    		System.out.println(commodity);
		    		out.write("YH"+":"+commodity+":"+"\\r\\n"+";");		
		    	}
		    }
			out.flush();
			in.close();
			out.close();
			android_socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

