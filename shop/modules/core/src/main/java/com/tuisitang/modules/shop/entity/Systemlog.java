package com.tuisitang.modules.shop.entity;import java.io.Serializable;import net.jeeshop.core.dao.page.PagerModel;/**     * @{#} Systemlog.java *  * <p>Copyright: Copyright(c) 2013 </p>  * <p>Company: TST</p> * @version 1.0 * @author <a href="mailto:paninxb@gmail.com">panin</a>    */public class Systemlog extends PagerModel<Systemlog> implements Serializable {	private static final long serialVersionUID = 1L;	protected Long id;	private String title;	private String content;	private String account;	private int type;	private String loginIP;	private String logintime;	private String loginArea;	private String diffAreaLogin;	/**	 * 是否是异地登陆	 */	public static final String systemlog_diffAreaLogin_y = "y";// 是	public static final String systemlog_diffAreaLogin_n = "n";// 否	public void clear() {		super.clear();		id = null;		title = null;		content = null;		account = null;		type = 0;		loginIP = null;		logintime = null;		loginArea = null;		diffAreaLogin = null;	}	public Long getId() {		return id;	}	public void setId(Long id) {		this.id = id;	}	public String getTitle() {		return title;	}	public void setTitle(String title) {		this.title = title;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public int getType() {		return type;	}	public void setType(int type) {		this.type = type;	}	public String getLoginIP() {		return loginIP;	}	public void setLoginIP(String loginIP) {		this.loginIP = loginIP;	}	public String getLogintime() {		return logintime;	}	public void setLogintime(String logintime) {		this.logintime = logintime;	}	public String getLoginArea() {		return loginArea;	}	public void setLoginArea(String loginArea) {		this.loginArea = loginArea;	}	public String getDiffAreaLogin() {		return diffAreaLogin;	}	public void setDiffAreaLogin(String diffAreaLogin) {		this.diffAreaLogin = diffAreaLogin;	}	public String getAccount() {		return account;	}	public void setAccount(String account) {		this.account = account;	}}