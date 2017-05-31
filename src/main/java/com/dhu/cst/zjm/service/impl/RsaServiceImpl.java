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
        RsaBaseEntity rsaBaseEntity = findRsaByRelation(relationId);
        if (rsaBaseEntity == null) {
            EncryptRelationEntity encryptRelationEntity = new EncryptRelationEntity();
            encryptRelationEntity.setId(relationId);
            RsaEntity rsaEntity = new RsaEntity();
            rsaEntity.setEncryptRelationByRelationId(encryptRelationEntity);
            rsaEntity.setPublicKey(publicKey);
            rsaEntity.setPrivateKey(privateKey);
            try {
                save(rsaEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return findRsaByRelation(relationId);
        } else {
            rsaBaseEntity = updateRsaByRelationAndKey(rsaBaseEntity.getId(), relationId, publicKey, privateKey);
            return rsaBaseEntity;
        }
    }

    @Override
    public RsaBaseEntity updateRsaByRelationAndKey(int id, int relationId, String publicKey, String privateKey) {
        RsaBaseEntity rsaBaseEntity = findRsaByRelation(relationId);
        if (rsaBaseEntity == null) {
            rsaBaseEntity = saveRsaByRelationAndKey(relationId, publicKey, privateKey);
            return rsaBaseEntity;
        } else {
            EncryptRelationEntity encryptRelationEntity = new EncryptRelationEntity();
            encryptRelationEntity.setId(relationId);
            RsaEntity rsaEntity = new RsaEntity();
            rsaEntity.setId(id);
            rsaEntity.setEncryptRelationByRelationId(encryptRelationEntity);
            rsaEntity.setPublicKey(publicKey);
            rsaEntity.setPrivateKey(privateKey);
            try {
                update(rsaEntity);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return findRsaByRelation(relationId);
        }
    }
}
