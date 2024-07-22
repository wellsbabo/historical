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
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"kr.co.gravity.sample.mapper.database1"}, sqlSessionFactoryRef = "database1SqlSessionFactory")
public class Database1Config {

    @Primary
    @Bean(name = "database1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.database1")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "database1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("database1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeHandlers(new TypeHandler[]{});
        sqlSessionFactory.getObject().getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.getObject().getConfiguration().setCallSettersOnNulls(true);
        sqlSessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory.getObject();
    }

    @Primary
    @Bean(name = "database1SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory db1SqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(db1SqlSessionFactory);
    }

}
