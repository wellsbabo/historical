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
@MapperScan(basePackages = {"kr.co.gravity.sample.mapper.database2"}, sqlSessionFactoryRef = "database2SqlSessionFactory")
public class Database2Config {

    @Bean(name = "database2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.database2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "database2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("database2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeHandlers(new TypeHandler[]{});
        sqlSessionFactory.getObject().getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.getObject().getConfiguration().setCallSettersOnNulls(true);
        sqlSessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory.getObject();
    }

    @Bean(name = "database2SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory db2SqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(db2SqlSessionFactory);
    }

}
