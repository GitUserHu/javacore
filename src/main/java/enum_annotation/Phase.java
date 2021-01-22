package enum_annotation;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author JiaBing
 * @date 2019-04-24 22:55:11 
 * 使用 EnumMap 完成 固体，液体，气体的相互转换过程,当添加新的元素进去时，不用在乎元素之间的顺序，程序会自动处理其他所有的事情。如果用ordinal()来索引数组，就需要在乎元素的顺序
 * 比如添加两个新的状态： A,B. A->B 过程 ACTIONAB, B->A 过程 ACTIONBA
 */
public enum Phase {
	SOLID, LIQUID, GAS, A, B;
	public enum Transition{
		MELT(SOLID, LIQUID),// 融化(固体->液体);
		FREEZE(LIQUID, SOLID),// 凝固(液体->固体)
		BOIL(LIQUID, GAS),// 沸腾(液体->气体)
		CONDENSE(GAS, LIQUID),// 凝结(气体->液体)
		SUBLIME(SOLID, GAS),// 升华(固体->气体)
		DEPOSIT(GAS, SOLID),// （气体->固体）
		ACTIONAB(A, B),
		ACTIONBA(B, A);
		private final Phase from;
		private final Phase to;
		private static final Map<Phase, Map<Phase, Transition>> map = new EnumMap<>(Phase.class);
		private Transition(Phase from, Phase to) {
			this.from = from;
			this.to = to;
		}
		static {
			for (Phase from : Phase.values()) {
				map.put(from, new EnumMap<Phase, Phase.Transition>(Phase.class));
			}
			for (Transition t : Transition.values()) {
				map.get(t.from).put(t.to, t);
			}
		}
		
		public static String transition(Phase from, Phase to) {
			return from.name()+" to "+to.name()+":"+map.get(from).get(to).name();
		}
	}
}
