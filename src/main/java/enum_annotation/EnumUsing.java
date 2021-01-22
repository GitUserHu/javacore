package enum_annotation;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author JiaBing
 * @date 2019-04-23 21:43:34 
 */
public class EnumUsing {

	public static void main(String[] args) {
		//EnumUsing.testEnum();
		System.out.println(FirstEnum.fromString("-"));
		System.out.println(FirstEnum.PLUS.ordinal()+1);
		// EnumSet 
		testEnumSet(EnumSet.of(FirstEnum.TIMES,FirstEnum.PLUS,FirstEnum.DIVIDE));
		Map<FirstEnum, Object> enumMap = new EnumMap<>(FirstEnum.class);
		// 液体到气体的转换
		System.out.println(Phase.Transition.transition(Phase.LIQUID, Phase.GAS));
		// A到B的转换
		System.out.println(Phase.Transition.transition(Phase.A, Phase.B));
	}
	public static void testEnum() {
		
		for (FirstEnum fe : FirstEnum.values()) {
			System.out.println(fe);
		}
		
	}
	public static void testEnumSet(Set<FirstEnum> enumSet) {
		Iterator<FirstEnum> iterator = enumSet.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
