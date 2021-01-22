package util;
/**
 * 
 * @author JiaBing
 *
 */
public class Demo_YYS_CalculateDamage {
	public final static int basicAttackOfCM = 3216;
	public final static int basicAttackOfSFG = 1741;
	public static void main(String[] args) {
		int extralAttackOfCM,extralAttackOfSFG, totalAttackOfCM, totalAttachOfSFG;
		float critDamageOfCM, critDamageOfSFG;
		extralAttackOfCM = 4751;
		extralAttackOfSFG = 1541;
		critDamageOfCM = 2.01f;
		critDamageOfSFG = 2.03f;
		
		
		totalAttackOfCM = basicAttackOfCM + extralAttackOfCM;
		totalAttachOfSFG = basicAttackOfSFG + extralAttackOfSFG;
		System.out.println("茨木的伤害: "+ calculate(totalAttackOfCM, critDamageOfCM));
		System.out.println("食发鬼的伤害： "+ calculate(totalAttachOfSFG, critDamageOfSFG));
		
		

	}
	public static float calculate(final int attack,final float critDamage) {
		
		int a = attack;
		float b = critDamage;
		return a*b;
	}

}
