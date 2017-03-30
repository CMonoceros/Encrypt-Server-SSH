package com.dhu.cst.zjm.service;

import com.dhu.cst.zjm.base.BaseDaoSupport;
import com.dhu.cst.zjm.entity.DesEntity;
import com.dhu.cst.zjm.entity.base.DesBaseEntity;

/**
 * Created by zjm on 2017/3/21.
 */
public interface BaseDesService extends BaseDaoSupport<DesEntity> {
    DesBaseEntity findDesByRelationAndLayer(int relationId,int layer);

    DesBaseEntity saveDesByRelationAndKeyAndLayer(int relationId,int layer,String key);
}
