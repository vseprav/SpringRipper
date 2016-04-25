package main.java.quoters;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrii.peliak on 4/15/2016.
 */
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

    private Map<String,Class> classMap = new HashMap<>();



    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = bean.getClass();
        if(beanClass.isAnnotationPresent(Profiling.class)){
            classMap.put(beanName,beanClass);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class beanClass = classMap.get(beanName);
        if(beanClass != null){
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("Start " + method);
                    System.out.println("----------------");
                    Object value = method.invoke(bean,args);
                    System.out.println("---------------R-");
                    System.out.println("End " + method);

                    return value;
                }
            });
        }

        return bean;
    }
}
