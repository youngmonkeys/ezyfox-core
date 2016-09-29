package com.tvd12.ezyfox.sfs2x.testing.extensionconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.tvd12.ezyfox.core.annotation.PackagesScan;
import com.tvd12.ezyfox.core.config.AppExtensionConfigurationImpl;

@Configuration
@ComponentScan({"com.lagente.sfs2x"})
@PackagesScan(packages = {"com.lagente.sfs2x.testing.extensionconfig"})
public class TestAppConfig {
	
	@Bean
	public AppExtensionConfigurationImpl extensionConfig() {
		return new AppExtensionConfigurationImpl();
	}
}
