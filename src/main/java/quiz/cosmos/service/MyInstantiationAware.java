package quiz.cosmos.service;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
// all beans will go thru this layer

import quiz.cosmos.interceptor.MyMethodInterceptor;
import quiz.cosmos.model.Employee;

@Component
public class MyInstantiationAware implements InstantiationAwareBeanPostProcessor, Ordered  {

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		// TODO Auto-generated method stub
		//System.out.println("postProcessAfterInitialization executed"+ arg0);
		
		
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		//System.out.println("postProcessBeforeInitialization executed"+ arg0);
		
		if(bean instanceof Employee) {
			System.out.println("postProcessBeforeInitialization in MyInstantiationAware for employee "+ beanName);
		}
		return bean;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String arg1) throws BeansException {
		// TODO Auto-generated method stub
		if(bean instanceof Employee) {
			//System.out.println("postProcessBeforeInitialization in MyInstantiationAware for employee "+ beanName);
			
		}
		
		return true;
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> className, String arg1) throws BeansException {
		// TODO Auto-generated method stub
		//System.out.println("postProcessBeforeInstantiation executed" + arg0);
		if(className == Employee.class) {
			System.out.println("postProcessBeforeInstantiation run "+ arg1+" classname: "+className);
			Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(className);
            enhancer.setCallback(new MyMethodInterceptor());
			Employee emp = (Employee) enhancer.create();
            return emp;
		}
		return null;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues arg0, PropertyDescriptor[] arg1, Object bean,
			String arg3) throws BeansException {
		// TODO Auto-generated method stub
		if(bean instanceof Employee) {
			((Employee) bean).setDept("PoleDancing");
		}
		return arg0;
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}