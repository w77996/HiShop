package com.hishop.test;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;

public class Test {
	private ApplicationContext applicationContext;
	@Before
	public void init() {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
	}
	//redis集群
		@org.junit.Test
		public void testJedisCluster() {
		JedisCluster jedisCluster = (JedisCluster) applicationContext
						.getBean("jedisCluster");
				
				jedisCluster.set("name", "zhangsan");
				String value = jedisCluster.get("name");
				System.out.println(value);
		}
}
