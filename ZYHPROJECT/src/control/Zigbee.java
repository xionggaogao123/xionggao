package control;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import main.Server;


public class Zigbee extends Thread{
	private Socket zigbee_socket;
	private BufferedReader DIS;
	private PrintStream PS;
	public Zigbee(Socket ss)  {
		// TODO Auto-generated constructor stub
		zigbee_socket=ss;
		try {
			 DIS = new BufferedReader(new InputStreamReader(ss.getInputStream()));
			 PS = new PrintStream(ss.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void run(){
		try {
			
			String Zmessage=DIS.readLine();
			
			char chs[]=Zmessage.toCharArray();
			
			if(chs[0]=='3'&&chs[1]=='0'){
				String t = Zmessage.substring(2,4);
				String h = Zmessage.substring(4,6);
				String str=t+":"+h;
				System.out.println("HT: "+str);
				Server.javabean.setZigbee_Message_HT(str);
				System.out.println(Server.javabean.getAndroid_Message_Control());
				PS.println(Server.javabean.getAndroid_Message_Control());
			}else if(chs[0]=='3'&&chs[1]=='1'){
				String m = Zmessage.substring(2,5);
				String str = m;
				System.out.println("High :"+str);
				Server.javabean.setZigbee_Message_Hight(str);
				System.out.println(Server.javabean.getAndroid_Message_Control());
				PS.println(Server.javabean.getAndroid_Message_Control());
			}else if(chs[0]=='3'&&chs[1]=='2'){
				String fan = Zmessage.substring(3);
				String str = fan;
				System.out.println("Fan :"+str);
				Server.javabean.setZigbee_Message_Fan_state(str);
				System.out.println(Server.javabean.getAndroid_Message_Control());
				PS.println(Server.javabean.getAndroid_Message_Control());
			}else{
				System.out.println("接收到错误信息!!!");
				System.out.println(Server.javabean.getAndroid_Message_Control());
				PS.println(Server.javabean.getAndroid_Message_Control());
			}
			PS.flush();
			DIS.close();
			PS.close();
			zigbee_socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
