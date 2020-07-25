package com.in.service.impl;

import com.in.constant.Constant;
import com.in.dao.ProductDao;
import com.in.dao.UserDao;
import com.in.dao.impl.UserDaoImpl;
import com.in.domain.User;
import com.in.service.UserService;
import com.in.utils.BeanFactory;
import com.in.utils.MailUtils;

public class UserServiceImpl implements UserService {

	
	@Override
	/*
	 * 用户注册
	 */
	public void regist(User user) throws Exception {
		//1. Enregistrement complet
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
		ud.save(user);
		
		//2. Envoyer un mail pour activer le compte
		String emailMsg = "Felicitation"+user.getName()+": Succès de l'inscription，<a href='http://localhost:8090/shop/user?method=active&code="+user.getCode()+"'>Cliquer ici pour activer votre compte.</a>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
		
	}

	/*
	 * 用户激活
	 */
	public User active(String code) throws Exception {
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
		//1.La paramètre code
		User user = ud.getByCode(code);
		
		//1.1 Aucun utilisateur trouvé par code d'activation
		if(user == null) {
			return null;
		}
		//2.Changer la state
		user.setState(Constant.USER_IS_ACTIVE);
		user.setCode(null);
		
		ud.update(user);
		return user;
	}

	@Override
	/*
	 * 用户登录
	 */
	public User login(String username, String password) throws Exception {
	    UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
	    
		return ud.getByUsernameAndPwd(username,password);
	}

}
