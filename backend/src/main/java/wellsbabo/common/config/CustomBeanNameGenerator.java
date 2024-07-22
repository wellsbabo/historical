package wellsbabo.common.config;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;


public class CustomBeanNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
        final String result;
        result = generateFullBeanName((AnnotatedBeanDefinition) beanDefinition);
        return result;
    }

    private String generateFullBeanName(final AnnotatedBeanDefinition definition) {
        return definition.getMetadata().getClassName();
    }

}
