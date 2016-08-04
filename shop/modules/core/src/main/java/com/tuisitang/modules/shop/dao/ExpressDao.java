package com.tuisitang.modules.shop.dao;

import com.tuisitang.modules.shop.entity.Express;

/**    
 * @{#} ExpressDao.java 
 * 
 * 
 * <p>Copyright: Copyright(c) 2013 </p> 
 * <p>Company: TST</p>
 * @version 1.0
 * @author <a href="mailto:paninxb@gmail.com">panin</a>   
 */
@MyBatisRepository
public interface ExpressDao extends BaseDao<Express> {
	
	/**
	 * 10. 根据code获得Express
	 * 
	 * @param code
	 * @return
	 */
	Express getByCode(String code);
	
}
