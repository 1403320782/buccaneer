package testinterface;

import testbean.User;
import buccaneer.utils.Reflections;

public class OOO extends Interfaces<User,Integer> {
	public static void main(String[] args) {
		System.out.println(Reflections.getSuperClassGenricType(OOO.class, 0));
	}
}
