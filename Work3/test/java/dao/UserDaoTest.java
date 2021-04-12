package dao;

import entity.User;
import org.easymock.EasyMock;
import org.junit.jupiter.api.*;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    public HibernateTemplate hibernateTemplate;
    public UserDao userDao;
    @BeforeEach
    public void init() {
        userDao = new UserDao();
        hibernateTemplate = EasyMock.createMock(HibernateTemplate.class);
        userDao.setHibernateTemplate(hibernateTemplate);
    }
    @Test
    public void addUserTest1() {
        String username = "shk001";
        String password = "123";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        List<User> users= new ArrayList<User>();
        expect((List<User>) hibernateTemplate.find("from User where username=?", username)).andReturn(users);
        expect(hibernateTemplate.save(user)).andReturn(1);
        replay(hibernateTemplate);
        assertEquals(true, userDao.addUser(user));
        verify(hibernateTemplate);
    }

    @Test
    public void addUserTest2() {
        String username = "shk001";
        String password = "123";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        List<User> users= new ArrayList<User>();
        users.add(user);
        expect((List<User>)hibernateTemplate.find("from User where username=?", username)).andReturn(users);
//        expect(hibernateTemplate.save(user)).andReturn(1);
        replay(hibernateTemplate);
        assertEquals(false, userDao.addUser(user));
        verify(hibernateTemplate);
    }

    @Test
    public void verifyUsernameTest1() {
        String username = "shk001";
        List<User> users= new ArrayList<User>();
        expect((List<User>)hibernateTemplate.find("from User where username=?", username)).andReturn(users);
        replay(hibernateTemplate);
        assertEquals(false, userDao.verifyUsername(username));
        verify(hibernateTemplate);
    }

    @Test
    public void verifyUsernameTest2() {
        String username = "shk001";
        String password = "123";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        List<User> users= new ArrayList<User>();
        users.add(user);
        expect((List<User>)hibernateTemplate.find("from User where username=?", username)).andReturn(users);
        replay(hibernateTemplate);
        assertEquals(true, userDao.verifyUsername(username));
        verify(hibernateTemplate);
    }

    @Test
    public void verifyPasswordTest1() {

        String username = "shk001";
        String testPassword = "123";
        String truePassword = "123";
        List<String> list = new ArrayList<String>();
        list.add(truePassword);
        expect(hibernateTemplate.find("select password from User where username=?", username)).andReturn((List) list);
        replay(hibernateTemplate);
        assertEquals(true, userDao.verifyPassword(username,testPassword));
        verify(hibernateTemplate);
    }

    @Test
    public void verifyPasswordTest2() {
        String username = "shk001";
        String testPassword = "12345";
        String truePassword = "123";
        List<String> list = new ArrayList<String>();
        list.add(truePassword);
        expect(hibernateTemplate.find("select password from User where username=?", username)).andReturn((List) list);
        replay(hibernateTemplate);
        assertEquals(false, userDao.verifyPassword(username,testPassword));
        verify(hibernateTemplate);
    }

    @Test
    public void verifyPasswordTest3() {
        String username = "shk003";
        String testPassword = "114514";
        List<String> list = new ArrayList<String>();
        expect(hibernateTemplate.find("select password from User where username=?", username)).andReturn((List) list);
        replay(hibernateTemplate);
        assertEquals(false, userDao.verifyPassword(username,testPassword));
        verify(hibernateTemplate);
    }
}