package action;

import com.opensymphony.xwork2.ActionSupport;

import service.UserService;

public class RegisterAction extends ActionSupport {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void validate() {
		super.clearErrorsAndMessages();
		if("".equals(username)) {
			super.addActionError("�û�������Ϊ�գ�");
		}
		if("".equals(password)) {
			super.addActionError("���벻��Ϊ�գ�");
		}
	}

	public String execute() {
		if(userService.userRegister(username, password)) {
			super.addActionMessage("ע��ɹ���");
		} else {
			super.addActionError("ע��ʧ��,���û����Ѵ��ڣ�");
		}
		return "success";
	}
}
