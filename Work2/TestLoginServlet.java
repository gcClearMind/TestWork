import org.easymock.EasyMock;
import junit.framework.TestCase;
import org.easymock.EasyMock;

import org.junit.Before;
import org.junit.jupiter.api.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.junit.platform.commons.function.Try.success;


public class TestLoginServlet {
    public HttpServletRequest request;
    public LoginServlet servlet;
    public HttpSession session;
    public void init() {

        servlet = new LoginServlet();
        request = EasyMock.createStrictMock(HttpServletRequest.class);
        session = EasyMock.createStrictMock(HttpSession.class);

    }

    @Test
    public void test() {
        init();
        expect(request.getParameter("username")).andReturn("admin");
        expect(request.getParameter("password")).andReturn("1234");
        replay(request);
        try {

            servlet.doPost(request, null);
            System.out.println("access");
//            success("No catch!");
        }catch (RuntimeException re) {
            assertEquals("fail login","Login failed.", re.getMessage());
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(request);

    }

}
