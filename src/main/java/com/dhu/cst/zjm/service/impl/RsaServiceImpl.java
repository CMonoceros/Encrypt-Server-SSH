package com.dhu.cst.zjm.service.impl;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.EncryptRelationEntity;
import com.dhu.cst.zjm.entity.RsaEntity;
import com.dhu.cst.zjm.entity.base.RsaBaseEntity;
import com.dhu.cst.zjm.service.BaseRsaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zjm on 2017/3/21.
 */
@Transactional
@Service("rsaService")
public class RsaServiceImpl extends BaseDaoSupportImpl<RsaEntity> implements BaseRsaService {
    @Override
    public RsaBaseEntity findRsaByRelation(int relationId) {
        return (RsaBaseEntity) getSession().createQuery("from RsaBaseEntity where relationId=?")
                .setParameter(0, relationId)
                .uniqueResult();
    }

    @Override
    public RsaBaseEntity saveRsaByRelationAndKey(int relationId, String publicKey, String privateKey) {
        EncryptRelationEntity encryptRelationEntity = new EncryptRelationEntity();
        encryptRelationEntity.setId(relationId);
        RsaEntity rsaEntity = new RsaEntity();
        rsaEntity.setEncryptRelationByRelationId(encryptRelationEntity);
        rsaEntity.setPublicKey(publicKey);
        rsaEntity.setPrivateKey(privateKey);
        try {
            save(rsaEntity);
            return findRsaByRelation(relationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
