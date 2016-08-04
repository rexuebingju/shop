package net.jeeshop.services.manage.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jeeshop.core.Services;
import net.jeeshop.core.dao.BaseDao;
import net.jeeshop.core.dao.page.PagerModel;
import net.jeeshop.core.system.bean.Menu;
import net.jeeshop.core.system.bean.Privilege;
import net.jeeshop.core.system.bean.Role;


/**
 * 权限业务逻辑实现类
 * 
 * @author huangf
 * 
 */
@Service
public class PrivilegeService implements Services<Privilege> {
	@Autowired
	private BaseDao dao;

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public List<Privilege> selectList(Privilege privilege) {
		if (privilege == null)
			return dao.selectList("privilege.selectList");
		return dao.selectList("privilege.selectList", privilege);
	}

	public Privilege selectOne(Privilege privilege) {
		return (Privilege) dao.selectOne("privilege.selectOne", privilege);
	}

	public void insert(Privilege privilege) {
		dao.insert("privilege.insert", privilege);
	}

	public int delete(Privilege privilege) {
		return dao.delete("privilege.delete", privilege);
	}

	public void update(Privilege privilege) {
		dao.update("privilege.update", privilege);
	}

	/**
	 * 根绝角色删除权限
	 * 
	 * @param role
	 */
	public void deleteByRole(Role role) {
		Privilege privilege = new Privilege();
		privilege.setRid(role.getId());
		delete(privilege);
	}

	public PagerModel selectPageList(Privilege e) {
		// TODO Auto-generated method stub
		return null;
	}

	public int deletes(Long[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Privilege selectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
