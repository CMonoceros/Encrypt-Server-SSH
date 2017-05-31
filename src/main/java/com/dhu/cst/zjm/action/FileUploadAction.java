package com.dhu.cst.zjm.action;

import com.dhu.cst.zjm.base.BaseAction;
import com.dhu.cst.zjm.entity.FileEntity;
import com.dhu.cst.zjm.entity.base.FileBaseEntity;
import com.dhu.cst.zjm.util.FileUtil;
import com.dhu.cst.zjm.util.JsonUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.dhu.cst.zjm.action.EncryptRelationAction.savePath;

/**
 * Created by zjm on 2017/3/26.
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FileUploadAction extends BaseAction<FileEntity> {



    private List<File> file;
    private List<String> fileFileName;
    private List<String> fileContentType;
    private String owner;
    private String fileName;
    private String fileSize;

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSize() {
        return fileSize;
    }

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    public List<String> getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(List<String> fileFileName) {
        this.fileFileName = fileFileName;
    }

    public List<String> getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(List<String> fileContentType) {
        this.fileContentType = fileContentType;
    }

    @Override
    public String execute() throws IOException {
        for (int i = 0; i < file.size(); i++) {
            String ownerSavePath=savePath + owner + "/";
            File dir=new File(ownerSavePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File out = new File(ownerSavePath, fileFileName.get(i));
            if (!out.exists()) {
                try {
                    out.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            OutputStream os = null;
            InputStream is = null;
            byte[] buf = new byte[4096];
            int length = 0;
            long fileUpload = 0;
            try {
                os = new FileOutputStream(out);
                is = new FileInputStream(file.get(i));
                length = is.read(buf);
                while (length > 0) {
                    os.write(buf, 0, length);
                    fileUpload += length;
                    length = is.read(buf);
                    System.out.println("Upload " + fileName + " :" + fileUpload);
                }
                os.flush();
                is.close();
                os.close();
            } catch (IOException e) {
                is.close();
                os.close();
                e.printStackTrace();
                return null;
            }
        }
        FileBaseEntity fileBaseEntity = fileService.saveFile(fileName, fileSize, savePath + owner + "/", Integer.parseInt(owner));
        if (fileBaseEntity != null) {
            JsonUtil.toJson(ServletActionContext.getResponse(), fileBaseEntity);
        } else {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        }
        return null;
    }

}
