package com.nieyue.rabbitmq.confirmcallback;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.nieyue.bean.Acount;
import com.nieyue.bean.App;
import com.nieyue.bean.DailyData;
import com.nieyue.bean.Data;
import com.nieyue.bean.DataRabbitmqDTO;
import com.nieyue.business.AcountBusiness;
import com.nieyue.service.AppService;
import com.nieyue.service.DailyDataService;
import com.nieyue.service.DataService;
import com.nieyue.util.DateUtil;
import com.rabbitmq.client.Channel;


/**
 * 消息监听者
 * @author 聂跃
 * @date 2017年5月31日
 */
@Configuration  
public class Listener {
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Resource
	private AcountBusiness acountBusiness;
	@Resource
	private AppService appService;
	@Resource
	private DailyDataService dailyDataService;
	@Resource
	private DataService dataService;
	@Resource
	private Sender sender;
	@Value("${myPugin.projectName}")
	String projectName;
	    
	    /**
	     * web活动阅读 ip==阅读
	     * @param channel
	     * @param dataRabbitmqDTO
	     * @param message
	     * @throws IOException
	     */
		    @RabbitListener(queues="${myPugin.rabbitmq.APPWEBREAD_DIRECT_QUEUE}") 
		    public void AppWebRead(Channel channel, DataRabbitmqDTO dataRabbitmqDTO,Message message) throws IOException   {
		           try {
		        	  /**
		        	   * 判断是否存在
		        	   */
		        	 //如果活动不予统计  
		       		App inApp = appService.loadSmallApp(dataRabbitmqDTO.getAppId());
		       		if(inApp==null||inApp.equals("")){
		       		 channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		       		 return;
		       		}
		       		//如果账户不存在则用，1000
		       		Acount readinacount = acountBusiness.getAcountByAcountId(dataRabbitmqDTO.getAcountId());//阅读账户
		       		Acount inacount = acountBusiness.getAcountByAcountId(1000);//平台账户
		       		if(readinacount==null||readinacount.equals("")){
		       			readinacount = inacount;
		       		}
		     	   		/**
		        	    * 统计data
		        	    */
		       			//统计当日当前活动每日ip(总统计，做区别ip,保证不同acountId同一IP)
		        	   BoundSetOperations<String, String> bsodatips = stringRedisTemplate.boundSetOps(projectName+"AppId"+dataRabbitmqDTO.getAppId()+"Data"+DateUtil.getImgDir()+"Ips");
		        	   int isAddIp=0;//默认没增加
		        	   int oldIPSize = bsodatips.members().size();
		        	   bsodatips.add(dataRabbitmqDTO.getIp());//总ip
		        	   int nowIPSize = bsodatips.members().size();
		        	   if(nowIPSize>oldIPSize){
		        		   isAddIp=1;//增加了
		        	   }
		        	   //统计当日当前人的当前活动每日ip
		        	   BoundSetOperations<String, String> bsodataips = stringRedisTemplate.boundSetOps(projectName+"AcountId"+readinacount.getAcountId()+"AppId"+dataRabbitmqDTO.getAppId()+"Data"+DateUtil.getImgDir()+"Ips");
				        	   if(isAddIp==1){
				        	   bsodataips.add(dataRabbitmqDTO.getIp());//ip存入redis数据库
				        	   //bsodataips.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);//按天计算有用
				        	   }
		     	  	//三段时间数据
		   			Data realdata=new Data();
		   			//时间是3段:0-8,8-16,16-24
		   			realdata.setCreateDate(DateUtil.getDayPeriod(3));
		   			realdata.setAppId(dataRabbitmqDTO.getAppId());
		   			realdata.setAcountId(readinacount.getAcountId());
		   			dataService.saveOrUpdateData(realdata,dataRabbitmqDTO.getUv(), isAddIp,isAddIp);
		   			//日数据
		   			DailyData realdailydata=new DailyData();
		   			//时间是日
		   			realdailydata.setCreateDate(DateUtil.getStartTime());
		   			realdailydata.setAppId(dataRabbitmqDTO.getAppId());
		   			realdailydata.setAcountId(readinacount.getAcountId());
		   			dailyDataService.saveOrUpdateDailyData(realdailydata, dataRabbitmqDTO.getUv(), isAddIp, isAddIp);
		        	  /**
		        	   * 更新活动
		        	   */
		        	   //当前活动
		        	   App app = appService.loadSmallApp(dataRabbitmqDTO.getAppId());
		        	   app.setReadingNumber(app.getReadingNumber()+isAddIp);
		        	   app.setPvs(app.getPvs()+1);
		        	   app.setUvs(app.getUvs()+dataRabbitmqDTO.getUv());
		        	   app.setIps(app.getIps()+isAddIp);
		        	   appService.updateApp(app);//更新活动数据
		        	  channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					 try {
						channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
					} catch (IOException e1) {
						channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
						
						e1.printStackTrace();
					}
					//e.printStackTrace();
				} //确认消息成功消费 
		    }  
}
