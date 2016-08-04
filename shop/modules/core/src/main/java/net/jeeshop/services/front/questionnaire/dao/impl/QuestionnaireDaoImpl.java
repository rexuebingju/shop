package net.jeeshop.services.front.questionnaire.dao.impl;import java.util.List;import net.jeeshop.core.dao.BaseDao;import net.jeeshop.core.dao.page.PagerModel;import com.tuisitang.modules.shop.entity.Questionnaire;import net.jeeshop.services.front.questionnaire.dao.QuestionnaireDao;public class QuestionnaireDaoImpl implements QuestionnaireDao {	private BaseDao dao;	public void setDao(BaseDao dao) {		this.dao = dao;	}	public PagerModel selectPageList(Questionnaire e) {		return dao.selectPageList("front.questionnaire.selectPageList",				"front.questionnaire.selectPageCount", e);	}	public List selectList(Questionnaire e) {		return dao.selectList("front.questionnaire.selectList", e);	}	public Questionnaire selectOne(Questionnaire e) {		return (Questionnaire) dao.selectOne("front.questionnaire.selectOne", e);	}	public int delete(Questionnaire e) {		return dao.delete("front.questionnaire.delete", e);	}	public int update(Questionnaire e) {		return dao.update("front.questionnaire.update", e);	}	public int deletes(Long[] ids) {		Questionnaire e = new Questionnaire();		for (int i = 0; i < ids.length; i++) {			e.setId(ids[i]);			delete(e);		}		return 0;	}	public int insert(Questionnaire e) {		return dao.insert("front.questionnaire.insert", e);	}	public int deleteById(long id) {		return dao.delete("front.questionnaire.deleteById", id);	}	@Override	public Questionnaire selectById(Long id) {		return (Questionnaire) dao.selectOne("front.questionnaire.selectById", id);	}}