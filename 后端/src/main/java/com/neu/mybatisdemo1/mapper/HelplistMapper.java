package com.neu.mybatisdemo1.mapper;

import com.neu.mybatisdemo1.entity.Helplist;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @description 针对表【helplist】的数据库操作Mapper
* @createDate 2022-07-17 15:31:57
* @Entity com.neu.mybatisdemo1.entity.Helplist
*/
public interface HelplistMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Helplist record);

    int insertSelective(Helplist record);

    Helplist selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Helplist record);

    int updateByPrimaryKey(Helplist record);

    List<Helplist> getNewId();

    List<Helplist> search(@Param("donationDemand") String donationDemand, @Param("donationState") int donationState);

    List<Helplist> searchByGiverId(@Param("giverId")int giverId);

    List<Helplist> searchByRecipientId(@Param("recipientId")int recipientId);
}
