package control;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

import factory.CommodityDaoFactory;
import bean.Commodity;

public class Eserver extends Thread{
	private Socket eserver_socket;
	private InputStream is ;
	private OutputStream out;
	public String encoding = "GBK";
	Commodity commodity ;
	public Eserver(Socket ee){
		 eserver_socket = ee;
		 try {
				is = ee.getInputStream();
				out = ee.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void run(){
		try{
			is = eserver_socket.getInputStream();
			System.out.println("客户端发送的请求信息: >>>>>>>>>>>>>>>>>>>>>>>>>");
			String line = readLine(is);
			String resource = line.substring(line.indexOf('/'),
					line.lastIndexOf('/') - 5);
			resource = URLDecoder.decode(resource, encoding);
			String id = resource.substring(1,resource.length());
			String method = new StringTokenizer(line).nextElement()
					.toString();
			int contentLength = 0;
			do {
				line = readLine(is);
				if (line.startsWith("Content-Length")) {
					contentLength = Integer.parseInt(line
							.split(":")[1].trim());
				}
				System.out.print(line);
			} while (!line.equals("\r\n"));
			System.out.println("客户端发送的请求信息结束 <<<<<<<<<<<<<<<<<<<<<<<<<<");
			System.out.println("用户请求的资源是:" + resource);
			System.out.println("商品ID"+id);
			System.out.println("请求的类型是: " + method);
			System.out.println();
			
			commodity = CommodityDaoFactory.getCommodityDaoInstance().doFindById(id);
			System.out.println("查询商品的信息为:"+commodity);  
			if(commodity ==null){
				PrintWriter out = new PrintWriter(
						eserver_socket.getOutputStream(), true);
				out.println("HTTP/1.0 200 OK");
				out.println("Content-Type:text/html;charset="
						+ encoding);
				out.println();
				out.println("<title>烟花管理系统</title>");
				out.println("<h1>该产品为假冒伪劣产品</h1>");
				out.println("你好, 请求的资源未找到.<br>");
				out.close();
				is.close();
				eserver_socket.close();
			}else{
				String message = commodity.toString();
				String a[] = message.split(":");
				PrintWriter out = new PrintWriter(
						eserver_socket.getOutputStream(), true);
				out.println("HTTP/1.0 200 OK");
				out.println("Content-Type:text/html;charset="
						+ encoding);
				out.println();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>烟花安全管理溯源系统</title>");
				out.println("</head>");
				out.println("<body bgcolor=\"#7CFC00\">");
				out.println("<h1 align =center style='color:red'>商品信息如下</h1>");
				out.println("<h2  style='font-size:30px>产 品 ID:"+a[0]+"</h2>");
				out.println("<h2 style='font-size:30px>产品名称:"+a[1]+"</h2>");
				out.println("<h2 style='font-size:30px>产品生产时间:"+a[2]+"</h2>");
				out.println("<h2 style='font-size:30px>产 品 价 格:"+a[3]+"</h2>");
				out.println("<h2 style='font-size:30px>产品生产商:"+a[4]+"</h2>");
				out.println("<h2 style='font-size:30px>产 品 类 别:"+a[5]+"</h2>");
				out.println("</body></html>");
				
				out.close();
				is.close();
				eserver_socket.close();
			}			
		}catch(Exception e){
			System.out.println("HTTP服务器错误:"
					+ e.getLocalizedMessage());
		}
	  }
	
	
	private String readLine(InputStream is) throws IOException {
		ArrayList lineByteList = new ArrayList();
		byte readByte;
		int total = 0;
			do {
				readByte = (byte) is.read();
				lineByteList.add(Byte.valueOf(readByte));
			} while (readByte != 10);
		byte[] tmpByteArr = new byte[lineByteList.size()];
		for (int i = 0; i < lineByteList.size(); i++) {
			tmpByteArr[i] = ((Byte) lineByteList.get(i)).byteValue();
		}
		lineByteList.clear();

		String tmpStr = new String(tmpByteArr, encoding);
		if (tmpStr.startsWith("Referer")) {// 如果有Referer头时，使用UTF-8编码
			tmpStr = new String(tmpByteArr, "UTF-8");
		}
		return tmpStr;
	}

}
