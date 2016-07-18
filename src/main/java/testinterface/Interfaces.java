package testinterface;

import java.io.Serializable;

public class Interfaces<T,PK extends Serializable> {
	public void say(){
		System.out.println(getClass());
	}
}
