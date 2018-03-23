package pl.zaprogramuj.spring.boot.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import pl.zaprogramuj.spring.boot.webapp.service.SystemPropertiesService;

@Service("systemPropertiesService")
@PropertySource(value = { "classpath:system.properties" })
public class SystemPropertiesServiceImpl implements SystemPropertiesService {

	@Autowired
	private Environment environment;

	@Override
	public String getSystemVersion() {
		String systemFullVersion = environment.getRequiredProperty("system.version");

		if (systemFullVersion.indexOf('-') >= 0) {
			return systemFullVersion.substring(0, systemFullVersion.indexOf('-'));
		}

		return systemFullVersion;
	}
}