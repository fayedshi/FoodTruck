package quiz.cosmos.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	
	
	// can process bean definition
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory fac) throws BeansException {
		// TODO Auto-generated method stub
		
		String[] defNames= fac.getBeanDefinitionNames();
		for(String nm: defNames) {
			//System.out.println(" def name "+ nm);	
			BeanDefinition bd = fac.getBeanDefinition(nm);
			//System.out.println(bd.getPropertyValues().toString());
		}
		System.out.println(" testing postProcessBeanFactory()");
	}
	

}
