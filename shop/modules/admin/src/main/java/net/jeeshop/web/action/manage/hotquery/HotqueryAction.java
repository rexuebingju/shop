package net.jeeshop.web.action.manage.hotquery;import net.jeeshop.core.BaseAction;import net.jeeshop.services.manage.hotquery.HotqueryService;import com.tuisitang.modules.shop.entity.Hotquery;public class HotqueryAction extends BaseAction<Hotquery> {	private static final long serialVersionUID = 1L;	private HotqueryService hotqueryService;	public HotqueryService getHotqueryService() {		return hotqueryService;	}	protected void selectListAfter() {		pager.setPagerUrl("hotquery!selectList.action");	}	public void setHotqueryService(HotqueryService hotqueryService) {		this.hotqueryService = hotqueryService;	}	public Hotquery getE() {		return this.e;	}	public void prepare() throws Exception {		if (this.e == null) {			this.e = new Hotquery();		}	}	public void insertAfter(Hotquery e) {		e.clear();	}}