package com.zwli.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxySample {

	public static void main(String[] args) {
		List<String> ary = new ArrayList<String>();
		ary.add("Hello");
		ary.add("Proxy");
		ary.add("World!!");

		ClassLoader loader = ProxySample.class.getClassLoader();
		@SuppressWarnings("unchecked")
		List<String> proxyAry = (List<String>) Proxy.newProxyInstance(loader, new Class<?>[] { List.class },
				new MyInvocationHandler(ary));
		for (int i = 0; i < 10; i++) {
			log(proxyAry.get(i));
		}
	}

	static class MyInvocationHandler implements InvocationHandler {

		private List<String> ary;

		public MyInvocationHandler(List<String> ary) {
			this.ary = ary;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (isFouthGet(method, args)) {
				return "Bow!!";
			} else if (moreGet(method, args)) {
				return new StringBuilder("HAHA ").append(args[0]).toString();
			}
			return method.invoke(ary, args);
		}

		private boolean moreGet(Method method, Object[] args) {
			return "get".equals(method.getName()) && Integer.valueOf(args[0].toString()).intValue() > 3;
		}

		private boolean isFouthGet(Method method, Object[] args) {
			return "get".equals(method.getName()) && ((Integer) args[0]) == 3;
		}
	}

	private static void log(Object msg) {
		System.out.println(msg);
	}
}
