package com.dhu.cst.zjm.service.impl;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.SessionEntity;
import com.dhu.cst.zjm.entity.UserEntity;
import com.dhu.cst.zjm.entity.base.SessionBaseEntity;
import com.dhu.cst.zjm.service.BaseSessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zjm on 2017/3/20.
 */
@Transactional
@Service("sessionService")
public class SessionServiceImpl extends BaseDaoSupportImpl<SessionEntity> implements BaseSessionService {
    @Override
    public SessionBaseEntity saveSession(int userId, String sessionId, int time) {
        if (getSession(userId) != null) {
            deleteSession(userId);
        }
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId(sessionId);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        sessionEntity.setUserByUserID(userEntity);
        sessionEntity.setTime(time);
        try {
            save(sessionEntity);
            return getSession(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SessionBaseEntity getSession(int userId) {
        return (SessionBaseEntity) getSession().createQuery("from SessionBaseEntity where userId=?")
                .setParameter(0, userId)
                .uniqueResult();
    }

    @Override
    public void deleteSession(int userId) {
        getSession().createQuery("delete SessionBaseEntity s where s.userId=?")
                .setParameter(0, userId)
                .executeUpdate();
    }
}
