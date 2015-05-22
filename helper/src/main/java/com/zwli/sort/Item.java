package com.zwli.sort;

public class Item implements Comparable<Item> {
	private String name;
	private int age;

	public Item(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean equals(Object obj) {
		if (getClass() == obj.getClass())
			return true;
		if (obj instanceof Item) {
			Item it = (Item) obj;
			if (getName().equals(it.getName()) && getAge() == it.getAge())
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "" + age;
		// return "name : " + name + ", age : " + age;
	}

	@Override
	public int hashCode() {
		return 31 * age + name.hashCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int compareTo(Item o) {
		int ra;
		if (getAge() != o.getAge()) {
			ra = (getAge() > o.getAge() ? 1 : -1);
		} else
			ra = 0;

		return ra;
	}

}