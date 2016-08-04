package com.tuisitang.modules.shop.entity;import java.io.Serializable;import java.util.Date;import net.jeeshop.core.dao.page.PagerModel;/**     * @{#} Sms.java *  * <p>Copyright: Copyright(c) 2013 </p>  * <p>Company: TST</p> * @version 1.0 * @author <a href="mailto:paninxb@gmail.com">panin</a>    */public class Sms extends PagerModel<Sms> implements Serializable {	private static final long serialVersionUID = 1L;		public static final String STATUS_SUCCESS = "0";// ok:正常发送	public static final String STATUS_EXCEPTION = "1";// 异常	protected Long id;	private String mobile;	private String type;// 0 注册 1 登录 2 忘记密码 3 修改密码 4 手机修改确认 5 重新绑定手机号	private String platform;// 短信平台类型：CL 创蓝	private String content;// 短信发送内容	private Date createTime;// 短信创建时间	private Date sendTime;// 短信发送时间	private String status;// 系统发送状态 1 ex:异常, 0 ok:正常发送。	private String returnCode;// 短信平台返回代码	private String returnMsg;// 短信平台返回消息	public Sms() {		super();	}		public Sms(String mobile, String type, String platform, String content,			Date createTime, String status, String returnCode, String returnMsg) {		super();		this.mobile = mobile;		this.type = type;		this.platform = platform;		this.content = content;		this.createTime = createTime;		this.status = status;		this.returnCode = returnCode;		this.returnMsg = returnMsg;	}	public Sms(String mobile, String type, String platform, String content,			Date createTime, Date sendTime, String status, String returnCode,			String returnMsg) {		super();		this.mobile = mobile;		this.type = type;		this.platform = platform;		this.content = content;		this.createTime = createTime;		this.sendTime = sendTime;		this.status = status;		this.returnCode = returnCode;		this.returnMsg = returnMsg;	}	public Long getId() {		return id;	}	public void setId(Long id) {		this.id = id;	}	public String getMobile() {		return mobile;	}	public void setMobile(String mobile) {		this.mobile = mobile;	}	public String getType() {		return type;	}	public void setType(String type) {		this.type = type;	}	public String getPlatform() {		return platform;	}	public void setPlatform(String platform) {		this.platform = platform;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public Date getCreateTime() {		return createTime;	}	public void setCreateTime(Date createTime) {		this.createTime = createTime;	}	public Date getSendTime() {		return sendTime;	}	public void setSendTime(Date sendTime) {		this.sendTime = sendTime;	}	public String getStatus() {		return status;	}	public void setStatus(String status) {		this.status = status;	}	public String getReturnCode() {		return returnCode;	}	public void setReturnCode(String returnCode) {		this.returnCode = returnCode;	}	public String getReturnMsg() {		return returnMsg;	}	public void setReturnMsg(String returnMsg) {		this.returnMsg = returnMsg;	}}