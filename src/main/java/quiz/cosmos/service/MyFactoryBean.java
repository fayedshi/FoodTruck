package quiz.cosmos.service;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.FactoryBean;


public class MyFactoryBean<T> implements FactoryBean<T> {

	@Override
	public T getObject() throws Exception {
		ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ptype.getActualTypeArguments()[0];
        return (T) clazz.newInstance();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getObjectType() {
		ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) ptype.getActualTypeArguments()[0];
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
