package idv.hsiehpinghan.springcloudconfigclientboot.utility;

import idv.hsiehpinghan.springcloudconfigclientboot.config.ConfigServerConfig;
import idv.hsiehpinghan.springcloudconfigclientboot.criteria.CrudCriteria;
import idv.hsiehpinghan.springcloudconfigclientboot.entity.CrudEntity;

public class ConvertUtility {
	public static CrudEntity convertToCrudEntity(CrudCriteria criteria, ConfigServerConfig configServerConfig) {
		Integer id = criteria.getId();
		String string = criteria.getString();
		String configurationProperty = configServerConfig.getConfigurationProperty();
		CrudEntity entity = new CrudEntity(id, string, configurationProperty);
		return entity;
	}
}
