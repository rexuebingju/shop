package net.jeeshop.services.front.address.bean;import java.io.Serializable;import java.util.List;import com.tuisitang.modules.shop.entity.Area;public class Address extends com.tuisitang.modules.shop.entity.Address implements		Serializable {	private static final long serialVersionUID = 1L;	private List<Area> areaList;// 区域列表	public void clear() {		super.clear();	}	public List<Area> getAreaList() {		return areaList;	}	public void setAreaList(List<Area> areaList) {		this.areaList = areaList;	}}