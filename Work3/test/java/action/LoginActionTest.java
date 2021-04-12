package action;

import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsSpringTestCase;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginActionTest {

    public LoginAction loginAction;
    public UserService userService;

    @BeforeEach
    public void init() {
        loginAction = new LoginAction();
        userService = createMock(UserService.class);
        loginAction.setUserService(userService);
    }

    @Test
    public void validateTest1() {
        loginAction.setUsername("shk001");
        loginAction.setPassword("123");
        loginAction.validate();
        assertEquals(0, loginAction.getActionErrors().size());
    }

    @Test
    public void validateTest2() {
        loginAction.setUsername("");
        loginAction.setPassword("123");
        loginAction.validate();
        assertEquals(1, loginAction.getActionErrors().size());
        Iterator<String> it = loginAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("用户名不能为空！",error);
    }

    @Test
    public void validateTest3() {
        loginAction.setUsername("shk001");
        loginAction.setPassword("");
        loginAction.validate();
        assertEquals(1, loginAction.getActionErrors().size());
        Iterator<String> it = loginAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("密码不能为空！",error);
    }
    @Test
    public void validateTest4() {
        loginAction.setUsername("");
        loginAction.setPassword("");
        loginAction.validate();
        assertEquals(2, loginAction.getActionErrors().size());
        Iterator<String> it = loginAction.getActionErrors().iterator();
        String error1 = it.next();
        String error2 = it.next();
        assertEquals("用户名不能为空！",error1);
        assertEquals("密码不能为空！",error2);
    }

    @Test
    public void executeTest1() {
        String username = "shk001";
        String password = "123";
        Map session = new HashMap();
        loginAction.setUsername(username);
        loginAction.setPassword(password);
        loginAction.setSession(session);
        expect(userService.loginVerify(username,password)).andReturn(Boolean.TRUE);
        replay(userService);
        assertEquals("success",loginAction.execute());
        verify(userService);
    }

    @Test
    public void executeTest2() {
        String username = "shk001";
        String password = "123";
        Map session = new HashMap();
        loginAction.setUsername(username);
        loginAction.setPassword(password);
        loginAction.setSession(session);
        expect(userService.loginVerify(username,password)).andReturn(Boolean.FALSE);
        replay(userService);
        assertEquals("input",loginAction.execute());
        Iterator<String> it = loginAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("登录失败！", error);
        verify(userService);

    }
}