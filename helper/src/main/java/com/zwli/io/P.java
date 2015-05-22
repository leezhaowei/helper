package com.zwli.io;

import static java.lang.System.*;

public class P {
	/**
	 * 打印后换行
	 * 
	 * @param s
	 */
	public static final void pln(String s) {
		out.println(s);
	}

	/**
	 * 打印后不换行
	 * 
	 * @param s
	 */
	public static final void a(String s) {
		out.println(s);
	}

	public static final void a() {
		out.println();
	}

	public static final void b(long l) {
		out.print(l);
	}

	public static final void a(long l) {
		out.println(l);
	}

	public static final void b(int i) {
		out.print(i);
	}

	public static final void a(int i) {
		out.println(i);
	}

	public static final void b(char c) {
		out.print(c);
	}

	public static final void a(char c) {
		out.println(c);
	}

	public static final void b(byte b) {
		out.print(b);
	}

	public static final void a(byte b) {
		out.println(b);
	}

	public static final void b(float f) {
		out.print(f);
	}

	public static final void a(float f) {
		out.println(f);
	}

	public static final void b(double d) {
		out.print(d);
	}

	public static final void a(double d) {
		out.println(d);
	}

	public static final void b(boolean b) {
		out.print(b);
	}

	public static final void a(boolean b) {
		out.println(b);
	}

	public static final void b(short s) {
		out.print(s);
	}

	public static final void a(short s) {
		out.println(s);
	}

	public static final void b(Object o) {
		out.print(o);
	}

	public static final void a(Object o) {
		out.println(o);
	}

	public static final void f(String format, Object... args) {
		out.printf(format, args);
	}
}
