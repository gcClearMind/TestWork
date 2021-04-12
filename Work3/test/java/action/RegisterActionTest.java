package action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;
import java.util.Iterator;
import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.*;

class RegisterActionTest {
    public RegisterAction registerAction;
    public UserService userService;
    @BeforeEach
    public void init() {
        registerAction = new RegisterAction();
        userService = createMock(UserService.class);
    }

    @Test
    public void validateTest1() {
        registerAction.setUsername("");
        registerAction.setPassword("123");
        registerAction.validate();
        Iterator<String> it = registerAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("用户名不能为空！",error);
    }

    @Test
    public void validateTest2() {
        registerAction.setUsername("123");
        registerAction.setPassword("");
        registerAction.validate();
        Iterator<String> it = registerAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("密码不能为空！",error);
    }

    @Test
    public void executeTest1() {
        String username = "123";
        String password = "123";
        registerAction.setUserService(userService);
        registerAction.setUsername(username);
        registerAction.setPassword(password);
        expect(userService.userRegister("123","123")).andReturn(Boolean.TRUE);
        replay(userService);
        assertEquals("success",registerAction.execute());
        Iterator<String> it = registerAction.getActionMessages().iterator();
        String error = it.next();
        assertEquals("注册成功！", error);
        verify(userService);
    }
    
    @Test
    public void executeTest2() {
        String username = "123";
        String password = "123";
        registerAction.setUserService(userService);
        registerAction.setUsername(username);
        registerAction.setPassword(password);
        expect(userService.userRegister("123","123")).andReturn(Boolean.FALSE);
        replay(userService);
        assertEquals("success",registerAction.execute());
        Iterator<String> it = registerAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("注册失败,该用户名已存在！", error);
        verify(userService);
    }
}