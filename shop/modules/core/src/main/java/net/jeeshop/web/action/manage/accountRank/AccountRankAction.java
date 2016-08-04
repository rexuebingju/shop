package net.jeeshop.web.action.manage.accountRank;import net.jeeshop.core.BaseAction;import net.jeeshop.core.ManageContainer;import net.jeeshop.core.exception.NotThisMethod;import net.jeeshop.services.manage.accountRank.AccountRankService;import com.tuisitang.modules.shop.entity.AccountRank;/** * 会员等级 * @author jqsl2012@163.com * */public class AccountRankAction extends BaseAction<AccountRank> {	private static final long serialVersionUID = 1L;	private AccountRankService accountRankService;	public AccountRankService getAccountRankService() {		return accountRankService;	}	protected void selectListAfter() {		pager.setPagerUrl("accountRank!selectList.action");	}	public void setAccountRankService(AccountRankService accountRankService) {		this.accountRankService = accountRankService;	}	public AccountRank getE() {		return this.e;	}	public void prepare() throws Exception {		if (this.e == null) {			this.e = new AccountRank();		}	}	public void insertAfter(AccountRank e) {		e.clear();	}	/**	 * 不支持此方法	 */	@Override	public String insert() throws Exception {		throw new NotThisMethod(ManageContainer.not_this_method);	}	/**	 * 不支持此方法	 */	@Override	public String deletes() throws Exception {		throw new NotThisMethod(ManageContainer.not_this_method);	}}