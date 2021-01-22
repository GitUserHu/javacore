package enum_annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JiaBing
 * @date 2019-04-23 21:41:28 
 */
public enum FirstEnum {
	PLUS("+"),
	MINUS("-"),
	TIMES("*"),
	DIVIDE("/");
	private final String symbol;
	private static final Map<String, FirstEnum> stringToEnum = new HashMap<>();
	static {
		for (FirstEnum f : values()) {
			stringToEnum.put(f.toString(), f);
		}
	}
	FirstEnum(String symbol) {
		this.symbol = symbol;
	}
	@Override
	public String toString() {
		return this.symbol;
	}
	/**
	 * 根据symbol返回一个枚举实例
	 * @param symbol
	 * @return
	 */
	public static FirstEnum fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
	
}
