package com.dhu.cst.zjm.service;

import com.dhu.cst.zjm.base.BaseDaoSupport;
import com.dhu.cst.zjm.entity.EncryptTypeEntity;
import com.dhu.cst.zjm.entity.base.EncryptTypeBaseEntity;

import java.util.List;

/**
 * Created by zjm on 2017/3/11.
 */
public interface BaseEncryptTypeService extends BaseDaoSupport<EncryptTypeEntity> {
    List<EncryptTypeBaseEntity> findEncryptTypeList();

    EncryptTypeBaseEntity findEncryptTypeById(int id);
}
