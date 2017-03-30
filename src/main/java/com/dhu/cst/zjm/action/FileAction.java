package com.dhu.cst.zjm.action;

import com.dhu.cst.zjm.base.BaseAction;
import com.dhu.cst.zjm.entity.FileEntity;
import com.dhu.cst.zjm.entity.base.FileBaseEntity;
import com.dhu.cst.zjm.util.FileUtil;
import com.dhu.cst.zjm.util.JsonUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zjm on 2017/3/11.
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class FileAction extends BaseAction<FileEntity> {

    public String getFileList() throws Exception {
        int id = 0;
        String idString = ServletActionContext.getRequest().getParameter("owner");
        if (idString != null) {
            id = Integer.parseInt(idString);
        }
        List<FileBaseEntity> list = fileService.findFileListById(id);
        if (list != null) {
            JsonUtil.toJson(ServletActionContext.getResponse(), list);
        } else {
            JsonUtil.toJson(ServletActionContext.getResponse(), null);
        }
        return null;
    }

    public String getFileListByPaper() throws Exception {
        int id = 0;
        int rows = 20;
        int paper = 1;
        String idString = ServletActionContext.getRequest().getParameter("owner");
        String rowsString = ServletActionContext.getRequest().getParameter("rows");
        String paperString = ServletActionContext.getRequest().getParameter("paper");
        if (idString == null || rowsString == null || paperString == null) {
            return null;
        } else {
            id = Integer.parseInt(idString);
            rows = Integer.parseInt(rowsString);
            paper = Integer.parseInt(paperString);
            List<FileBaseEntity> list = fileService.findFileListByPaper(id, paper, rows);
            if (list != null) {
                JsonUtil.toJson(ServletActionContext.getResponse(), list);
            } else {
                JsonUtil.toJson(ServletActionContext.getResponse(), null);
            }
        }
        return null;
    }
}
