package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.AppCate;
import com.nieyue.dao.AppCateDao;
import com.nieyue.service.AppCateService;
@Service
public class AppCateServiceImpl implements AppCateService{
	@Resource
	AppCateDao appCateDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addAppCate(AppCate appCate) {
		appCate.setUpdateDate(new Date());
		boolean b = appCateDao.addAppCate(appCate);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delAppCate(Integer appCateId) {
		boolean b = appCateDao.delAppCate(appCateId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateAppCate(AppCate appCate) {
		appCate.setUpdateDate(new Date());
		boolean b = appCateDao.updateAppCate(appCate);
		return b;
	}

	@Override
	public AppCate loadAppCate(Integer appCateId) {
		AppCate r = appCateDao.loadAppCate(appCateId);
		return r;
	}

	@Override
	public int countAll() {
		int c = appCateDao.countAll();
		return c;
	}

	@Override
	public List<AppCate> browsePagingAppCate(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<AppCate> l = appCateDao.browsePagingAppCate(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
