package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 应用
 * @author yy
 *
 */
public class App implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 应用id
	 */
	private Integer appId;
	/**
	 * 版面类型，1经典必玩，2热门推荐，3，最热必玩
	 */
	private Integer type;
	/**
	 * 应用来源
	 */
	private String source;
	/**
	 * 版本
	 */
	private String version;
	/**
	 * 应用名称
	 */
	private String title;
	/**
	 * 简介
	 */
	private String summary;
	/**
	 * 容量，单位MB
	 */
	private Double capacity;
	/**
	 *封面
	 */
	private String imgAddress;
	/**
	 *内容
	 */
	private String content;
	/**
	 *pv
	 */
	private Integer pvs;
	/**
	 *uv
	 */
	private Integer uvs;
	/**
	 *ip
	 */
	private Integer ips;
	/**
	 *阅读数
	 */
	private Integer readingNumber;
	/**
	 *下载次数
	 */
	private Integer downloadNumber;
	/**
	 *安卓跳转url
	 */
	private String androidUrl;
	/**
	 *ios跳转url
	 */
	private String iosUrl;
	/**
	 *下架0,上架1
	 */
	private Integer status;
	/**
	 *应用类型id,外键
	 */
	private Integer appCateId;
	/**
	 * 应用创建时间
	 */
	private Date createDate;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 应用类型
	 */
	private AppCate appCate;
	/**
	 * 应用图片集合
	 */
	private List<AppImg> appImgList;

	public App() {
		super();
	}

	@Override
	public String toString() {
		return "App [appId=" + appId + ", type=" + type + ", source=" + source + ", version=" + version + ", title="
				+ title + ", summary=" + summary + ", capacity=" + capacity + ", imgAddress=" + imgAddress
				+ ", content=" + content + ", pvs=" + pvs + ", uvs=" + uvs + ", ips=" + ips + ", readingNumber="
				+ readingNumber + ", downloadNumber=" + downloadNumber + ", androidUrl=" + androidUrl + ", iosUrl="
				+ iosUrl + ", status=" + status + ", appCateId=" + appCateId + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", appCate=" + appCate + ", appImgList=" + appImgList + "]";
	}

	public App(Integer appId, Integer type, String source, String version, String title, String summary,
			Double capacity, String imgAddress, String content, Integer pvs, Integer uvs, Integer ips,
			Integer readingNumber, Integer downloadNumber, String androidUrl, String iosUrl, Integer status,
			Integer appCateId, Date createDate, Date updateDate, AppCate appCate, List<AppImg> appImgList) {
		super();
		this.appId = appId;
		this.type = type;
		this.source = source;
		this.version = version;
		this.title = title;
		this.summary = summary;
		this.capacity = capacity;
		this.imgAddress = imgAddress;
		this.content = content;
		this.pvs = pvs;
		this.uvs = uvs;
		this.ips = ips;
		this.readingNumber = readingNumber;
		this.downloadNumber = downloadNumber;
		this.androidUrl = androidUrl;
		this.iosUrl = iosUrl;
		this.status = status;
		this.appCateId = appCateId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.appCate = appCate;
		this.appImgList = appImgList;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public String getImgAddress() {
		return imgAddress;
	}

	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPvs() {
		return pvs;
	}

	public void setPvs(Integer pvs) {
		this.pvs = pvs;
	}

	public Integer getUvs() {
		return uvs;
	}

	public void setUvs(Integer uvs) {
		this.uvs = uvs;
	}

	public Integer getIps() {
		return ips;
	}

	public void setIps(Integer ips) {
		this.ips = ips;
	}

	public Integer getReadingNumber() {
		return readingNumber;
	}

	public void setReadingNumber(Integer readingNumber) {
		this.readingNumber = readingNumber;
	}

	public Integer getDownloadNumber() {
		return downloadNumber;
	}

	public void setDownloadNumber(Integer downloadNumber) {
		this.downloadNumber = downloadNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAppCateId() {
		return appCateId;
	}

	public void setAppCateId(Integer appCateId) {
		this.appCateId = appCateId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public AppCate getAppCate() {
		return appCate;
	}

	public void setAppCate(AppCate appCate) {
		this.appCate = appCate;
	}

	public List<AppImg> getAppImgList() {
		return appImgList;
	}

	public void setAppImgList(List<AppImg> appImgList) {
		this.appImgList = appImgList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAndroidUrl() {
		return androidUrl;
	}


	public void setAndroidUrl(String androidUrl) {
		this.androidUrl = androidUrl;
	}


	public String getIosUrl() {
		return iosUrl;
	}


	public void setIosUrl(String iosUrl) {
		this.iosUrl = iosUrl;
	}

	
}
