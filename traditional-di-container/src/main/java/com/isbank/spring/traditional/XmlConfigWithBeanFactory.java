package com.isbank.spring.traditional;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class XmlConfigWithBeanFactory {

	public static void main(String[] args) {
		
		/*
		 * DefaultListableBeanFactory: One of the two main BeanFactory implementations supplied with Spring.
		 * (Other is ClassPathXmlApplicationContext)
		 */
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		
		/*
		 * XmlBeanDefinitionReader: Reading bean definition information from an XML file
		 * PropertiesBeanDefinitionReader: allows you to manage your bean configuration by using properties rather than XML
		 */
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		
		reader.loadBeanDefinitions(new ClassPathResource("META-INF/spring/app-context.xml"));

		BoardGame game = (BoardGame) factory.getBean("boardGame");
		
		System.out.println(game.getHistory());
		
	}

}
