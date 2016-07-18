package testbean;

import org.springframework.stereotype.Component;

@Component
public class Person {
	@SuppressWarnings("unused")
	private String name;

	public String getName() {
		return "person-liujinbao";
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
