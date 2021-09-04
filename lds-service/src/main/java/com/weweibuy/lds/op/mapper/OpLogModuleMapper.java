package com.weweibuy.lds.op.mapper;

import com.weweibuy.lds.op.model.example.OpLogModuleExample;
import com.weweibuy.lds.op.model.po.OpLogModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OpLogModuleMapper {
    long countByExample(OpLogModuleExample example);

    int deleteByExample(OpLogModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpLogModule record);

    int insertSelective(OpLogModule record);

    OpLogModule selectOneByExample(OpLogModuleExample example);

    List<OpLogModule> selectByExample(OpLogModuleExample example);

    OpLogModule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpLogModule record, @Param("example") OpLogModuleExample example);

    int updateByExample(@Param("record") OpLogModule record, @Param("example") OpLogModuleExample example);

    int updateByPrimaryKeySelective(OpLogModule record);

    int updateByPrimaryKey(OpLogModule record);
}