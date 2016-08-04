package net.jeeshop.web.action.front.cart;import java.io.IOException;import java.text.DecimalFormat;import java.util.HashMap;import java.util.Iterator;import java.util.LinkedList;import java.util.List;import java.util.Map;import net.jeeshop.core.BaseAction;import net.jeeshop.core.FrontContainer;import net.jeeshop.core.front.SystemManager;import net.jeeshop.services.front.account.bean.Account;import net.jeeshop.services.front.address.AddressService;import net.jeeshop.services.front.product.ProductService;import net.jeeshop.services.front.product.bean.Product;import net.jeeshop.services.front.product.bean.ProductStockInfo;import net.jeeshop.services.front.questionnaireResult.bean.QuestionnaireResult;import net.jeeshop.services.manage.activity.bean.Activity;import net.jeeshop.services.manage.spec.SpecService;import net.jeeshop.services.manage.spec.bean.Spec;import net.jeeshop.web.action.front.orders.CartInfo;import org.apache.commons.lang3.StringUtils;import org.apache.log4j.Logger;import com.alibaba.fastjson.JSON;/** * 购物车 *  * @author huangf *  */public class CartAction extends BaseAction<CartInfo> {	private static final long serialVersionUID = 1L;	private static final Logger logger = Logger.getLogger(CartAction.class);	private ProductService productService;	private AddressService addressService;	private SpecService specService;	public SpecService getSpecService() {		return specService;	}	public void setSpecService(SpecService specService) {		this.specService = specService;	}	public void setAddressService(AddressService addressService) {		this.addressService = addressService;	}	public void setProductService(ProductService productService) {		this.productService = productService;	}	protected void selectListAfter() {		pager.setPagerUrl("cart!selectList.action");	}	public CartInfo getE() {		return this.e;	}	public void prepare() throws Exception {		if (this.e == null) {			this.e = new CartInfo();		}	}	public void insertAfter(QuestionnaireResult e) {		e.clear();	}	@Override	public void insertAfter(CartInfo e) {	}		/**	 * 查看购物车	 * @return	 */	public String cart(){		logger.error("AccountAction.cart查看购物车...");//		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);//		if (acc == null || StringUtils.isBlank(acc.getAccount())) {//			return "toLogin";//		}				super.setSelectMenu(FrontContainer.not_select_menu);//设置主菜单为不选中		return "cart";		//		String addCart = getRequest().getParameter("addCart");//		CartInfo cartInfo = (CartInfo) getSession().getAttribute(FrontContainer.myCart);//		if(cartInfo==null){//			cartInfo = new CartInfo();//		}//		getSession().setAttribute(FrontContainer.myCart,cartInfo);//		logger.error("===addCart="+(addCart!=null && addCart.equals("1")));//		if(addCart!=null && addCart.equals("1")){//			String productID = getE().getId();//			if(StringUtils.isEmpty(productID)){//				//查询购物车//				return "cart";//			}//			//			if(cartInfo==null){//				cartInfo = new CartInfo();//				getSession().setAttribute(FrontContainer.myCart, cartInfo);//			}//			int inputBuyNum = Integer.valueOf(getRequest().getParameter("inputBuyNum"));//			if(inputBuyNum<=0){//				throw new NullPointerException();//			}//			//检查指定的产品是否已购买到购物车，购买了则数量++，否则查询后添加到购物车//			boolean exists = false;//			for(int i=0;i<cartInfo.getProductList().size();i++){//				Product p = cartInfo.getProductList().get(i);//				if(p.getId().equals(productID)){//					p.setBuyCount(p.getBuyCount()+inputBuyNum);//					exists = true;//				}//			}//			if(!exists){//				//添加产品到购物车//				System.out.println("id="+productID);////				getE().clear();//				Product pp = new Product();//				pp.setId(productID);//				pp.setStatus(1);//				pp = productService.selectOne(pp);//				if(pp==null){//					throw new NullPointerException("根据商品ID="+productID+"查询不到指定的商品信息。");//				}//				pp.setBuyCount(inputBuyNum);//				//				cartInfo.getProductList().add(pp);//			}//			cartInfo.setAmount(cartInfo.cacl());//			getE().clear();//			//		}//		//		//加载配送信息//		Address add = new Address();//		add.setAccount(acc.getAccount());//		List<Address> addressList = addressService.selectList(add);//		cartInfo.setAddressList(addressList);//		if(addressList!=null && addressList.size()>0){////			boolean exist = false;//			for(int i=0;i<addressList.size();i++){//				Address addItem = addressList.get(i);//				if(StringUtils.isNotBlank(addItem.getIsdefault()) && addItem.getIsdefault().equals("y")){//					cartInfo.setDefaultAddessID(addItem.getId());//					break;//				}//			}//		}//		logger.error("cartInfo="+cartInfo);//		return "cart";	}		/**	 * 从购物车中删除指定的产品	 * @return	 */	public String delete(){		String id = getRequest().getParameter("id");		if(StringUtils.isBlank(id)){			throw new NullPointerException("非法请求！");		}				CartInfo cartInfo = (CartInfo) getSession().getAttribute(FrontContainer.myCart);		if(cartInfo==null){			//会话超时，转到登陆页面			return "toLogin";		}				for(Iterator<Product> it = cartInfo.getProductList().iterator();it.hasNext();){			Product p = it.next();			if(p.getId().equals(id)){				it.remove();								//重新计算总支付金额//				cartInfo.setAmount(cartInfo.totalCacl());				cartInfo.totalCacl();				break;			}		}		return "cart";	}	DecimalFormat df = new DecimalFormat("0.00");	/**	 * 加入购物车，不对金额进行任何的运算。金额的运算在方法CartAction.notifyCart	 * @return	 * @throws IOException 	 */	public String addToCart() throws IOException{//		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);//		if (acc == null || StringUtils.isBlank(acc.getAccount())) {//			super.write("1");//需要登录//			return null;//		}				logger.error("ProductAction.cart...");		Long productID = new Long(getRequest().getParameter("productID"));//商品ID		int buyCount = Integer.valueOf(getRequest().getParameter("buyCount"));//购买数量		String buySpecID = getRequest().getParameter("buySpecID");//选中的规格ID		if(productID == null || buyCount<=0){			throw new NullPointerException("参数错误！");		}				/**		 * 检查内存库存是否已超卖，如果超库存不足，则提醒用户		 */		ProductStockInfo momeryStockInfo = SystemManager.productStockMap.get(productID);		if(momeryStockInfo==null){			String jsonError = JSON.toJSONString(new StockErrorProduct(productID,"很抱歉，商品已下架暂时无法购买！"));			logger.error("jsonError="+jsonError);			super.write(jsonError);			return null;		}						CartInfo cartInfo = (CartInfo) getSession().getAttribute(FrontContainer.myCart);		if(cartInfo==null){			cartInfo = new CartInfo();		}		getSession().setAttribute(FrontContainer.myCart,cartInfo);				//检查指定的产品是否已购买到购物车，购买了则数量++，否则查询后添加到购物车		boolean exists = false;		for(int i=0;i<cartInfo.getProductList().size();i++){			Product p = cartInfo.getProductList().get(i);			if(p.getId().equals(productID)){				p.setBuyCount(p.getBuyCount()+buyCount);				logger.error("商品已购买，只对数量进行++，总数="+p.getBuyCount());								if(p.getExchangeScore() > 0){					p.setTotal0("0.00");					p.setTotalExchangeScore(p.getBuyCount() * p.getExchangeScore());				}else{					p.setTotal0(df.format(p.getBuyCount() * Double.valueOf(p.getNowPrice())));				}				exists = true;			}		}				super.utf8JSON();				//如果购物车中部存在此商品，则添加到购物车		if(!exists){			Product product = new Product();			product.setId(new Long(productID));//			product.setStatus(1);			product = productService.selectOne(product);			if(product==null){				throw new NullPointerException();			}						/**			 * 如果此商品为促销活动的商品，则按照活动规则计算商品金额			 */			if(product.getActivityID() != null){				Activity activity = SystemManager.activityMap.get(product.getActivityID());				if(activity==null){					String jsonError = JSON.toJSONString(new StockErrorProduct(productID,"活动可能已下架！"));					logger.error("jsonError="+jsonError);					super.write(jsonError);//活动可能已下架!					return null;				}else if(activity.checkActivity()){					String jsonError = JSON.toJSONString(new StockErrorProduct(productID,"活动已结束！"));					logger.error("jsonError="+jsonError);					super.write(jsonError);//活动已结束！					return null;				}								//检查是否符合此活动的会员等级范围				Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);				if(acc==null){					String jsonError = JSON.toJSONString(new StockErrorProduct(productID,"此商品为促销活动的商品，请先登陆才能购买！！"));					logger.error("jsonError="+jsonError);					super.write(jsonError);					return null;				}				logger.error("acc.getRank() = " + acc.getRank());				logger.error("activity.getAccountRange() = " + activity.getAccountRange());								if(activity.getAccountRange().indexOf(acc.getRank()) < 0){					String jsonError = JSON.toJSONString(new StockErrorProduct(productID,"您的会员等级不在此活动的购买范围内！"));					logger.error("jsonError="+jsonError);					super.write(jsonError);					return null;				}								//计算活动商品的最终结算价				product.setNowPrice(product.caclFinalPrice());								//判断如果是积分商品，则计算所需的积分				if(activity.getActivityType().equals(Activity.activity_activityType_j)){					product.setExchangeScore(activity.getExchangeScore());				}			}						product.setBuyCount(buyCount);						/**			 * 加载指定商品的规格信息			 */			if(StringUtils.isNotBlank(buySpecID)){				Spec spec = specService.selectById(new Long(buySpecID));				if(spec==null){					throw new NullPointerException("根据指定的规格"+buySpecID+"查询不到任何数据!");				}				product.setBuySpecInfo(spec);								//减少以后的逻辑判断，规格的价格等同于商品的价格				product.setNowPrice(spec.getSpecPrice());			}						if(product.getExchangeScore()==0){				product.setTotal0(df.format(product.getBuyCount() * Double.valueOf(product.getNowPrice())));			}else{				product.setTotalExchangeScore(product.getBuyCount() * product.getExchangeScore());				product.setTotal0("0.00");			}						cartInfo.getProductList().add(product);			logger.error("添加商品到购物车!商品ID="+product.getId());		}		cartInfo.totalCacl();//计算购物车中商品总金额		e.clear();				super.write("0");//成功添加商品到购物车				logger.error("cartInfo="+cartInfo);		return null;	}		/**	 * 通知购物车+-商品，然后计算出总金额返回。	 * @return	 * @throws IOException 	 */	public String notifyCart() throws IOException{		int currentBuyNumber = Integer.valueOf(getRequest().getParameter("currentBuyNumber"));//当前购买的商品的数量		String productID = getRequest().getParameter("productID");//+-的商品ID		logger.error("currentBuyNumber="+currentBuyNumber+",productID="+productID);				if (StringUtils.isBlank(productID) || currentBuyNumber<=0) {			throw new NullPointerException("非法请求!");		}				CartInfo cartInfo = (CartInfo) getSession().getAttribute(FrontContainer.myCart);		if(cartInfo==null || cartInfo.getProductList()==null || cartInfo.getProductList().size()==0){			//购物车失效，转到登陆页面			getResponse().sendRedirect("/user/login.html");		}		//		String msg = null;		CartProductInfo cartProductInfo = new CartProductInfo();				/**		 * 检查购买的商品是否超出库存数		 */		ProductStockInfo stockInfo = SystemManager.productStockMap.get(productID);		if(stockInfo==null){			//商品已卖完或已下架，请联系站点管理员!			logger.error("商品已卖完或已下架，请联系站点管理员或从购物车中删除!");			super.utf8JSON();			cartProductInfo.code = "notThisProduct";			cartProductInfo.msg = "商品已卖完或已下架，请联系站点管理员或从购物车中删除!";			super.write(JSON.toJSONString(cartProductInfo));//			super.write("{\"code\":\"notThisProduct\",\"msg\":\"商品已卖完或已下架，请联系站点管理员或从购物车中删除!\"}");			return null;		} else if(stockInfo.getActivityID() == null){			/**			 * 购买的是活动促销的商品，则检查是否超出购买的最大数量			 */			Activity activity = SystemManager.activityMap.get(stockInfo.getActivityID());			if(activity==null || activity.getStatus().equals(Activity.activity_status_n)){				cartProductInfo.code = "buyMore";				cartProductInfo.msg = "此商品为促销活动的商品，最多只能购买" + activity.getMaxSellCount()+"件";				super.write(JSON.toJSONString(cartProductInfo));				return null;			}else if(activity.getMaxSellCount() != 0 && currentBuyNumber > activity.getMaxSellCount()){//				String msg0 = "此商品为促销活动的商品，最多只能购买" + activity.getMaxSellCount()+"件";//				msg = "{\"code\":\"buyMore\",\"msg\":\"" + msg0 + "\",\"maxStock\":\""+stockInfo.getStock()+"\"}";								cartProductInfo.code = "buyMore";				cartProductInfo.msg = "此商品为促销活动的商品，最多只能购买" + activity.getMaxSellCount()+"件";				super.write(JSON.toJSONString(cartProductInfo));				return null;			}			//			if(activity.getActivityType().equals(Activity.activity_activityType_j)){////				currentBuyNumber * activity.getExchangeScore()//				activity.sete//			}		}else{			if(stockInfo.getStock() < currentBuyNumber){				//购买的商品数超出库存数，则自动当最大库存数计算				currentBuyNumber = stockInfo.getStock();				//				String msg0 = "最多只能买"+stockInfo.getStock()+"件";//				msg = "{\"code\":\"buyMore\",\"msg\":\""+msg0+"\",\"maxStock\":\""+stockInfo.getStock()+"\"}";								cartProductInfo.code = "buyMore";				cartProductInfo.msg = "最多只能买"+stockInfo.getStock()+"件";				super.write(JSON.toJSONString(cartProductInfo));				return null;			}		}				/**		 * 计算出点击+-的这一个商品的一些信息：小计、所需积分		 *///		if(msg==null){			for(int i=0;i<cartInfo.getProductList().size();i++){				Product pro = cartInfo.getProductList().get(i);				if(pro.getId().equals(productID)){					pro.setBuyCount(currentBuyNumber);//设置指定商品购买的数量										cartInfo.totalCacl();//计算购物车中商品总金额					//					msg = "{\"code\":\"ok\",\"amount\":\""+cartInfo.getAmount()+"\"}";										if(pro.getExchangeScore()==0){						pro.setTotal0(df.format(Double.valueOf(pro.getNowPrice()) * pro.getBuyCount()));					}else{						pro.setTotal0("0.00");						pro.setTotalExchangeScore(pro.getBuyCount() * pro.getExchangeScore());					}										cartProductInfo.code = "ok";					cartProductInfo.total0 = pro.getTotal0();					cartProductInfo.amount = cartInfo.getAmount();					cartProductInfo.totalExchangeScore = pro.getBuyCount() * pro.getExchangeScore();					cartProductInfo.amountExchangeScore = cartInfo.getTotalExchangeScore();//					cartProductInfo.msg = "{\"code\":\"ok\",\"amount\":\""+cartInfo.getAmount()+"\"}";					break;				}			}//		}		super.utf8JSON();		super.write(JSON.toJSONString(cartProductInfo));		return null;	}		public static void main(String[] args) {		Map<String, String> map = new HashMap<String, String>();		map.put("code", "ok");		map.put("monery", "33");		System.out.println(JSON.toJSONString(map));	}		/**	 * 正式转到支付之前的最后一次检查库存	 * 此方法也可以用于批量错误消息检查，比如在购物车商品列表页面，提交到支付页面的时候进行批量检查（所有商品是否都有货、是否存在超卖、是否已下架、是否活动已结束（未在指定时间内进行支付，且活动已结束））	 * @return	 * @throws IOException 	 */	public String checkStockLastTime() throws IOException{				logger.error("checkStockLastTime...");		Account acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);		if(acc==null){//			throw new NullPointerException("请先登陆！");			logger.error("提示用户需要登录...");			super.utf8JSON();			super.write("-1");//提示用户需要登录			return null;					}						StockErrorProductReturn result = new StockErrorProductReturn();		CartInfo cartInfo = (CartInfo)getSession().getAttribute(FrontContainer.myCart);		if(cartInfo==null){			logger.error("login..");			//session超时，转到登陆页面，让用户重新登陆下单，上次未支付的单子只能找不到了。			result.code = "login";			super.utf8JSON();			super.write(JSON.toJSONString(result).toString());			return null;		}				result.code = "result";		List<StockErrorProduct> list = new LinkedList<CartAction.StockErrorProduct>();		for(int i=0;i<cartInfo.getProductList().size();i++){			Product pro = cartInfo.getProductList().get(i);			ProductStockInfo stockInfo = SystemManager.productStockMap.get(pro.getId());			if(stockInfo!=null){				if(stockInfo.getActivityID() != null){					/**					 * 购买的是活动促销的商品，则检查是否超出购买的最大数量					 */					Activity activity = SystemManager.activityMap.get(stockInfo.getActivityID());					if(activity.getMaxSellCount() != 0 && pro.getBuyCount() > activity.getMaxSellCount()){						String msg0 = "此商品为促销活动的商品，最多只能购买" + activity.getMaxSellCount()+"件";//						msg = "{\"code\":\"buyMore\",\"msg\":\"" + msg0 + "\",\"maxStock\":\""+stockInfo.getStock()+"\"}";						list.add(new StockErrorProduct(pro.getId(),msg0));					}										//如果商品为需要积分兑换的，则检查用户账户上的积分是否足够					if(false){						acc = (Account) getSession().getAttribute(FrontContainer.USER_INFO);						if(acc==null){							throw new NullPointerException("请先登陆！");						}												//积分不足的错误提示						if(acc.getScore() < activity.getExchangeScore() * pro.getBuyCount()){							list.add(new StockErrorProduct(pro.getId(),"此商品总共所需积分："+activity.getExchangeScore() * pro.getBuyCount() + "点，可惜您目前只有"+acc.getScore()+"积分"));						}					}				}else{					if(stockInfo.getStock()<pro.getBuyCount()){						//购物车中购买的商品超出库存数						list.add(new StockErrorProduct(pro.getId(),"最多只能购买"+stockInfo.getStock()+"个！"));					}					//					if(stockInfo.getStock() < currentBuyNumber){//						//购买的商品数超出库存数，则自动当最大库存数计算//						currentBuyNumber = stockInfo.getStock();//						//						String msg0 = "最多只能买"+stockInfo.getStock()+"件";//						msg = "{\"code\":\"buyMore\",\"msg\":\""+msg0+"\",\"maxStock\":\""+stockInfo.getStock()+"\"}";//					}				}							}else{				//商品可能已经下架或售完！				list.add(new StockErrorProduct(pro.getId(),"商品可能已经下架或售完！"));			}		}				//检查积分是否足够支付此订单消耗的积分				//积分不足的错误提示		if(acc.getScore() < cartInfo.getTotalExchangeScore()){			result.error = "总共所需积分：" + cartInfo.getTotalExchangeScore() + ",可惜您仅有积分：" + acc.getScore();		}				if(list!=null && list.size()>0){			result.list = list;		}				cartInfo.totalCacl();//计算购物车中商品总金额				super.utf8JSON();		super.write(JSON.toJSONString(result).toString());//		logger.error("checkStockLastTime..."+JSON.toJSONString(result).toString());		return null;	}		/**	 * 库存检查返回的错误对象	 * @author huangf	 *	 */	class StockErrorProductReturn{		String code;		String error;//错误消息，显示到提交按钮边上		List<StockErrorProduct> list;				public String getCode() {			return code;		}		public void setCode(String code) {			this.code = code;		}		public List<StockErrorProduct> getList() {			return list;		}		public void setList(List<StockErrorProduct> list) {			this.list = list;		}		public String getError() {			return error;		}		public void setError(String error) {			this.error = error;		}	}		/**	 * 库存检查错误消息对象	 */	class StockErrorProduct{		Long id;//商品ID		String tips;//错误消息				public StockErrorProduct(){}				public StockErrorProduct(Long id,String tips){			this.id = id;			this.tips = tips;		}				public Long getId() {			return id;		}				public void setId(Long id) {			this.id = id;		}				public String getTips() {			return tips;		}				public void setTips(String tips) {			this.tips = tips;		}	}		/**	 * 购物车页面，单个商品返回的信息对象	 */	class CartProductInfo {		String code;//返回代码		String msg;//返回消息		String total0;//小计金额		String amount;//总计金额		int totalExchangeScore;//兑换此商品所需总积分		int amountExchangeScore;//积分汇总				public String getCode() {			return code;		}		public void setCode(String code) {			this.code = code;		}		public String getMsg() {			return msg;		}		public void setMsg(String msg) {			this.msg = msg;		}		public String getTotal0() {			return total0;		}		public void setTotal0(String total0) {			this.total0 = total0;		}		public String getAmount() {			return amount;		}		public void setAmount(String amount) {			this.amount = amount;		}		public int getTotalExchangeScore() {			return totalExchangeScore;		}		public void setTotalExchangeScore(int totalExchangeScore) {			this.totalExchangeScore = totalExchangeScore;		}		public int getAmountExchangeScore() {			return amountExchangeScore;		}		public void setAmountExchangeScore(int amountExchangeScore) {			this.amountExchangeScore = amountExchangeScore;		}			}}