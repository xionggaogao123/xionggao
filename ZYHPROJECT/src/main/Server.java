package main;
import java.io.IOException;
import java.net.ServerSocket;

import control.Android;
import control.Eserver;
import control.Zigbee;
import bean.javaBean;
public class Server {

	public  static  javaBean javabean;
	private static  ServerSocket Android_socket;
	private static  ServerSocket Zigbee_socket;
	private static  ServerSocket Eerver_socket;
	public Server() throws IOException{
		Android_socket = new ServerSocket(10003);
		Zigbee_socket = new ServerSocket(10006);
		Eerver_socket = new ServerSocket(80);
		javabean= new javaBean();
	}
	public static void main(String[] args)  {
		try {
			new Server();
			System.out.println("wait client!!!");
			new Android_acc(Android_socket).start();
			new Zigbee_acc(Zigbee_socket).start();
			new Erserver_acc(Eerver_socket).start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class Android_acc extends Thread{
	private ServerSocket Android_socket;
	public Android_acc(ServerSocket Android_socket){
		this.Android_socket=Android_socket;
	}
	public void run(){
	
		while(true)
		{	
			try {
				new Android(Android_socket.accept()).start();
				System.out.println("进入android!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Zigbee_acc extends Thread{
	private ServerSocket zigbee_socket;
	public Zigbee_acc(ServerSocket zigbee_socket){
		this.zigbee_socket=zigbee_socket;
	}
	public void run(){
		while(true)
		{	
			try {
				new Zigbee(zigbee_socket.accept()).start();
				System.out.println("进入zigbee!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Erserver_acc extends Thread{
	private ServerSocket erserver_socket;
	public Erserver_acc(ServerSocket erserver_socket){
		this.erserver_socket=erserver_socket;
	}
	public void run(){
		while(true)
		{	
			try {
				new Eserver(erserver_socket.accept()).start();
				System.out.println("进入二维码!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

