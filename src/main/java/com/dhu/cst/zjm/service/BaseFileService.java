package com.dhu.cst.zjm.service;

import com.dhu.cst.zjm.base.BaseDaoSupport;
import com.dhu.cst.zjm.entity.FileEntity;
import com.dhu.cst.zjm.entity.base.FileBaseEntity;

import java.util.List;

/**
 * Created by zjm on 2017/3/11.
 */
public interface BaseFileService extends BaseDaoSupport<FileEntity>{
    List<FileBaseEntity> findFileListById(int id);

    FileBaseEntity findFileById(int id);

    FileBaseEntity findFileByNameAndOwner(String name, int owner);

    FileBaseEntity saveFile(String name,String size,String path,int owner);

    FileBaseEntity updateFileEncryptTime(int id);

    FileBaseEntity updateFileDownloadTime(int id);

    List<FileBaseEntity> findFileListByPaper(int id,int page,int rows);
}
