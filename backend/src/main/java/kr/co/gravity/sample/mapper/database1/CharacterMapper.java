package kr.co.gravity.sample.mapper.database1;

import kr.co.gravity.sample.controller.dto.CharacterSearch;
import kr.co.gravity.sample.service.dto.Character;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CharacterMapper {

    @Select("SELECT * FROM `character` WHERE id = #{id}")
    Character findById(@Param("id") String id);

    @Select("SELECT * FROM `character` LIMIT %${search.skipRows}, %${search.limit}")
    List<Character> findAll(@Param("search") CharacterSearch search);

    @Select("SELECT COUNT(*) FROM `character`")
    int countAll();

    @Select("SELECT * FROM `character` WHERE name like '%${search.name}%' LIMIT %${search.skipRows}, %${search.limit}")
    List<Character> findByName(@Param("search") CharacterSearch search);

    @Select("SELECT COUNT(*) FROM `character` WHERE name like '%${search.name}%'")
    int countByName(@Param("search") CharacterSearch search);

    // MyBatis : Dynamic SQL (https://mybatis.org/mybatis-3/dynamic-sql.html)
    @Select("""
        <script>
            SELECT * FROM `character`
            <where>
                <foreach collection='search.names' item='item' index='index' open='name in (' separator=',' close=')' nullable='true'>
                    #{item}
                </foreach>
            </where>
            LIMIT %${search.skipRows}, %${search.limit}
        </script>
    """)
    List<Character> findByNames(@Param("search") CharacterSearch search);

    @Select("""
        <script>
            SELECT COUNT(*) FROM `character`
            <where>
                <foreach collection='names' item='item' index='index' open='name in (' separator=',' close=')' nullable='true'>
                    #{item}
                </foreach>
            </where>
        </script>
    """)
    int countByNames(@Param("names") String[] names);

}
