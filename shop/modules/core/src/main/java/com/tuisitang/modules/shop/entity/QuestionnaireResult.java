package com.tuisitang.modules.shop.entity;import java.io.Serializable;import net.jeeshop.core.dao.page.PagerModel;/**     * @{#} QuestionnaireResult.java *  * 问卷结果对象 *     * <p>Copyright: Copyright(c) 2013 </p>  * <p>Company: TST</p> * @version 1.0 * @author <a href="mailto:paninxb@gmail.com">panin</a>    */public class QuestionnaireResult extends PagerModel<QuestionnaireResult> implements Serializable {	private static final long serialVersionUID = 1L;	protected Long id;	private String qid;	private Long accountId;	private String qItemID;	private String text;	public void clear() {		super.clear();		id = null;		qid = null;		accountId = null;		qItemID = null;		text = null;	}	public Long getId() {		return id;	}	public void setId(Long id) {		this.id = id;	}	public String getQid() {		return qid;	}	public void setQid(String qid) {		this.qid = qid;	}	public Long getAccountId() {		return accountId;	}	public void setAccountId(Long accountId) {		this.accountId = accountId;	}	public String getqItemID() {		return qItemID;	}	public void setqItemID(String qItemID) {		this.qItemID = qItemID;	}	public String getText() {		return text;	}	public void setText(String text) {		this.text = text;	}}