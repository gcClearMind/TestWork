package action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;
import java.util.Iterator;
import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.*;

public class RegisterActionTest {
    public RegisterAction registerAction;
    public UserService userService;
    @BeforeEach
    public void init() {
        registerAction = new RegisterAction();
        userService = createMock(UserService.class);
    }
    @Test
    public void validateTest1() {
        registerAction.setUsername("shk001");
        registerAction.setPassword("123");
        registerAction.validate();
        assertEquals(0, registerAction.getActionErrors().size());
    }

    @Test
    public void validateTest2() {
        registerAction.setUsername("");
        registerAction.setPassword("123");
        registerAction.validate();
        assertEquals(1, registerAction.getActionErrors().size());
        Iterator<String> it = registerAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("用户名不能为空！",error);
    }

    @Test
    public void validateTest3() {
        registerAction.setUsername("shk001");
        registerAction.setPassword("");
        registerAction.validate();
        assertEquals(1, registerAction.getActionErrors().size());
        Iterator<String> it = registerAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("密码不能为空！",error);
    }
    @Test
    public void validateTest4() {
        registerAction.setUsername("");
        registerAction.setPassword("");
        registerAction.validate();
        assertEquals(2, registerAction.getActionErrors().size());
        Iterator<String> it = registerAction.getActionErrors().iterator();
        String error1 = it.next();
        String error2 = it.next();
        assertEquals("用户名不能为空！",error1);
        assertEquals("密码不能为空！",error2);
    }

    @Test
    public void executeTest1() {
        String username = "shk001";
        String password = "123";
        registerAction.setUserService(userService);
        registerAction.setUsername(username);
        registerAction.setPassword(password);
        expect(userService.userRegister(username,password)).andReturn(Boolean.TRUE);
        replay(userService);
        assertEquals("success",registerAction.execute());
        Iterator<String> it = registerAction.getActionMessages().iterator();
        String error = it.next();
        assertEquals("注册成功！", error);
        verify(userService);
    }
    
    @Test
    public void executeTest2() {
        String username = "shk001";
        String password = "123";
        registerAction.setUserService(userService);
        registerAction.setUsername(username);
        registerAction.setPassword(password);
        expect(userService.userRegister(username,password)).andReturn(Boolean.FALSE);
        replay(userService);
        assertEquals("success",registerAction.execute());
        Iterator<String> it = registerAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("注册失败,该用户名已存在！", error);
        verify(userService);
    }
}