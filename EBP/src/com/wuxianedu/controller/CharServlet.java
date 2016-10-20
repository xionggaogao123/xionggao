package com.wuxianedu.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="CharServlet", urlPatterns={"/servlet/CharServlet"}) 
public class CharServlet extends HttpServlet {
	private static final long serialVersionUID = 3179657873638009249L;
	private static final int WEIDTH=140;
	private static final int HEIGHT=40;
	
			

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("image/jpeg");
		BufferedImage image=new BufferedImage(WEIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		Graphics graphics=image.getGraphics();
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, WEIDTH, HEIGHT);
		
		graphics.setColor(Color.GRAY);
		graphics.drawRect(0, 0, WEIDTH-2, HEIGHT-2);
		Random random=new Random();	
		for(int i=0;i<10;i++){
		int x1=random.nextInt(WEIDTH);
		int y1=random.nextInt(HEIGHT);
		int x2=random.nextInt(WEIDTH);
		int y2=random.nextInt(HEIGHT);
	
		graphics.drawLine(x1,y1,x2,y2);
		}
		graphics.setColor(Color.RED);
		StringBuilder str=new StringBuilder("  ");
		for(int i=0;i<4;i++){
			int a=random.nextInt(10);
			str.append(a).append("  ");
		}
		Font font=new Font("宋体",Font.BOLD,20);
		graphics.setFont(font);
		graphics.drawString(str.toString(),2,20);
		
		String[] yanzheng = str.toString().split("  ");
		String yzm = "";
		for (String string : yanzheng) {
			yzm += string;
		}
		
		session.setAttribute("yzm", yzm);
		ImageIO.write(image,"jpg",response.getOutputStream());
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
