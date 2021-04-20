import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet({"/register.do"})
public class register_judge extends HttpServlet {
    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String MemberName = new String(request.getParameter("MemberName").getBytes("iso-8859-1"), "UTF-8");
        String UserName = request.getParameter("UserName");
        String password = request.getParameter("password");
        password = getSHA256(password);
        String Sex = new String(request.getParameter("Sex").getBytes("iso-8859-1"), "UTF-8");
        String emil = request.getParameter("Email");
        String phone = request.getParameter("phoneNumber");
        PrintWriter out = response.getWriter();
        boolean f = false;
        String path = "C:\\Users\\29615\\Desktop\\information.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String str = null;
        while ((str = br.readLine()) != null) {//遍历
            str = str.substring(0, str.indexOf("|"));
            if (str.equals(UserName)) {
                f = true;
                out.println("<script language='JavaScript'>alert('Username already exists!')</script>");
                out.println("<center><a href=\"register.html\">register again</a></center>");
                break;
            }
        }
        if (!f) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path,true));
            String add = UserName + "|" + password + "|" + MemberName + "|" + Sex + "|" + emil + "|" + phone;
            bw.write(add);
            bw.newLine();
            bw.flush();
            bw.close();
            out.println("<center>register was successful,return to <a href=\"login.html\">login</a></center>");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
