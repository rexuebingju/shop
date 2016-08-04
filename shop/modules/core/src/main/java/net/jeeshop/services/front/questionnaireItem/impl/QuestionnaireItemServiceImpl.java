package net.jeeshop.services.front.questionnaireItem.impl;import java.util.List;import net.jeeshop.core.ServersManager;import net.jeeshop.services.front.questionnaireItem.QuestionnaireItemService;import com.tuisitang.modules.shop.entity.QuestionnaireItem;import net.jeeshop.services.front.questionnaireItem.dao.QuestionnaireItemDao;public class QuestionnaireItemServiceImpl extends		ServersManager<QuestionnaireItem> implements QuestionnaireItemService {	private QuestionnaireItemDao questionnaireItemDao;	public void setQuestionnaireItemDao(			QuestionnaireItemDao questionnaireItemDao) {		this.questionnaireItemDao = questionnaireItemDao;	}		public void insertList(List<QuestionnaireItem> list) {		if(list==null)			throw new NullPointerException();				//检查题目是否有相同的存在，同一张问卷不能存在相同的题目		if(questionnaireItemDao.uniqeItem(list.get(0)) > 0){			throw new RuntimeException("一张问卷不允许存在相同的题目！");		}				for(int i=0;i<list.size();i++){			questionnaireItemDao.insert(list.get(i));		}	}}