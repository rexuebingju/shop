package net.jeeshop.services.manage.attribute_link.impl;import net.jeeshop.core.ServersManager;import net.jeeshop.services.manage.attribute_link.Attribute_linkService;import com.tuisitang.modules.shop.entity.AttributeLink;import net.jeeshop.services.manage.attribute_link.dao.Attribute_linkDao;public class Attribute_linkServiceImpl extends ServersManager<AttributeLink>		implements Attribute_linkService {	private Attribute_linkDao attribute_linkDao;	public void setAttribute_linkDao(Attribute_linkDao attribute_linkDao) {		this.attribute_linkDao = attribute_linkDao;	}	@Override	public int deleteByCondition(AttributeLink e) {		return attribute_linkDao.deleteByCondition(e);	}}