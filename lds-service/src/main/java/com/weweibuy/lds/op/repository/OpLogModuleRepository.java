package com.weweibuy.lds.op.repository;

import com.weweibuy.lds.op.mapper.OpLogModuleMapper;
import com.weweibuy.lds.op.model.example.OpLogModuleExample;
import com.weweibuy.lds.op.model.po.OpLogModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author durenhao
 * @date 2021/9/4 9:32
 **/
@Repository
@RequiredArgsConstructor
public class OpLogModuleRepository {

    private final OpLogModuleMapper opLogModuleMapper;


    public List<OpLogModule> select() {
        return opLogModuleMapper.selectByExample(OpLogModuleExample.newAndCreateCriteria()
                .andDeletedEqualTo(false)
                .example());
    }

}
