package com.seed.study.serv;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seed.study.dao.MainDao;
import com.seed.study.vo.InsertUser;
import com.seed.study.vo.MainVo;

@Service("mainService")
public class MainServiceImpl implements MainService {
	@Autowired
	private MainDao mainDao;

	@Override
	public ArrayList<MainVo> selectList() throws Exception {
		return mainDao.selectList();
	}

	@Override
	public int insertUser1(InsertUser iu) {
		return mainDao.insertUser1(iu);
	}

	@Override
	public int insertUser2(InsertUser iu) {
		return mainDao.insertUser2(iu);

	}

	@Override
	public int deleteUser1(int userNo) {
		return mainDao.deleteUser1(userNo);
	}

	@Override
	public int deleteUser2(int userNo) {
		return mainDao.deleteUser2(userNo);
	}

}
