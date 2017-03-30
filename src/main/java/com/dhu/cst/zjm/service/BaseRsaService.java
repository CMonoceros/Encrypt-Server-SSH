package com.dhu.cst.zjm.service;

import com.dhu.cst.zjm.base.BaseDaoSupport;
import com.dhu.cst.zjm.entity.RsaEntity;
import com.dhu.cst.zjm.entity.base.RsaBaseEntity;

/**
 * Created by zjm on 2017/3/21.
 */
public interface BaseRsaService extends BaseDaoSupport<RsaEntity>{
    RsaBaseEntity findRsaByRelation(int relationId);

    RsaBaseEntity saveRsaByRelationAndKey(int relationId,String publicKey,String privateKey);
}
