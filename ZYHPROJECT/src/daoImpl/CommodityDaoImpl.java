package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.CommodityDao;
import bean.Commodity;

public class CommodityDaoImpl implements CommodityDao{
	public Connection conn = null;
	public CommodityDaoImpl(Connection conn){
		this.conn = conn;
	}
	PreparedStatement pstmt = null;
	public boolean doAdd(Commodity commodity){  //������Ʒ��Ϣ
		boolean flag = false;
		String sql = "INSERT INTO firework(id,name,date,price,producer,type) VALUES (?,?,?,?,?,?) ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, commodity.getID()); 
			pstmt.setString(2, commodity.getName()); 
			pstmt.setString(3, commodity.getDate());
			pstmt.setString(4, commodity.getPrice());
			pstmt.setString(5, commodity.getProducer());
			pstmt.setString(6, commodity.getType());
			if (pstmt.executeUpdate() > 0) {// �����Ѿ�������һ��
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // ��������׳������տ϶���Ҫ�������ݿ�Ĺرղ�����
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return flag;
	}
	public Commodity doFindById(String id){ //��ѯ��Ʒ��Ϣ
		Commodity commodity = null;
		String sql = "SELECT id,name,date,price,producer,type FROM firework WHERE id=?";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery(); // ִ�в�ѯ����
			if (rs.next()) {
				commodity = new Commodity();
				commodity.setID(rs.getString(1));
				commodity.setName(rs.getString(2));
				commodity.setDate(rs.getString(3));
				commodity.setPrice(rs.getString(4));
				commodity.setProducer(rs.getString(5));
				commodity.setType(rs.getString(6));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // ��������׳������տ϶���Ҫ�������ݿ�Ĺرղ�����
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return commodity;
	}
	
	public int doCount(){ //ͳ�Ƶ�ǰ�ֿ��̻�������
		String sql = "select count(*) from firework";
		int count=0 ;
		try{
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public int doACount(){
		String sql = "select count(*) from firework where type ='A'";
		int count=0 ;
		try{
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public int doBCount(){
		String sql = "select count(*) from firework where type ='B'";
		int count=0 ;
		try{
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public int doCCount(){
		String sql = "select count(*) from firework where type ='C'";
		int count=0 ;
		try{
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public int doDCount(){
		String sql = "select count(*) from firework where type ='D'";
		int count=0 ;
		try{
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Commodity>findAllA(){
		List<Commodity>all = new ArrayList<Commodity>();
		String sql = "select *from firework where type = 'A'";
		try{
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Commodity commodity = new Commodity();
				commodity.setID(rs.getString(1));
				commodity.setName(rs.getString(2));
				commodity.setDate(rs.getString(3));
				commodity.setPrice(rs.getString(4));
				commodity.setProducer(rs.getString(5));
				commodity.setType(rs.getString(6));
				all.add(commodity);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return all;
	}
	public List<Commodity>findAllB(){
		List<Commodity>all = new ArrayList<Commodity>();
		String sql = "select *from firework where type = 'B'";
		try{
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Commodity commodity = new Commodity();
				commodity.setID(rs.getString(1));
				commodity.setName(rs.getString(2));
				commodity.setDate(rs.getString(3));
				commodity.setPrice(rs.getString(4));
				commodity.setProducer(rs.getString(5));
				commodity.setType(rs.getString(6));
				all.add(commodity);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return all;
	}
	
	public List<Commodity>findAllC(){
		List<Commodity>all = new ArrayList<Commodity>();
		String sql = "select *from firework where type = 'C'";
		try{
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Commodity commodity = new Commodity();
				commodity.setID(rs.getString(1));
				commodity.setName(rs.getString(2));
				commodity.setDate(rs.getString(3));
				commodity.setPrice(rs.getString(4));
				commodity.setProducer(rs.getString(5));
				commodity.setType(rs.getString(6));
				all.add(commodity);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return all;
	}
	
	public List<Commodity>findAllD(){
		List<Commodity>all = new ArrayList<Commodity>();
		String sql = "select *from firework where type = 'D'";
		try{
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Commodity commodity = new Commodity();
				commodity.setID(rs.getString(1));
				commodity.setName(rs.getString(2));
				commodity.setDate(rs.getString(3));
				commodity.setPrice(rs.getString(4));
				commodity.setProducer(rs.getString(5));
				commodity.setType(rs.getString(6));
				all.add(commodity);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return all;
	}
	
}

