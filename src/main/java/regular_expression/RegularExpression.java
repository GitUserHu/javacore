package regular_expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 regular expression
 * @author Jiabing
 *
 */
public class RegularExpression {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String regex="((A+)(B(C)))";
		regex(regex);
	}
	
	public static void regex(String regex) {
		String input ="ABC-AABC-AAAAABCD";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		int groupCount = matcher.groupCount();
		//regex被圆括号分为四组，按照括号的出现位置，分别为：((A+)(B(C)))、(A+)、(B(C))、(C)
	    //匹，匹配的时候，还是按照A+BC 去搜索，只不过，每次匹配成功后，会将搜到的字符串按照上面的四组规则分为四个子串，存储起来
		while(matcher.find()) {
			System.out.print("匹配结果： ");
			for(int i=0;i<groupCount;i++){
	            System.out.print("group"+i+":"+matcher.group(i)+"\t");
	         }
			System.out.println();
		}
	}

}
