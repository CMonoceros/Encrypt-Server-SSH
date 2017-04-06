package com.dhu.cst.zjm.action;

import com.dhu.cst.zjm.base.BaseAction;
import com.dhu.cst.zjm.entity.EncryptRelationEntity;
import com.dhu.cst.zjm.entity.base.DesBaseEntity;
import com.dhu.cst.zjm.entity.base.EncryptRelationBaseEntity;
import com.dhu.cst.zjm.entity.base.FileBaseEntity;
import com.dhu.cst.zjm.util.FileUtil;
import com.dhu.cst.zjm.util.algorithm.des.DesUtil;
import com.dhu.cst.zjm.util.algorithm.md5.Md5Util;
import com.dhu.cst.zjm.util.algorithm.rsa.RSASignature;
import com.dhu.cst.zjm.util.algorithm.rsa.RSAUtil;
import com.dhu.cst.zjm.util.JsonUtil;
import com.dhu.cst.zjm.util.ZipUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zjm on 2017/3/13.
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class EncryptRelationAction extends BaseAction<EncryptRelationEntity> {
    public static final String savePath = "C:/Code/JAVA/test/save/";
    public static final String encryptPath = "C:/Code/JAVA/test/encrypt/";
    public static final String zipPath = "C:/Code/JAVA/test/zip/";

    public String encryptFile() throws Exception {
        int fileID = -1;
        int typeID = -1;
        String fileIDString = ServletActionContext.getRequest().getParameter("file_id");
        String typeIDString = ServletActionContext.getRequest().getParameter("type_id");
        if (fileIDString == null || typeIDString == null) {
            return null;
        } else {
            fileID = Integer.parseInt(fileIDString);
            typeID = Integer.parseInt(typeIDString);
        }
        FileBaseEntity file = fileService.findFileById(fileID);
        if (file == null) {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        } else if (encryptTypeService.findEncryptTypeById(typeID) == null) {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        } else if (encryptRelationService.findIDByFileAndType(fileID, typeID) != null) {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        } else {
            EncryptRelationBaseEntity encryptRelationBaseEntity = encryptRelationService.saveEncryptRelation(fileID, typeID);
            if (encryptRelationBaseEntity != null) {
                switch (typeID) {
                    case 1:
                        String key = ServletActionContext.getRequest().getParameter("des_key");
                        String layerString = ServletActionContext.getRequest().getParameter("des_layer");
                        if (layerString == null || key == null) {
                            return null;
                        } else {
                            desService.saveDesByRelationAndKeyAndLayer(encryptRelationBaseEntity.getId(),
                                    Integer.parseInt(layerString), key);
                            baseEncryptType(file, encryptRelationBaseEntity);
                        }
                        break;
                }
                JsonUtil.toJson(ServletActionContext.getResponse(), encryptRelationBaseEntity);
            } else {
                JsonUtil.toJson(ServletActionContext.getResponse(), null);
            }
        }
        return null;
    }

    private void baseEncryptType(FileBaseEntity file, EncryptRelationBaseEntity relation) throws Exception {
        String ownerEncryptPath = encryptPath + file.getOwner() + "/" + file.getName() + "/";
        String ownerZipPath = zipPath + file.getOwner() + "/";
        if (!(new File(ownerEncryptPath).exists())) {
            new File(ownerEncryptPath).mkdirs();
        }
        if (!(new File(ownerZipPath).exists())) {
            new File(ownerZipPath).mkdirs();
        }
        String[] s = file.getName().split("\\.");
        Map<String, String> map = RSAUtil.genKeyPair();
        rsaService.saveRsaByRelationAndKey(relation.getId(), map.get("public"), map.get("private"));

        String hashSign = Md5Util.getMd5ByFile(new File(file.getPath(), file.getName()));
        md5Service.saveMd5ByRelationAndSign(relation.getId(), hashSign);

        String signstr = RSASignature.sign(hashSign, map.get("private"));
        FileUtil.stringToFile(ownerEncryptPath + "sign.sign", signstr);
        FileUtil.stringToFile(ownerEncryptPath + "public.key", map.get("public"));
        DesBaseEntity desBaseEntity = desService.findDesByRelationAndLayer(relation.getId(), 1);

        byte[] encrypt = DesUtil.encrypt(FileUtil.File2byte(file.getPath() + file.getName()), desBaseEntity.getDesKey().getBytes());
        FileUtil.byte2File(encrypt, ownerEncryptPath, s[0] + ".encrypt");

        ZipUtil.ZipEncrypt(ownerEncryptPath,
                ownerZipPath, s[0] + ".zip");
    }


}
