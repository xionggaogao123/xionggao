package dao;

import java.util.List;

import bean.Commodity;

public interface CommodityDao {
	public boolean doAdd(Commodity commodity);
	public Commodity doFindById(String id);
	public int doCount();
	public int doACount();
	public int doBCount();
	public int doCCount();
	public int doDCount();
	public List<Commodity>findAllA();
	public List<Commodity>findAllB();
	public List<Commodity>findAllC();
	public List<Commodity>findAllD();
}
