package factory;

import proxy.CommodityDaoProxy;
import dao.CommodityDao;

public class CommodityDaoFactory {
	public static CommodityDao getCommodityDaoInstance() throws Exception{
		return new CommodityDaoProxy() ;
	}
}
