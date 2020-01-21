package quiz.cosmos.interceptor;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("before target method:" + method + "\n");
		Object object = methodProxy.invokeSuper(o, objects);
		System.out.println("after target method:" + method + "\n");
		return object;
	}
}
