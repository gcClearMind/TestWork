import org.easymock.EasyMock;
import junit.framework.TestCase;
import org.easymock.EasyMock;

import org.junit.Before;
import org.junit.jupiter.api.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.junit.platform.commons.function.Try.success;


public class TestLoginServlet {
    public HttpServletRequest request;
    public LoginServlet servlet;
    @Test
    public void testLoginSuccess() throws ServletException, IOException {
        // create mock:
        HttpServletRequest request = createMock(HttpServletRequest.class);
        final ServletContext contextObj = createMock(ServletContext.class);
        RequestDispatcher dispatcherObj = createMock(RequestDispatcher.class);

         // set behavior:
        expect(request.getParameter("username")).andReturn("admin");
        expect(request.getParameter("password")).andReturn("123456");
        expect(contextObj.getNamedDispatcher("dispatcher")).andReturn(
                dispatcherObj);
        dispatcherObj.forward(request, null);
        // done!
        replay(request);
        replay(contextObj);
        replay(dispatcherObj);
        // test:
        LoginServlet servlet = new LoginServlet() {

            private static final long serialVersionUID = 1L;

            public ServletContext getServletContext() {
                return contextObj;
            }
        };
        servlet.doPost(request, null);
        // verify:
        verify(request);
        verify(contextObj);
        verify(dispatcherObj);
    }

    @Test
    public void testLoginFailed() {
        request = EasyMock.createMock(HttpServletRequest.class);
        EasyMock.expect(request.getParameter("username")).andReturn("admin");
        EasyMock.expect(request.getParameter("password")).andReturn("1236");
        EasyMock.replay(request);
        try {
            servlet = new LoginServlet();
            servlet.doPost(request, null);
            fail("Not caught exception!");
        } catch (RuntimeException re) {
            assertEquals("Login failed.", re.getMessage());
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(request);
    }

}
