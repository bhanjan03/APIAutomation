package com.apiautomationframework.config;

import org.aeonbits.owner.ConfigCache;


public class ConfigFactory {
	public static FrameworkConfig getConfig() {
	    return ConfigCache.getOrCreate(FrameworkConfig.class);
	  }

}
