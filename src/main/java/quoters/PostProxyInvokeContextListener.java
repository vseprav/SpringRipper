package main.java.quoters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by andrii.peliak on 4/15/2016.
 */
public class PostProxyInvokeContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        ApplicationContext context = contextRefreshedEvent.getApplicationContext();

        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(name -> {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String originClassName = beanDefinition.getBeanClassName();

            try{
                Class originClass = Class.forName(originClassName);
                Method[] methods = originClass.getMethods();
                Arrays.stream(methods).forEach(originMethod -> {
                    if(originMethod.isAnnotationPresent(PostProxy.class)){
                        Object bean = context.getBean(name);
                        try {
                            Method currentMethod = bean.getClass().getMethod(originMethod.getName(), originMethod.getParameterTypes());
                            currentMethod.invoke(bean);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }catch (Exception e){
                e.printStackTrace();
            }


        });

    }
}
