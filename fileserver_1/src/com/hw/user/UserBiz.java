package com.hw.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hw.frame.Biz;
import com.hw.frame.Dao;
import com.hw.vo.User;
@Service("userBiz")
public class UserBiz implements Biz<User, String> {

	@Resource(name="userDao")
	Dao<User, String> dao;
	
	public UserBiz() {
		dao = new UserDao();
	}
	
	//이 함수는 트랜젝션이 자동으로 진행됨. 데이터가 바뀔 때 자동적으로 바뀐다.
	@Transactional
	@Override
	public void register(User t) {
		dao.insert(t);
		//dao.insert(t);
	}
	@Transactional
	@Override
	public void remove(String s) {
		dao.delete(s);

	}
	@Transactional
	@Override
	public void modify(User t) {
		dao.update(t);
	}

	@Override
	public User get(String s) {
		return dao.select(s);
	}

	@Override
	public List<User> get() {
		return dao.select();
	}

}





