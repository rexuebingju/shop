package com.tuisitang.modules.shop.entity;import java.io.Serializable;import java.util.List;import com.fasterxml.jackson.annotation.JsonIgnore;import com.google.common.collect.Lists;import com.tuisitang.common.cache.memcached.MemcachedObjectType;import com.tuisitang.common.cache.memcached.SpyMemcachedClient;import net.jeeshop.core.dao.QueryModel;/**     * @{#} Catalog.java   *  * 分类 Entity *  * <p>Copyright: Copyright(c) 2013 </p>  * <p>Company: TST</p> * @version 1.0 * @author <a href="mailto:paninxb@gmail.com">panin</a>    */public class Catalog extends QueryModel<Catalog> implements Serializable {	private static final long serialVersionUID = 1L;		/**	 * 是否显示在首页的导航条上	 */	public static final String catalog_showInNav_y = "y";	public static final String catalog_showInNav_n = "n";		/**	 * 是否推荐目录	 */	public static final String CATALOG_RECOMMEND_Y = "y";	public static final String catalog_RECOMMEND_N = "n";	/**	 * type类型	 */	public static final String catalog_type_p = "p";// 商品	public static final String catalog_type_a = "a";// 文章	private Long id;//目录ID	private Long pid; // 父级编号	private String code;	private String name;	private int sort;	private String type;	@JsonIgnore	private String showInNav;	private String isRecommend;//是否推荐	private String icon;// 目录icon	@JsonIgnore	private String showInNavStr = "否";// 是否显示在首页 中文	@JsonIgnore	private String isRecommendStr = "否";// 是否显示在首页 中文		private List<Catalog> children = Lists.newArrayList();	@JsonIgnore	private List<Catalog> recommend = Lists.newArrayList(); //推荐目录	public static final String CACHE_PROPERTY_ID = "id";// 缓存id属性	public static final String CACHE_PROPERTY_TYPE = "type";// 缓存TYPE属性	public static final String CACHE_PROPERTY_PRODUCTID = "productId";// 缓存产品ID属性	public static final String CATALOG_MAIN_CLASS = "0"; //目录主分类	public static final String CATALOG_TYPE_PROD= "p"; //产品目录		public Catalog() {		super();	}	public Catalog(Long id, String name) {		super();		this.id = id;		this.name = name;	}	public Catalog(String type, Long pid) {		super();		this.pid = pid;		this.type = type;	}	public void clear() {		super.clear();		id = null;		name = null;		code = null;		pid = null;		sort = 0;		type = null;		showInNav = null;		showInNavStr = null;	}		public static boolean isMainCatalog(Long catalogId) {		if (Catalog.CATALOG_MAIN_CLASS.equals(catalogId.toString())){			return true;		}		return false;	}	public Long getPid() {		return pid;	}	public Long getId() {		return id;	}	public void setId(Long id) {		this.id = id;	}	public void setPid(Long pid) {		this.pid = pid;	}	public String getType() {		return type;	}	public void setType(String type) {		this.type = type;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public int getSort() {		return sort;	}	public void setSort(int sort) {		this.sort = sort;	}	public String getCode() {		return code;	}	public void setCode(String code) {		this.code = code;	}	public String getShowInNav() {		return showInNav;	}	public void setShowInNav(String showInNav) {		this.showInNav = showInNav;	}		public String getShowInNavStr() {		return "y".equals(showInNav) ? "是" : "否";	}	public String getIsRecommend() {		return isRecommend;	}	public void setIsRecommend(String isRecommend) {		this.isRecommend = isRecommend;	}	public void setShowInNavStr(String showInNavStr) {		this.showInNavStr = showInNavStr;	}		public String getIsRecommendStr() {		return "y".equals(isRecommend) ? "是" : "否";	}	public void setIsRecommendStr(String isRecommendStr) {		this.isRecommendStr = isRecommendStr;	}	public List<Catalog> getChildren() {		return children;	}	public void setChildren(List<Catalog> children) {		this.children = children;	}		public List<Catalog> getRecommend() {		return recommend;	}	public void setRecommend(List<Catalog> recommend) {		this.recommend = recommend;	}	public String getIcon() {		return icon;	}	public void setIcon(String icon) {		this.icon = icon;	}	/**	 * 根据id获得memcached key	 * 	 * @param id	 * @return	 */	@JsonIgnore	public static String getKey(Long id) {		return MemcachedObjectType.Catalog.getPrefix() + Catalog.CACHE_PROPERTY_ID + SpyMemcachedClient.SEP + id;	}		/**	 * 根据产品Id获得memcached key	 * 	 * @param id	 * @return	 */	@JsonIgnore	public static String getProductKey(Long productId) {		return MemcachedObjectType.Catalog.getPrefix() + Catalog.CACHE_PROPERTY_PRODUCTID + SpyMemcachedClient.SEP + productId;	}		/**	 * 获取所有目录key	 * 	 * @param id	 * @return	 */	@JsonIgnore	public static String getCatalogTypeKey(String type) {		return MemcachedObjectType.CATALOG_TYPE_LIST.getPrefix() + Catalog.CACHE_PROPERTY_TYPE + SpyMemcachedClient.SEP + type;	}		/**	 * 获取目录子目录key	 * 	 * @param id	 * @return	 */	@JsonIgnore	public static String getSubCatalogKey(Long id) {		return MemcachedObjectType.CATALOG_SUB_LIST.getPrefix() + Catalog.CACHE_PROPERTY_TYPE + SpyMemcachedClient.SEP + id;	}	}