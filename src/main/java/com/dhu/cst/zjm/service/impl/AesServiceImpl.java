package com.dhu.cst.zjm.service.impl;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.AesEntity;
import com.dhu.cst.zjm.service.BaseAesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zjm on 2017/3/21.
 */
@Transactional
@Service("aesService")
public class AesServiceImpl extends BaseDaoSupportImpl<AesEntity> implements BaseAesService {
}
