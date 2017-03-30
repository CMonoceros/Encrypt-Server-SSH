package com.dhu.cst.zjm.action;

import com.dhu.cst.zjm.base.BaseAction;
import com.dhu.cst.zjm.entity.FileEntity;
import com.dhu.cst.zjm.entity.base.FileBaseEntity;
import com.dhu.cst.zjm.util.JsonUtil;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.dhu.cst.zjm.action.EncryptRelationAction.zipPath;

/**
 * Created by zjm on 2017/3/26.
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FileDownloadAction extends BaseAction<FileEntity> {

    private String path;

    public InputStream getDownloadFile() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(path);
        return inputStream;
    }

    @Override
    public String execute() {
        String idString = ServletActionContext.getRequest().getParameter("id");
        if (idString == null) {
            return null;
        }
        FileBaseEntity fileBaseEntity = fileService.findFileById(Integer.parseInt(idString));
        if (fileBaseEntity != null) {
            String[] s = fileBaseEntity.getName().split("\\.");
            path = zipPath + fileBaseEntity.getOwner() + "/" + s[0] + ".zip";
            ServletActionContext.getResponse().setContentLength((int) new File(path).length());
            return SUCCESS;
        } else {
            return null;
        }

    }
}
