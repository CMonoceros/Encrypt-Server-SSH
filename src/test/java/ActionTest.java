/**
 * Created by zjm on 2017/3/9.
 */

import com.dhu.cst.zjm.action.EncryptRelationAction;
import com.dhu.cst.zjm.action.EncryptTypeAction;
import com.dhu.cst.zjm.action.FileAction;
import com.dhu.cst.zjm.service.BaseEncryptRelationService;
import com.dhu.cst.zjm.service.BaseEncryptTypeService;
import com.dhu.cst.zjm.service.BaseFileService;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dhu.cst.zjm.action.UserAction;
import com.dhu.cst.zjm.service.BaseUserService;


public class ActionTest {
    private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

//    @Test
//    public void testUserAction() {
//        UserAction action = (UserAction) ac.getBean("userAction");
//        System.out.println(action);
//    }
//
//    @Test
//    public void testFileListAction() {
//        FileAction action = (FileAction) ac.getBean("fileAction");
//        System.out.println(action);
//    }
//
//    @Test
//    public void testEncryptTypeAction() {
//        EncryptTypeAction action = (EncryptTypeAction) ac.getBean("encryptTypeAction");
//        System.out.println(action);
//    }
//
//    @Test
//    public void testEncryptRelationAction() {
//        EncryptRelationAction action = (EncryptRelationAction) ac.getBean("encryptRelationAction");
//        System.out.println(action);
//    }
//
//    @Test
//    public void testSessionFaction() throws Exception {
//        SessionFactory sessionFaction = (SessionFactory) ac.getBean("sessionFactory");
//        System.out.println(sessionFaction);
//    }
//
//    @Test
//    public void testUserService() {
//        BaseUserService service = (BaseUserService) ac.getBean("userService");
//        System.out.println(service);
//    }
//
//    @Test
//    public void testFileListService() {
//        BaseFileService service = (BaseFileService) ac.getBean("fileService");
//        System.out.println(service);
//    }
//
//    @Test
//    public void testEncryptTypeService() {
//        BaseEncryptTypeService service = (BaseEncryptTypeService) ac.getBean("encryptTypeService");
//        System.out.println(service);
//    }
//
//    @Test
//    public void testEncryptRelationService() {
//        BaseEncryptRelationService service = (BaseEncryptRelationService) ac.getBean("encryptRelationService");
//        System.out.println(service);
//    }


}