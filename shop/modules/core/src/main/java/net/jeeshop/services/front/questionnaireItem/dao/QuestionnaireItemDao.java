package net.jeeshop.services.front.questionnaireItem.dao;import net.jeeshop.core.DaoManager;import com.tuisitang.modules.shop.entity.QuestionnaireItem;public interface QuestionnaireItemDao extends DaoManager<QuestionnaireItem> {	int uniqeItem(QuestionnaireItem questionnaireItem);}