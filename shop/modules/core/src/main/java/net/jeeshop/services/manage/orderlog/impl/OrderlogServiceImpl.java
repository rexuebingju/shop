package net.jeeshop.services.manage.orderlog.impl;import net.jeeshop.core.ServersManager;import net.jeeshop.services.manage.orderlog.OrderlogService;import com.tuisitang.modules.shop.entity.Orderlog;import net.jeeshop.services.manage.orderlog.dao.OrderlogDao;public class OrderlogServiceImpl extends ServersManager<Orderlog> implements		OrderlogService {	private OrderlogDao orderlogDao;	public void setOrderlogDao(OrderlogDao orderlogDao) {		this.orderlogDao = orderlogDao;	}}