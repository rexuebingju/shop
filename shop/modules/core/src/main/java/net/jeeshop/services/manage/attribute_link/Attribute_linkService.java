package net.jeeshop.services.manage.attribute_link;import net.jeeshop.core.Services;import com.tuisitang.modules.shop.entity.AttributeLink;public interface Attribute_linkService extends Services<AttributeLink> {	/**	 * @param oldAttr	 */	int deleteByCondition(AttributeLink oldAttr);}