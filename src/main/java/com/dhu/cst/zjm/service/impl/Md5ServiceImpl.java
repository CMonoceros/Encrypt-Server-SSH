package com.dhu.cst.zjm.service.impl;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.EncryptRelationEntity;
import com.dhu.cst.zjm.entity.Md5Entity;
import com.dhu.cst.zjm.entity.base.Md5BaseEntity;
import com.dhu.cst.zjm.service.BaseMd5Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zjm on 2017/3/21.
 */
@Transactional
@Service("md5Service")
public class Md5ServiceImpl extends BaseDaoSupportImpl<Md5Entity> implements BaseMd5Service {
    @Override
    public Md5BaseEntity findMd5ByRelation(int relationId) {
        return (Md5BaseEntity) getSession().createQuery("from Md5BaseEntity where relationId=?")
                .setParameter(0, relationId)
                .uniqueResult();
    }

    @Override
    public Md5BaseEntity saveMd5ByRelationAndSign(int relationId, String sign) {
        EncryptRelationEntity encryptRelationEntity = new EncryptRelationEntity();
        encryptRelationEntity.setId(relationId);
        Md5Entity md5Entity = new Md5Entity();
        md5Entity.setSign(sign);
        md5Entity.setEncryptRelationByRelationId(encryptRelationEntity);
        try {
            save(md5Entity);
            return findMd5ByRelation(relationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
