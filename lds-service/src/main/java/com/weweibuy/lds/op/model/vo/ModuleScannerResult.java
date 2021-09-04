package com.weweibuy.lds.op.model.vo;

import com.weweibuy.lds.op.core.OpLogConvertModule;
import com.weweibuy.lds.op.model.po.OpLogModule;
import lombok.Data;

import java.util.List;

/**
 * 模块扫描结果
 *
 * @author durenhao
 * @date 2021/9/4 11:27
 **/
@Data
public class ModuleScannerResult {

    private List<OpLogModule> opLogModuleListAdd;

    private List<OpLogModuleUpdate> opLogModuleListUpdate;

    private List<OpLogConvertModule> opLogModuleListRemove;

    public static ModuleScannerResult fromScan(List<OpLogModule> opLogModuleListAdd,
                                               List<OpLogModuleUpdate> opLogModuleListUpdate,
                                               List<OpLogConvertModule> opLogModuleListRemove) {
        ModuleScannerResult moduleScannerResult = new ModuleScannerResult();
        moduleScannerResult.setOpLogModuleListAdd(opLogModuleListAdd);
        moduleScannerResult.setOpLogModuleListUpdate(opLogModuleListUpdate);
        moduleScannerResult.setOpLogModuleListRemove(opLogModuleListRemove);
        return moduleScannerResult;
    }


    /**
     * 更新模块信息
     */
    @Data
    public static class OpLogModuleUpdate {

        private OpLogModule opLogModuleUpdate;

        private OpLogConvertModule moduleUpdate;

    }

    public static OpLogModuleUpdate moduleUpdate(OpLogModule opLogModule,
                                                 OpLogConvertModule moduleUpdate) {
        OpLogModuleUpdate opLogModuleUpdate = new OpLogModuleUpdate();
        opLogModuleUpdate.setOpLogModuleUpdate(opLogModule);
        opLogModuleUpdate.setModuleUpdate(moduleUpdate);
        return opLogModuleUpdate;
    }

}
