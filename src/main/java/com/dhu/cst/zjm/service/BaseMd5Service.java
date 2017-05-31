package com.dhu.cst.zjm.service;

import com.dhu.cst.zjm.base.BaseDaoSupport;
import com.dhu.cst.zjm.entity.Md5Entity;
import com.dhu.cst.zjm.entity.base.Md5BaseEntity;

/**
 * Created by zjm on 2017/3/21.
 */
public interface BaseMd5Service extends BaseDaoSupport<Md5Entity> {
    Md5BaseEntity findMd5ByRelation(int relationId);

    Md5BaseEntity saveMd5ByRelationAndSign(int relationId,String sign);

    Md5BaseEntity updateMd5ByRelationAndSign(int id,int relationId,String sign);
}
