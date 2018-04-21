package org.english.operation.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.english.operation.exception.BussinessException;

public class DateUtils {
	private static final DateFormat ymdDateFormat = new SimpleDateFormat("yyMMdd");
	private static final DateFormat ymdhmsDateFormat = new SimpleDateFormat("yyMMddHHmmss");
	private static final DateFormat ymdhmDateFormat = new SimpleDateFormat("yyMMddHHmm");
	
	/**
	 * 获取年月日，格式为20160101
	 * @return
	 */
	public static String getYMD(){
		return ymdDateFormat.format(new Date());
	}
	
	/**
	 * 获取年月日时分秒，格式为20160101121201
	 * @return
	 */
	public static String getYMDHMS(){
		return ymdhmsDateFormat.format(new Date());
	}
	
	/**
	 * 获取年月日时分，格式为201601011212
	 * @return
	 */
	public static String getYMDHM(){
		return ymdhmDateFormat.format(new Date());
	}

	/**
	 * 获取当前时间
	 */
	public static Date getNow(){ return new Date(); }

	/**
	 * 日期相加
	 * @param date 开始日期，如果是null则为当前时间
	 * @param nums 加时数 M = 分钟;h = 小时;d = 天数;m = 月
	 * @return 新日期
	 */
	public static Date addTime(Date date, String nums) throws BussinessException{
		if(StringUtils.isEmpty(nums)){
			throw new BussinessException("日期相加数不能为空");
		}
		String util = nums.substring(nums.length() - 1);
		try{
			Integer num = Integer.valueOf(nums.substring(0,nums.length()-1));
			Calendar calendar = Calendar.getInstance();
			if(date != null){
				calendar.setTime(date);
			}
			//分钟
			if("M".equals(util)){
				calendar.add(Calendar.MINUTE, num);
			}else if("h".equals(util)){
				calendar.add(Calendar.HOUR, num);
			}else if("d".equals(util)){
				calendar.add(Calendar.DAY_OF_MONTH, num);
			}else if("m".equals(util)){
				calendar.add(Calendar.MONTH, num);
			}
			return calendar.getTime();
		}catch (Exception e){
			throw new BussinessException(e);
		}
	}
	/**
	 * 相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int days(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
}
