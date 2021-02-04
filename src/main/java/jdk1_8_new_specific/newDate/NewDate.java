package jdk1_8_new_specific.newDate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * JDK1.8 新的时间工具类     测试使用
 * @author Jiabing
 *
 */
public class NewDate {

	public static void main(String[] args) {
		//获取当前日期
		LocalDate now = LocalDate.now();
		System.out.println(now.toString());
		//指定一个日期
		LocalDate of = LocalDate.of(2012, 9, 9);
		System.out.println(of.toString());
		//30天之前
		LocalDate plusDays = now.minusDays(30);
		System.out.println(plusDays);
		//30天之后
		System.out.println(now.plusDays(30).toString());
		
		System.out.println(ZonedDateTime.now(ZoneId.of("Europe/Paris")).toString());
		
		System.out.println(new Date(new Long("1545819060000")));
		System.out.println(new Date(new Long("1545818460000")));

	}

}
