package action;

import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsSpringTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginActionTest  extends StrutsSpringTestCase {

    public LoginAction loginAction;
    public UserService userService;
    protected String[] getContextLocations() {
        return new String[] { "struts.xml", "applicationContext.xml" };
    }
    @BeforeEach
    public void init() {

        loginAction = new LoginAction();
        userService = createMock(UserService.class);
    }

    @Test
    public void test(){
        initServletMockObjects();
        ActionProxy proxy = getActionProxy("/loginAction");
        LoginAction loginAction = (LoginAction) proxy.getAction();
        assertNotNull(loginAction);
        String username = "123";
        String password = "123";
        Map session = new HashMap();
        loginAction.setUserService(userService);
        loginAction.setUsername(username);
        loginAction.setPassword(password);
        loginAction.setSession(session);

        expect(userService.loginVerify("123","123")).andReturn(Boolean.TRUE);
        replay(userService);
        assertEquals("success",loginAction.execute());
        verify(userService);
    }

    @Test
    public void validateTest1() {

        loginAction.setUsername("");
        loginAction.setPassword("12");
        loginAction.validate();
        Iterator<String> it = loginAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("用户名不能为空！",error);
    }

    @Test
    public void validateTest2() {
        loginAction.setUsername("123");
        loginAction.setPassword("");
        loginAction.validate();
        Iterator<String> it = loginAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("密码不能为空！",error);
    }

    @Test
    public void executeTest1() {
        String username = "123";
        String password = "123";
        Map session = new HashMap();
        loginAction.setUserService(userService);
        loginAction.setUsername(username);
        loginAction.setPassword(password);
        loginAction.setSession(session);

        expect(userService.loginVerify("123","123")).andReturn(Boolean.TRUE);
        replay(userService);
        assertEquals("success",loginAction.execute());
        verify(userService);
    }

    @Test
    public void executeTest2() {
        String username = "123";
        String password = "123";
        Map session = new HashMap();
        loginAction.setUserService(userService);
        loginAction.setUsername(username);
        loginAction.setPassword(password);
        loginAction.setSession(session);
        expect(userService.loginVerify("123","123")).andReturn(Boolean.FALSE);
        replay(userService);
        assertEquals("input",loginAction.execute());
        Iterator<String> it = loginAction.getActionErrors().iterator();
        String error = it.next();
        assertEquals("登录失败！", error);
        verify(userService);

    }
}