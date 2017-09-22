package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.App;
import com.nieyue.bean.AppCate;
import com.nieyue.bean.AppImg;
import com.nieyue.dao.AppDao;
import com.nieyue.service.AppCateService;
import com.nieyue.service.AppImgService;
import com.nieyue.service.AppService;
@Service
public class AppServiceImpl implements AppService{
	@Resource
	AppDao appDao;
	@Resource
	AppCateService appCateService;
	@Resource
	AppImgService appImgService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addApp(App app) {
		app.setCreateDate(new Date());
		app.setUpdateDate(new Date());
		app.setPvs(0);
		app.setUvs(0);
		app.setIps(0);
		app.setReadingNumber(0);
		app.setDownloadNumber(0);
		if(app.getStatus()==null||app.getStatus().equals("")){
			app.setStatus(1);//默认上架
		}
		boolean b = appDao.addApp(app);
		List<AppImg> appImgList = app.getAppImgList();
		for (int i = 0; i < appImgList.size(); i++) {
			AppImg appImg = appImgList.get(i);
			appImg.setAppId(app.getAppId());
			b=appImgService.addAppImg(app.getAppImgList().get(i));
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delApp(Integer appId) {
		boolean b = appDao.delApp(appId);
		List<AppImg> appImgList=appImgService.browsePagingAppImg(appId, null, null, 1, Integer.MAX_VALUE, "app_img_id", "asc");
		for (int i = 0; i < appImgList.size(); i++) {
			AppImg appImg = appImgList.get(i);
			b=appImgService.delAppImg(appImg.getAppImgId());
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateApp(App app) {
		app.setUpdateDate(new Date());
		boolean b = appDao.updateApp(app);
		List<AppImg> delAppImgList = appImgService.browsePagingAppImg(app.getAppId(), null, null,  1, Integer.MAX_VALUE, "app_img_id","asc");
		for (AppImg appImg : delAppImgList) {
			b = appImgService.delAppImg(appImg.getAppImgId());
			}
		List<AppImg> appImgList = app.getAppImgList();
		for (int i = 0; i < appImgList.size(); i++) {
			AppImg appImg = appImgList.get(i);
			appImg.setAppId(app.getAppId());
			b=appImgService.addAppImg(appImg);
		}
		return b;
	}

	@Override
	public App loadApp(Integer appId) {
		App app = appDao.loadApp(appId);
		AppCate appCate = appCateService.loadAppCate(app.getAppCateId());
		app.setAppCate(appCate);
		List<AppImg> appImgList = appImgService.browsePagingAppImg(app.getAppId(), null, null,  1, Integer.MAX_VALUE, "app_img_id","asc");
		app.setAppImgList(appImgList);
		return app;
	}

	@Override
	public int countAll(
			Integer type,
			Double capacity,
			Integer readingNumber,
			Integer downloadNumber,
			Integer appCateId,
			Date createDate,
			Date updateDate,
			Integer status) {
		int c = appDao.countAll(
				 type,
				 capacity,
				 readingNumber,
				 downloadNumber,
				 appCateId,
				 createDate,
				 updateDate,
				 status);
		return c;
	}

	@Override
	public List<App> browsePagingApp(
			Integer type,
			Double capacity,
			Integer readingNumber,
			Integer downloadNumber,
			Integer appCateId,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<App> l = appDao.browsePagingApp(
				 type,
				 capacity,
				 readingNumber,
				 downloadNumber,
				 appCateId,
				 createDate,
				 updateDate,
				 status,
				pageNum-1, pageSize, orderName, orderWay);
		
		for (App app : l) {
			AppCate appCate = appCateService.loadAppCate(app.getAppCateId());
			app.setAppCate(appCate);
		}
		return l;
	}
	@Override
	public App loadSmallApp(Integer appId) {
		App app = appDao.loadSmallApp(appId);
		return app;
	}

	
}
