package com.security.mvc.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
/**
 * 
*  @Description: 常用转换工具
*  @version Revision: V1.0 2014-12-11 上午12:23:46
*  @author GuoJun mailto: guojun828@126.com
 */
public class ConvertUtil {
	public static String toStr(Object o) {
		return o == null ? "" : o.toString();
	}

	public static Integer toInt(Object o) {
		return o == null || o.equals("") ? null : Integer
				.parseInt(o.toString());
	}

	public static Long toLong(Object o) {
		return o == null || o.equals("") ? null : Long.parseLong(o.toString());
	}

	public static Double toDouble(Object o) {
		return o == null || o.equals("") ? null : Double.parseDouble(o
				.toString());
	}

	public static BigDecimal toBigDecimal(Object o) {
		return o == null || o.equals("") ? null : BigDecimal.valueOf(Double
				.parseDouble(o.toString()));
	}

	public static Date toDate(Object o) throws ParseException {
		return o == null || o.equals("") ? null : new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").parse(o.toString());
	}
	public static Date toDateMillSeconds(Object o) throws ParseException
	{
		return o == null || o.equals("") ? null : new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss.SSS").parse(o.toString());
	}
	public static Date toDateYY(Object o) throws ParseException {
		return o == null || o.equals("") ? null : new SimpleDateFormat(
				"yyyy-MM-dd").parse(o.toString());
	}

	public static Date toDate(String str, String format) throws ParseException {
		return str == null || str.equals("") ? null : new SimpleDateFormat(
				format).parse(str);
	}

	public static Date toDate(Object o, SimpleDateFormat sdf)
			throws ParseException {
		return o == null || o.equals("") ? null : sdf.parse(o.toString());
	}

	public static String toDateStr(Object o) throws ParseException {
		return o == null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format((Date) o);
	}

	public static String toYMDStr(Object o) throws ParseException {
		return o == null ? null : new SimpleDateFormat("yyyy-MM-dd")
				.format((Date) o);
	}
	
	/**
	 * 'yyyy-MM-dd'类型日期字符串转换为Date类型
	 */
	public static Date toYMDDate(String str) throws ParseException {
		return StringUtils.isBlank(str) ? null : new SimpleDateFormat(
				"yyyy-MM-dd").parse(str);
	}
	
	public static java.sql.Date toYMDDate(String str, String format) throws ParseException {
		 java.util.Date date =str == null || str.equals("") ? null : new SimpleDateFormat(
				format).parse(str);
		return new java.sql.Date(date.getTime());
	}
	public static java.sql.Date timestampToDate(java.util.Date date){
		return new java.sql.Date(date.getTime());
	}
	public static java.sql.Timestamp dateToTimestamp(java.util.Date date){
		return new Timestamp(date.getTime());
	}
	public static String toYMDStr(Object o, SimpleDateFormat sdf)
			throws ParseException {
		return o == null ? null : sdf.format((Date) o);
	}

	public static String toDateStr(Object o, SimpleDateFormat sdf)
			throws ParseException {
		return o == null ? null : sdf.format((Date) o);
	}

	public static String getStr(Map<String, Object> map, Object key) {
		Object value = null;

		if (key != null && map.containsKey(key.toString()))
			value = map.get(key.toString());

		return value == null ? "" : value.toString();
	}

	public static Integer getTimeRange(Date begin, Date end) {
		Integer minutes = null;
		if (begin == null)
			return minutes;

		if (end == null)
			end = new Date();

		long ms = end.getTime() - begin.getTime();
		minutes = (int) (ms / 1000 / 60);

		return minutes;
	}

	/**
	 * @Description: 为date增加特定天数
	 * @param�? d：需要处理的时间   n：需要增加的天数
	 * @return: 增加n天之后的d
	 * @date : 2013-1-15 上午09:38:32
	 * @author�? jijunsheng; E-mail: jijs@highcolu.com
	 */
	public static Date addSpecifiedDays(Date d, int n){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");   
		//String date = format.format(d);   
		//System.out.println("现在的日期是�?" + date);    
		Calendar ca = Calendar.getInstance();   
		ca.setTime(d);
		ca.add(Calendar.DATE, n);
		d = ca.getTime();   
		String backTime = format.format(d);
		Date resDate = null;
		try {
			resDate = toDate(backTime, format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//System.out.println("增加天数以后的日期：" + backTime);
		return resDate;
	}
	
	/**
	 * 保留兩位小數
	 * @param val
	 * @return
	 */
	public static String saveTwoDecimal (Object val){
		if(val!=null){
			DecimalFormat df = new DecimalFormat("0.00");
			String temp=df.format(val).toString();
			return temp.equals("0.00")?"0":temp;
		}
		return null;
	}
	public static Double saveTwoDecimalDouble(Object val){
		if(val!=null){
			DecimalFormat df = new DecimalFormat("0.00");
			String temp=df.format(val).toString();
			return Double.parseDouble(temp);
		}
		return null;
	}
	public static Double saveZeroDecimalDouble(Double val)
	{
		if(val!=null){
			return new BigDecimal(val).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return null;
	}
	/** 设置目标月份之后的日�?
	 * eg. 假如当前日期�?2013-11-26,得到三个月之后的日期�?2014-02-26
	* @param baseDate 
	* 		基准日期String (格式yyyy-MM-dd)
	* @param amount 
	* 		要增加的月份（负数为减）
	* @return 
	* 		目标月份之后的日�?
	*/
	public static String setTargetMonth(String baseDate,int amount )
	{
	   StringBuffer sb = new StringBuffer();
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); // 将字符串格式�?
	   Date dt=sdf.parse(baseDate,new ParsePosition(0)); // 由格式化后的字符串产生一个Date对象
	  
	   Calendar c = Calendar.getInstance(); // 初始化一个Calendar
	   c.setTime(dt); // 设置基准日期
	   c.add(Calendar.MONTH, amount); 
	   Date dt1=c.getTime(); // 从Calendar对象获得基准日期增减后的日期
	   sb=sdf.format(dt1,sb,new FieldPosition(0)); // 得到结果字符�?
	   return sb.toString();
	}
	/**
	 * 计算两个日期的时间差,单位为天
	 * @param 
	 * 	currentDate 当前日期
	 * @param 
	 * 	targetDate 目标日期
	 * @return 两个日期的相差天�?
	 * @throws ParseException
	 */
	public static Integer calculateStrandedDays(Date d1,Date d2) throws ParseException
	{
		long diff = d1.getTime() - d2.getTime();
		Long days =(diff / (1000 * 60 * 60 * 24));
		return Integer.parseInt(days.toString());
	}
	/**
	 * 转换为千分位显示eg:10000==>10,000
	 * @param amount 金额
	 * @return
	 */
	public static String fmtMicrometer(Double amount)  
	{  
	    DecimalFormat df = null; 
	    String text=String.valueOf(amount);
	    if(text.indexOf(".") > 0)  
	    {  
	        if(text.length() - text.indexOf(".")-1 == 0)  
	        {  
	            df = new DecimalFormat("###,##0.");  
	        }else if(text.length() - text.indexOf(".")-1 == 1)  
	        {  
	            df = new DecimalFormat("###,##0.0");  
	        }else  
	        {  
	            df = new DecimalFormat("###,##0.00");  
	        }  
	    }else   
	    {  
	        df = new DecimalFormat("###,##0");  
	    }  
	    double number = 0.0;  
	    try {  
	         number = Double.parseDouble(text);  
	    } catch (Exception e) {  
	        number = 0.0;  
	    }  
	    return df.format(number);  
	} 
	/**
	 * 取得上个月的�?后一天的日期
	 * @return 
	 * 			上个月的�?后一天日�?
	 */
	public static String getLastMonthLastDay()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
		return sdf.format(calendar.getTime());  
	}
	/**
	 * 
	 * @param baseDate 基础日期
	 * @param monthsQuatity 基础日期月份之前的月份数�?
	 * @return eg.2013-11-01
	 */
	public static String getLastQuatityMonth(String baseDate,int monthsQuatity)
	{
		String[] items = baseDate.split("-");  
		int year  = Integer.parseInt(items[0]);  
		int month = Integer.parseInt(items[1]);  
		if((month - monthsQuatity) <= 0){  
		    month = month + 12 - monthsQuatity;  
		    year = year -1;  
		}else {  
		    month = month - monthsQuatity;  
		}  
		return year + "-" + (month+1) + "-" + 01; 
	}
}
