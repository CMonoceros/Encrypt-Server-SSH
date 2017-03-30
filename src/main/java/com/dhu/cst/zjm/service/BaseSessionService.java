package com.dhu.cst.zjm.service;

import com.dhu.cst.zjm.base.BaseDaoSupport;
import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.SessionEntity;
import com.dhu.cst.zjm.entity.base.SessionBaseEntity;

/**
 * Created by zjm on 2017/3/20.
 */
public interface BaseSessionService extends BaseDaoSupport<SessionEntity> {
    SessionBaseEntity saveSession(int userId,String sessionId,int time);

    SessionBaseEntity getSession(int userId);

    void deleteSession(int userId);

}
