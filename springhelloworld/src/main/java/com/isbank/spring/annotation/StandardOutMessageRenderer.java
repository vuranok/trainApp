package com.isbank.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MessageRenderer")
public class StandardOutMessageRenderer implements MessageRenderer {

	private MessageProvider messageProvider;

	public void render() {
		
		if(messageProvider == null)
			throw new RuntimeException("You must specify MessageProvider of the class " + StandardOutMessageRenderer.class.getName());

		System.out.println(messageProvider.getMessage());
	}

	/*
	 * Instead of @Autowired, you can also use @Resource(name="messageProvider") to achieve the same result.
			@Resource is one of the annotations in the JSR-250 standard that defines a common set of Java annotations for use
			on both JSE and JEE platforms. Different from @Autowired, the @Resource annotation supports the name parameter for
			more fine-grained DI requirements. Additionally, Spring supports use of the @Inject annotation introduced as part of
			JSR-299, “Contexts and Dependency Injection for the Java EE Platform” (CDI). @Inject is equivalent in behavior to
			Spring’s @Autowired annotation.
	 */
	
	/*
	 * (non-Javadoc)
	 * @see com.isbank.spring.annotation.MessageRenderer#setMessageProvider(com.isbank.spring.annotation.MessageProvider)
	 */
	@Autowired
	public void setMessageProvider(MessageProvider messageProvider) {
		
		this.messageProvider = messageProvider;

	}

	public MessageProvider getMessageProvider() {
		return messageProvider;
	}

}
