/**
 * 
 */
package com.bccard.template.common.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @packageName : com.hoban.common.util
 * @fileName    : DateUtils.java
 * @author      : hslee
 * @date        : 2021.11.30
 * @description :
 * ===========================================================
 * DATE                       AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2021.11.30                 hslee       최초 생성
 */
public class DateUtils {

	public static String getNowDate() {
		return getNowDate("yyyyMMddHHmmss");
	}

	public static String getNowDate(String format) {
		Calendar now = Calendar.getInstance(Locale.KOREA);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String result = sdf.format(now.getTime());
		return result;
	}

	/**
	 * 현재시간을 java.util.Calendar 객체로 반환.
	 * @return java.util.Calendar 날짜 데이터
	 */
	public static Calendar currCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 문자열 날자 데이터를 java.util.Calendar 형태로 변환.
	 * <pre>
	 *  Calendar cal = DateUtils.parseCalendar("2003/03/26","yyyy/MM/dd");
	 * </pre>
	 * @see SimpleDateFormat
	 * @see Calendar
	 * @param  date     문자열 날짜 데이터
	 * @param  pattern  포맷팅 패턴 ( java.text.SimpleDateFormat 참조 )
	 * @return			Calendar 날짜 데이터
	 */
	public static Calendar parseCalendar(String date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		ParsePosition pos = new ParsePosition(0);
		Date ddate = formatter.parse(date, pos);
		Calendar rtnCal = currCalendar();
		rtnCal.setTime(ddate);
		return rtnCal;
	}

	/**
	 * 두 시각 사이의 지정한 형식의 차이를 구한다.
	 * <pre>
	 *  Calendar startCal = DateUtils.parseCalnedar("2003/03/26","yyyy/MM/dd");
	 *  Calendar endCal   = DateUtils.parseCalnedar("2003/09/07","yyyy/MM/dd");
	 *  int monthTerm = DateUtils.dateDiff('M' startCal, endCal); // 월차이
	 *  int dayTerm   = DateUtils.dateDiff('d' startCal, endCal); // 일차이
	 *  int secTerm   = DateUtils.dateDiff('s' startCal, endCal); // 초차이
	 * </pre>
	 * @see SimpleDateFormat
	 * @see Calendar
	 * @param  pattern 	'y','M','d','h','m','s'
	 * @param  from	    시작일시 Calendar 클래스
	 * @param  to		종료일시 Calendar 클래스
	 * @return			두 시간 사이의 지정한 형식의 차
	 */
	public static int dateDiff(char pattern, Calendar from, Calendar to) {
		int toYear = to.get(Calendar.YEAR);
		int fromYear = from.get(Calendar.YEAR);
		int diffYear = toYear - fromYear;
		if (pattern == 'y') {
			return diffYear;
		}
		int diffMonth = (diffYear * 12) + (to.get(Calendar.MONTH) - from.get(Calendar.MONTH));
		if (pattern == 'M') {
			return diffMonth;
		}
		long toLong = to.getTime().getTime();
		long fromLong = from.getTime().getTime();
		long diffLong = toLong - fromLong;
		if (pattern == 'h') {
			return (Long.valueOf(diffLong / (1000 * 60 * 60))).intValue();
		} else if (pattern == 'm') {
			return (Long.valueOf(diffLong / (1000 * 60))).intValue();
		} else if (pattern == 's') {
			return (Long.valueOf(diffLong / (1000))).intValue();
		}
		return (Long.valueOf(diffLong / (1000 * 60 * 60 * 24))).intValue();
	}

	/**
	 * 두 시각 사이의 지정한 형식의 차이를 구한다.
	 * <pre>
	 *  int monthTerm = DateUtils.dateDiff('M' "2003/03/26","yyyy/MM/dd","2003.09.07","yyyy.MM.dd"); // 월차이
	 *  int dayTerm   = DateUtils.dateDiff('d' "2003/03/26","yyyy/MM/dd","2003.09.07","yyyy.MM.dd"); // 일차이
	 *  int secTerm   = DateUtils.dateDiff('s' "2003/03/26","yyyy/MM/dd","2003.09.07","yyyy.MM.dd"); // 초차이
	 * </pre>
	 * @see SimpleDateFormat
	 * @param  pattern 	    'y','M','d','h','m','s'
	 * @param  from	        시작일시 java.util.Date 클래스
	 * @param  fromPattern  시작일시 문자열 날짜 형식
	 * @param  to		    종료일시 java.util.Date 클래스
	 * @param  toPattern    종료일시 문자열 날짜 형식
	 * @return			두 시간 사이의 지정한 형식의 차
	 */
	public static int dateDiff(char pattern, String from, String fromPattern, String to, String toPattern) {
		Calendar fromCal = parseCalendar(from, fromPattern);
		Calendar toCal = parseCalendar(to, toPattern);
		return dateDiff(pattern, fromCal, toCal);
	}

	/**
	 * 변경된 날짜를 구한다.
	 * <pre>
	 *  String monthTerm = DateUtils.dateDiff('y' 3,"yyyy.MM.dd"); // 년변경
	 *  String dayTerm   = DateUtils.dateDiff('M' 2,"yyyy.MM.dd"); // 월변경
	 *  String secTerm   = DateUtils.dateDiff('d' 1,"yyyy.MM.dd"); // 일변경
	 * </pre>
	 * @param  pattern 	    'y','M','d','h','m','s'
	 * @param  diff	     변경할 값
	 * @param  format      날짜 형식
	 * @return			변경된 날짜
	 */
	public static String getDiffDate(char pattern, int diff, String format) {
		Calendar now = Calendar.getInstance(Locale.KOREA);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String nowDate = sdf.format(now.getTime());

		return getDiffDate(pattern, diff, nowDate, format, format);
	}

	/**
	 * 변경된 날짜를 구한다.
	 * <pre>
	 *  String monthTerm = DateUtils.getDiffDate('y' 3,"2017/05/05","yyyy/MM/dd",yyyy.MM.dd"); // 년변경
	 *  String dayTerm   = DateUtils.getDiffDate('M' 2,"2017/05/05","yyyy/MM/dd","yyyy.MM.dd"); // 월변경
	 *  String secTerm   = DateUtils.getDiffDate('d' 1,"2017/05/05","yyyy/MM/dd","yyyy.MM.dd"); // 일변경
	 * </pre>
	 * @param  pattern 	    'y','M','d','h','m','s'
	 * @param  diff	     변경할 값
	 * @param  fromDate	   시작일자
	 * @param  fromFormat	  시작일자 형식
	 * @param  toFormat     변경일자 형식
	 * @return			변경일자
	 */
	public static String getDiffDate(char pattern, int diff, String fromDate, String fromFormat, String toFormat) {
		Calendar fromCal = parseCalendar(fromDate, fromFormat);

		int field = 0;
		if(pattern == 'y') {
			field = Calendar.YEAR;
		} else if(pattern == 'M') {
			field = Calendar.MONTH;
		} else if(pattern == 'd') {
			field = Calendar.DATE;
		} else if(pattern == 'H') {
			field = Calendar.HOUR;
		} else if(pattern == 'm') {
			field = Calendar.MINUTE;
		} else if(pattern == 's') {
			field = Calendar.SECOND;
		}
		fromCal.add(field, diff);

		SimpleDateFormat sdf = new SimpleDateFormat(toFormat);
		String result = sdf.format(fromCal.getTime());

		return result;
	}

	/**
	 * 해당 달의 마지막 날짜를 구한다.
	 * @param  baseDate         기준일자
	 * @return 마지막 날짜
	 */
	public static int getLastDate(String baseDate) {
		return getLastDate2(baseDate,"yyyyMMdd");
	}

	/**
	 * 해당 달의 마지막 날짜를 구한다.
	 * @param  baseDate         기준일자
	 * @param  baseFormat     기준일자 형식
	 * @return 마지막 날짜
	 */
	public static int getLastDate2(String baseDate, String baseFormat) {
		Calendar baseCal = parseCalendar(baseDate, baseFormat);
		int result = baseCal.getActualMaximum(Calendar.DATE);

		return result;
	}

	/**
	 * 해당 달의 첫번째 날짜를 구한다.
	 * @param  baseDate         기준일자
	 * @return 첫번째 날짜
	 */
	public static int getFirstDate(String baseDate) {
		return getFirstDate2(baseDate,"yyyyMMdd");
	}

	/**
	 * 해당 달의 첫번째 날짜를 구한다.
	 * @param  baseDate         기준일자
	 * @param  baseFormat     기준일자 형식
	 * @return 첫번째 날짜
	 */
	public static int getFirstDate2(String baseDate, String baseFormat) {

		Calendar baseCal = parseCalendar(baseDate, baseFormat);
		int result = baseCal.getActualMinimum(Calendar.DATE);

		return result;
	}

	/**
	 * 날짜 데이터를 문자열로 포맷팅한다.
	 * <pre>
	 *  String curDateFormated = DateUtils.format(System.currentTimeMillis(),"yyyy/MM/dd");
	 * </pre>
	 * @see SimpleDateFormat
	 * @param  date     long 날짜
	 * @param  pattern  포맷팅 패턴 ( java.text.SimpleDateFormat 참조 )
	 * @return			포맷팅된 문자열
	 */
	public static String format(long date, String pattern) {
		if( date <=0 ) return "";
		Date newDate = new Date(date);
		return format(newDate, pattern);
	}

	/**
	 * 날짜 데이터를 문자열로 포맷팅한다.
	 * <pre>
	 *  Date curDate = new Date( System.currentTimeMillis() );
	 *  String curDateFormated = DateUtils.format(curDate,"yyyy/MM/dd");
	 * </pre>
	 * @see SimpleDateFormat
	 * @see Date
	 * @param  date     java.util.Date 날짜 데이터
	 * @param  pattern  포맷팅 패턴 ( java.text.SimpleDateFormat 참조 )
	 * @return			포맷팅된 문자열
	 */
	public static String format(Date date, String pattern) {
		if( date == null ) return "";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * 날짜 데이터를 문자열로 포맷팅한다.
	 * <pre>
	 *  String date = "03.26.2017";
	 *  String curDateFormated = DateUtils.format(date,"MM.dd.yyyy","yyyy/MM/dd");
	 * </pre>
	 * @see SimpleDateFormat
	 * @param  date       날짜데이터
	 * @param  fromPattern 날짜데이터의 패턴 ( java.text.SimpleDateFormat 참조 )
	 * @param  toPattern    포맷팅 패턴 ( java.text.SimpleDateFormat 참조 )
	 * @return			  포맷팅된 문자열
	 */
	public static String format(String date, String fromPattern, String toPattern) {
		if( StringUtils.isNullOrBlank(date) ) return "";
		return format(parseDate(date, fromPattern), toPattern);
	}

	/**
	 * 날짜 데이터를 문자열로 포맷팅한다.
	 * <pre>
	 *  Calendar curCal = DateUtil.currCalendar();
	 *  String curDateFormated = DateUtil.format(curCal,"yyyy/MM/dd");
	 * </pre>
	 * @see SimpleDateFormat
	 * @see Calendar
	 * @param  date     java.util.Calendar 날짜 데이터
	 * @param  pattern  포맷팅 패턴 ( java.text.SimpleDateFormat 참조 )
	 * @return			포맷팅된 문자열
	 */
	public static String format(Calendar date, String pattern) {
		Date ddate = date.getTime();
		return format(ddate, pattern);
	}

	/**
	 * 문자열 날자 데이터를 java.util.Date 형태로 변환.
	 * <pre>
	 *  Date date = DateUtils.parseDate("2017/03/26","yyyy/MM/dd");
	 * </pre>
	 * @see SimpleDateFormat
	 * @see Date
	 * @param  date     문자열 날짜 데이터
	 * @param  pattern  포맷팅 패턴 ( java.text.SimpleDateFormat 참조 )
	 * @return			java.util.Date 날짜 데이터
	 */
	public static Date parseDate(String date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(date, pos);
	}

	/**
	 * LocalDateTime 변수를 Jullian Date long 으로 변환
	 * @param dateTime
	 * @return
	 */
	public static long convertDateToJde(LocalDateTime dateTime){
		if( dateTime == null ){
			return 0L;
		}

		Integer result = (dateTime.getYear() - 1900) * 1000 + dateTime.getDayOfYear();
		return result.longValue();
	}

	/**
	 * LocalDate 변수를 Jullian Date long 으로 변환
	 * @param date
	 * @return
	 */
	public static long convertDateToJde(LocalDate date){
		return convertDateToJde(LocalDateTime.of(date, LocalTime.now()));
	}

	/**
	 * 문자열 날짜 데이터를 Jullian Date long 으로 변환
	 * <pre>
	 *     long jdeDate = DateUtils.convertDateToJde("20171031", "yyyyMMdd");
	 * </pre>
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static long convertDateToJde(String dateStr, String pattern){
		Date input = parseDate(dateStr, pattern);
		LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return convertDateToJde(date);
	}


	/**
	 * 날짜의 (+)(-) 연산.
	 * <pre>
	 *  Calendar startCal = DateUtil.parseCalnedar("2017/03/26","yyyy/MM/dd");
	 *  Calendar endCal1  = DateUtil.dateAdd('d', 7, startCal);   // 7일후
	 *  Calendar endCal2  = DateUtil.dateAdd('M',-1, startCal);   // 한달전
	 * </pre>
	 * @see SimpleDateFormat
	 * @see Calendar
	 * @param  pattern 	'y','M','d','h','m','s'
	 * @param  amount	더하거나 뺄 값
	 * @param  objCal	캘린더 클래스
	 * @return			결과 캘린더 클래스
	 */
	public static Calendar dateAdd(char pattern, int amount, Calendar objCal) {
		if (pattern == 'y') {
			objCal.add(Calendar.YEAR, amount);
		} else if (pattern == 'M') {
			objCal.add(Calendar.MONTH, amount);
		} else if (pattern == 'h') {
			objCal.add(Calendar.HOUR_OF_DAY, amount);
		} else if (pattern == 'm') {
			objCal.add(Calendar.MINUTE, amount);
		} else if (pattern == 's') {
			objCal.add(Calendar.SECOND, amount);
		} else {
			objCal.add(Calendar.DAY_OF_MONTH, amount);
		}
		return objCal;
	}

	/**
	 * 날짜의 (+)(-) 연산.
	 * <pre>
	 *  Date startDate = DateUtil.parseDate("2017/03/26","yyyy/MM/dd");
	 *  Calendar endDate1 = DateUtil.dateAdd('d', 7, startDate);   // 7일후
	 *  Calendar endDate2 = DateUtil.dateAdd('M',-1, startDate);   // 한달전
	 * </pre>
	 * @see SimpleDateFormat
	 * @see Date
	 * @param  pattern 	'y','M','d','h','m','s'
	 * @param  amount	더하거나 뺄 값
	 * @param  objDate	java.util.Date 클래스
	 * @return			결과 java.util.Date 클래스
	 */
	public static Date dateAdd(char pattern, int amount, Date objDate) {
		Calendar objCal = currCalendar();
		objCal.setTime(objDate);
		return dateAdd(pattern, amount, objCal).getTime();
	}

	/**
	 * 날짜의 (+)(-) 연산.
	 * <pre>
	 *  String endDate1 = DateUtil.dateAdd('d', 7,"2017/03/26","yyyy/MM/dd"); // 7일후
	 *  String endDate2 = DateUtil.dateAdd('M',-1,"2017/03/26","yyyy/MM/dd"); // 한달전
	 * </pre>
	 * @see SimpleDateFormat
	 * @param  pattern 	    'y','M','d','h','m','s'
	 * @param  amount		더하거나 뺄 값
	 * @param  objDate      날짜 문자열
	 * @param  dPattern   	포맷
	 * @return			    결과 캘린더 클래스
	 */
	public static String dateAdd(char pattern, int amount, String objDate, String dPattern) {
		Calendar objCal = parseCalendar(objDate, dPattern);
		return format(dateAdd(pattern, amount, objCal), dPattern);
	}
}
