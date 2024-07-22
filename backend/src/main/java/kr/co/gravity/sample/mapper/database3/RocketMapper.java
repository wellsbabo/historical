package kr.co.gravity.sample.mapper.database3;

import wellsbabo.common.request.ApiPageRequest;
import kr.co.gravity.sample.service.dto.Rocket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RocketMapper {

    @Select("SELECT * FROM team_rocket LIMIT %${search.skipRows}, %${search.limit}")
    List<Rocket> findAll(@Param("search") ApiPageRequest search);

    @Select("SELECT COUNT(*) FROM team_rocket")
    int countAll();

}
