package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.AppCate;

/**
 * 应用类型逻辑层接口
 * @author yy
 *
 */
public interface AppCateService {
	/** 新增应用类型 */	
	public boolean addAppCate(AppCate appCate) ;	
	/** 删除应用类型 */	
	public boolean delAppCate(Integer appCateId) ;
	/** 更新应用类型*/	
	public boolean updateAppCate(AppCate appCate);
	/** 装载应用类型 */	
	public AppCate loadAppCate(Integer appCateId);	
	/** 应用类型总共数目 */	
	public int countAll();
	/** 分页应用类型信息 */
	public List<AppCate> browsePagingAppCate(int pageNum,int pageSize,String orderName,String orderWay) ;
}
