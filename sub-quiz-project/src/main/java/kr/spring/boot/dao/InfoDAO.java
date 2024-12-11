package kr.spring.boot.dao;

import org.apache.ibatis.annotations.Param;

public interface InfoDAO {

	boolean updateInfo(@Param("type")String type, @Param("mb_id")String mb_id, @Param("mb_attr")String mb_attr);

}
