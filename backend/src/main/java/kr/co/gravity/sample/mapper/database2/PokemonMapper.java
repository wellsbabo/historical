package kr.co.gravity.sample.mapper.database2;

import kr.co.gravity.sample.controller.dto.PokemonCreate;
import kr.co.gravity.sample.controller.dto.PokemonSearch;
import kr.co.gravity.sample.controller.dto.PokemonUpdate;
import kr.co.gravity.sample.service.dto.Pokemon;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PokemonMapper {

    @Select("""
        SELECT *
        FROM pokemon WITH(NOLOCK)
        ORDER BY code DESC
        OFFSET #{search.skipRows} ROWS
        FETCH NEXT #{search.limit} ROWS ONLY
    """)
    List<Pokemon> findAll(@Param("search") PokemonSearch search);

    @Select("SELECT COUNT(*) FROM pokemon")
    int countAll();

    @Insert("INSERT INTO pokemon (code, name) VALUES (#{create.code}, #{create.name})")
    int create(@Param("create") PokemonCreate create);

    @Update("Update pokemon SET code = #{update.code}, name = #{update.name} WHERE idx = #{idx}")
    int update(@Param("idx") int idx, @Param("update") PokemonUpdate update);

    @Update("DELETE FROM pokemon WHERE idx = #{idx}")
    int delete(@Param("idx") int idx);

}
