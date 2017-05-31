package com.dhu.cst.zjm.service.impl;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.EncryptFrequencyEntity;
import com.dhu.cst.zjm.entity.EncryptTypeEntity;
import com.dhu.cst.zjm.entity.UserEntity;
import com.dhu.cst.zjm.entity.base.EncryptFrequencyBaseEntity;
import com.dhu.cst.zjm.service.BaseEncryptFrequencyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zjm on 2017/5/29.
 */
@Transactional
@Service("encryptFrequencyService")
public class EncryptFrequencyServiceImpl extends BaseDaoSupportImpl<EncryptFrequencyEntity> implements BaseEncryptFrequencyService {
    @Override
    public EncryptFrequencyBaseEntity saveEncryptFrequency(int owner, int typeID) {
        EncryptFrequencyBaseEntity encryptFrequencyBaseEntity = findEncryptFrequencyByOwnerAndTypeID(owner, typeID);
        if (encryptFrequencyBaseEntity == null) {
            EncryptFrequencyEntity encryptFrequencyEntity = new EncryptFrequencyEntity();
            encryptFrequencyEntity.setDayFrequency(1);
            encryptFrequencyEntity.setWeekFrequency(1);
            encryptFrequencyEntity.setMonthFrequency(1);
            encryptFrequencyEntity.setYearFrequency(1);
            UserEntity userEntity = new UserEntity();
            userEntity.setId(owner);
            encryptFrequencyEntity.setUserByFrequencyOwner(userEntity);
            EncryptTypeEntity encryptTypeEntity = new EncryptTypeEntity();
            encryptTypeEntity.setId(typeID);
            encryptFrequencyEntity.setEncryptTypeByFrequencyTypeId(encryptTypeEntity);
            try {
                save(encryptFrequencyEntity);
                return findEncryptFrequencyByOwnerAndTypeID(owner, typeID);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            encryptFrequencyBaseEntity = updateEncryptFrequency(encryptFrequencyBaseEntity.getId(), owner, typeID);
            return encryptFrequencyBaseEntity;
        }
    }

    @Override
    public EncryptFrequencyBaseEntity updateEncryptFrequency(int id, int owner, int typeID) {
        EncryptFrequencyBaseEntity encryptFrequencyBaseEntity = findEncryptFrequencyByOwnerAndTypeID(owner, typeID);
        if (encryptFrequencyBaseEntity == null) {
            encryptFrequencyBaseEntity = saveEncryptFrequency(owner, typeID);
            return encryptFrequencyBaseEntity;
        } else {
            getSession().evict(encryptFrequencyBaseEntity);
            getSession().createQuery("update EncryptFrequencyBaseEntity f " +
                    "set f.dayFrequency=? ,f.weekFrequency=?,f.monthFrequency=?,f.yearFrequency=? " +
                    "where typeId=? and owner=?")
                    .setParameter(0, encryptFrequencyBaseEntity.getDayFrequency() + 1)
                    .setParameter(1, encryptFrequencyBaseEntity.getWeekFrequency() + 1)
                    .setParameter(2, encryptFrequencyBaseEntity.getMonthFrequency() + 1)
                    .setParameter(3, encryptFrequencyBaseEntity.getYearFrequency() + 1)
                    .setParameter(4, typeID)
                    .setParameter(5, owner)
                    .executeUpdate();
            return findEncryptFrequencyByOwnerAndTypeID(owner, typeID);
        }
    }

    @Override
    public EncryptFrequencyBaseEntity findEncryptFrequencyByOwnerAndTypeID(int owner, int typeID) {
        return (EncryptFrequencyBaseEntity) getSession().createQuery("FROM EncryptFrequencyBaseEntity WHERE owner=? and typeId=?")
                .setParameter(0, owner)
                .setParameter(1, typeID)
                .uniqueResult();
    }

    @Override
    public List<EncryptFrequencyBaseEntity> findEncryptFrequencyByOwner(int owner) {
        return (List<EncryptFrequencyBaseEntity>) getSession().createQuery("FROM EncryptFrequencyBaseEntity WHERE owner=?")
                .setParameter(0, owner)
                .list();
    }

    @Override
    public EncryptFrequencyBaseEntity updateEncryptFrequencySetPriority(int typeID, int owner, int priority) {
        getSession().createQuery("update EncryptFrequencyBaseEntity f set f.priority=? where typeId=? and owner=?")
                .setParameter(0, priority)
                .setParameter(1, typeID)
                .setParameter(2, owner)
                .executeUpdate();
        return findEncryptFrequencyByOwnerAndTypeID(owner, typeID);
    }

    @Override
    public List<Integer> findTypeIDByOwnerOrderByPriority(int owner) {
        return (List<Integer>) getSession().createQuery("select typeId FROM EncryptFrequencyBaseEntity WHERE owner=? order by priority")
                .setParameter(0, owner)
                .list();
    }
}
