package net.jeeshop.services.front.keyvalue.bean;import java.io.Serializable;/**     * @{#} Keyvalue.java   *  * Keyvalue Entity *  * <p>Copyright: Copyright(c) 2013 </p>  * <p>Company: TST</p> * @version 1.0 * @author <a href="mailto:paninxb@gmail.com">panin</a>    */public class Keyvalue extends com.tuisitang.modules.shop.entity.Keyvalue implements Serializable {	private static final long serialVersionUID = 1L;	private String keyword;	private String value;	public void clear() {		super.clear();		id = null;		keyword = null;		value = null;	}	public String getKeyword() {		return keyword;	}	public void setKeyword(String keyword) {		this.keyword = keyword;	}	public String getValue() {		return value;	}	public void setValue(String value) {		this.value = value;	}}