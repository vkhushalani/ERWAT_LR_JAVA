package com.erwat.lr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import com.erwat.lr.model.CaseTypes;

@Configuration
public class RespositoryConfig extends RepositoryRestConfigurerAdapter {
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(CaseTypes.class);
    }
}
