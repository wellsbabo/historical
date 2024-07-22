package kr.co.gravity.sample.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"kr.co.gravity.sample.mapper.database3"}, sqlSessionFactoryRef = "database3SqlSessionFactory")
public class Database3Config {

    @Bean(name = "database3DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.database3")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "database3SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("database3DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeHandlers(new TypeHandler[]{});
        sqlSessionFactory.getObject().getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.getObject().getConfiguration().setCallSettersOnNulls(true);
        sqlSessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory.getObject();
    }

    @Bean(name = "database3SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory db3SqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(db3SqlSessionFactory);
    }

}
