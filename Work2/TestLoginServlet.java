import org.easymock.EasyMock;
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
    public ServletContext context;
    public RequestDispatcher dispatcher;
    @BeforeEach
    public void init() {
        request = createMock(HttpServletRequest.class);
        context= createMock(ServletContext.class);
        dispatcher = createMock(RequestDispatcher.class);
        servlet = new LoginServlet() {
            private static final long serialVersionUID = 1L;
            public ServletContext getServletContext() {
                return context;
            }
        };
    }

    @Test
    public void testLoginSuccess1() throws ServletException, IOException {
        expect(request.getParameter("username")).andReturn("admin");
        expect(request.getParameter("password")).andReturn("123456");
        expect(context.getNamedDispatcher("dispatcher")).andReturn(dispatcher);
        dispatcher.forward(request, null);
        replay(request);
        replay(context);
        replay(dispatcher);
        try{
            servlet.doPost(request, null);
            success("yeah");
        }
        catch (RuntimeException re) {
            fail("WA");
        }
        // verify:
        verify(request);
        verify(context);
        verify(dispatcher);
    }

    @Test
    public void testLoginFailed2() throws ServletException, IOException {
        EasyMock.expect(request.getParameter("username")).andReturn("admin");
        EasyMock.expect(request.getParameter("password")).andReturn("114514");
        EasyMock.replay(request);
        try{
            servlet.doPost(request, null);
            success("yeah");
        } catch (RuntimeException re) {
            assertEquals("Login failed.", re.getMessage());
        }
        verify(request);
    }
    @Test
    public void testLoginFailed3() throws ServletException, IOException {
        EasyMock.expect(request.getParameter("username")).andReturn("adc");
        EasyMock.expect(request.getParameter("password")).andReturn("123456");
        EasyMock.replay(request);
        try{
            servlet.doPost(request, null);
            success("yeah");
        } catch (RuntimeException re) {
            assertEquals("Login failed.", re.getMessage());
        }
        verify(request);
    }
    @Test
    public void testLoginFailed4() throws ServletException, IOException {
        EasyMock.expect(request.getParameter("username")).andReturn("admin");
        EasyMock.expect(request.getParameter("password")).andReturn(null);
        EasyMock.replay(request);
        try{
            servlet.doPost(request, null);
            success("yeah");
        } catch (RuntimeException re) {
            assertEquals("Login failed.", re.getMessage());
        }
        verify(request);
    }
    @Test
    public void testLoginFailed5() throws ServletException, IOException {
        EasyMock.expect(request.getParameter("username")).andReturn(null);
        EasyMock.expect(request.getParameter("password")).andReturn("123456");
        EasyMock.replay(request);
        try{
            servlet.doPost(request, null);
            success("yeah");
        } catch (RuntimeException re) {
            assertEquals("Login failed.", re.getMessage());
        }
        verify(request);
    }
    @Test
    public void testLoginFailed6() throws ServletException, IOException {
        EasyMock.expect(request.getParameter("username")).andReturn(null);
        EasyMock.expect(request.getParameter("password")).andReturn(null);
        EasyMock.replay(request);
        try{
            servlet.doPost(request, null);
            success("yeah");
        } catch (RuntimeException re) {
            assertEquals("Login failed.", re.getMessage());
        }
        verify(request);
    }
    @Test
    public void testLoginFailed7() throws ServletException, IOException {
        EasyMock.expect(request.getParameter("username")).andReturn("yilu");
        EasyMock.expect(request.getParameter("password")).andReturn("1919");
        EasyMock.replay(request);
        try{
            servlet.doPost(request, null);
            success("yeah");
        } catch (RuntimeException re) {
            assertEquals("Login failed.", re.getMessage());
        }
        verify(request);
    }

}
