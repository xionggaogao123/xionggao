package proxy;

import java.util.List;

import util.GetConnection;
import dao.CommodityDao;
import daoImpl.CommodityDaoImpl;
import bean.Commodity;


public class CommodityDaoProxy implements CommodityDao{
	private GetConnection dbc = null;
	private CommodityDao dao = null;
	
	public CommodityDaoProxy() throws Exception{
		this.dbc = new GetConnection();
		this.dao = new CommodityDaoImpl(this.dbc.getConnection());
	}
	
	public boolean doAdd(Commodity commodity){
		boolean flag = true;
		try {
			flag = this.dao.doAdd(commodity);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public Commodity doFindById(String id){
		Commodity commodity = null;
		try{
			commodity = this.dao.doFindById(id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return commodity;
	}
	
	public int doCount(){
		int count=0;
		try{
			count =  this.dao.doCount();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int doACount(){
		int count=0;
		try{
			count = this.dao.doACount();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int doBCount(){
		int count=0;
		try{
			count = this.dao.doBCount();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int doCCount(){
		int count=0;
		try{
			count = this.dao.doCCount();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int doDCount(){
		int count=0;
		try{
			count = this.dao.doDCount();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public List<Commodity>findAllA(){
		List<Commodity>all = null;
		try{
			all = this.dao.findAllA();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  all;
	}
	
	public List<Commodity>findAllB(){
		List<Commodity>all = null;
		try{
			all = this.dao.findAllB();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  all;
	}
	
	public List<Commodity>findAllC(){
		List<Commodity>all = null;
		try{
			all = this.dao.findAllC();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  all;
	}
	
	public List<Commodity>findAllD(){
		List<Commodity>all = null;
		try{
			all = this.dao.findAllD();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  all;
	}
}
