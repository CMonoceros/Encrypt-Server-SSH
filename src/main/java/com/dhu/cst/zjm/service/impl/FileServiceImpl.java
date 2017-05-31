package com.dhu.cst.zjm.service.impl;

import com.dhu.cst.zjm.base.BaseDaoSupportImpl;
import com.dhu.cst.zjm.entity.FileEntity;
import com.dhu.cst.zjm.entity.UserEntity;
import com.dhu.cst.zjm.entity.base.FileBaseEntity;
import com.dhu.cst.zjm.entity.base.PaperBaseEntity;
import com.dhu.cst.zjm.service.BaseFileService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by zjm on 2017/3/11.
 */

@Transactional
@Service("fileService")
public class FileServiceImpl extends BaseDaoSupportImpl<FileEntity> implements BaseFileService {
    public List<FileBaseEntity> findFileListById(int id) {
        return (List<FileBaseEntity>) getSession().createQuery("FROM FileBaseEntity WHERE owner=?")
                .setParameter(0, id)
                .setCacheable(true)
                .list();
    }

    public FileBaseEntity findFileById(int id) {
        return (FileBaseEntity) getSession().createQuery(
                "FROM FileBaseEntity WHERE id=?")
                .setParameter(0, id)
                .setCacheable(true)
                .uniqueResult();
    }

    public FileBaseEntity findFileByNameAndOwner(String name, int owner) {
        return (FileBaseEntity) getSession().createQuery("FROM FileBaseEntity WHERE name=? and owner=?")
                .setParameter(0, name)
                .setParameter(1, owner)
                .setCacheable(true)
                .uniqueResult();
    }


    public FileBaseEntity saveFile(String name, String size, String path, int owner) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(name);
        fileEntity.setSize(size);
        fileEntity.setPath(path);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(owner);
        fileEntity.setUserByOwner(userEntity);
        fileEntity.setUploadTime(new Timestamp(System.currentTimeMillis()));
        try {
            save(fileEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return findFileByNameAndOwner(name, owner);
    }

    @Override
    public FileBaseEntity updateFileEncryptTime(int id) {
        FileBaseEntity fileBaseEntity = findFileById(id);
        if (fileBaseEntity == null) {
            return null;
        } else {
            getSession().createQuery("update FileEntity f set f.lastEncryptTime=? where f.id=?")
                    .setParameter(0,new Timestamp(System.currentTimeMillis()))
                    .setParameter(1,id)
                    .executeUpdate();
            return findFileById(id);
        }
    }

    @Override
    public FileBaseEntity updateFileDownloadTime(int id) {
        FileBaseEntity fileBaseEntity = findFileById(id);
        if (fileBaseEntity == null) {
            return null;
        } else {
            getSession().createQuery("update FileEntity f set f.lastDownloadTime=? where f.id=?")
                    .setParameter(0,new Timestamp(System.currentTimeMillis()))
                    .setParameter(1,id)
                    .executeUpdate();
            return findFileById(id);
        }
    }

    @Override
    public List<FileBaseEntity> findFileListByPaper(int id, int page, int rows) {
        String hql = "FROM FileBaseEntity WHERE owner=:owner";
        PaperBaseEntity paperBaseEntity = new PaperBaseEntity();
        paperBaseEntity.setHql(hql);
        paperBaseEntity.setPage(page);
        paperBaseEntity.setRows(rows);
        Map<String, Object> map = new HashedMap();
        map.put("owner", id);
        return (List<FileBaseEntity>) findListByPaper(paperBaseEntity, map).getList();
    }
}
