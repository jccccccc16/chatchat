package com.cjc.chatchat.mapper;

import com.cjc.chatchat.entity.UserPO;
import com.cjc.chatchat.entity.UserPOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserPOMapper {
    int countByExample(UserPOExample example);

    int deleteByExample(UserPOExample example);

    int insert(UserPO record);

    int insertSelective(UserPO record);

    List<UserPO> selectByExample(UserPOExample example);

    int updateByExampleSelective(@Param("record") UserPO record, @Param("example") UserPOExample example);

    int updateByExample(@Param("record") UserPO record, @Param("example") UserPOExample example);
}