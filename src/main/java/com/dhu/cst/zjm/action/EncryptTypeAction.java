package com.dhu.cst.zjm.action;

import com.dhu.cst.zjm.base.BaseAction;
import com.dhu.cst.zjm.entity.EncryptTypeEntity;
import com.dhu.cst.zjm.entity.base.EncryptTypeBaseEntity;
import com.dhu.cst.zjm.util.JsonUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by zjm on 2017/3/11.
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class EncryptTypeAction extends BaseAction<EncryptTypeEntity> {
    public String getEncryptType() throws Exception {
        List<EncryptTypeBaseEntity> list = encryptTypeService.findEncryptTypeList();
        if (list != null) {
            JsonUtil.toJson(ServletActionContext.getResponse(), list);
        } else {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        }
        return null;
    }
}
