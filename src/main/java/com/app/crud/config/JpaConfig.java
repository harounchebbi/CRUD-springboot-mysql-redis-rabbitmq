package com.app.crud.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.app.crud.repository")
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class}, basePackages = {"com.app.crud.entity"})
public class JpaConfig {

}
