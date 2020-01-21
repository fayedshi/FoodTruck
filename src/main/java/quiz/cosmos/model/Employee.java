package quiz.cosmos.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Employee implements BeanFactoryAware, BeanNameAware, ApplicationContextAware, DisposableBean {
	private String name;
	private String dept;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		
		System.out.println("to set bean name "+name);
		
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		this.setApplicationContext(arg0);
		
	}
	
	public String toString() {
		return "printing Employee";
	}

}
