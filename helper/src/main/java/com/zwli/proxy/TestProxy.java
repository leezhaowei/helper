package com.zwli.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {

	public static void main(String[] args) {

		InvocationHandler handler = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if ("hello".equals(method.getName())) {
					System.out.println("Hello World!");
				} else if ("bye".equals(method.getName())) {
					System.out.println("Bye!");
				}
				return null;
			}
		};

		Foo f = null;

		// Class<?> proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(),
		// new Class[] { Foo.class });
		// try {
		// f = (Foo) proxyClass.getConstructor(new Class[] {
		// InvocationHandler.class })
		// .newInstance(new Object[] { handler });
		// f.hello();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(), new Class[] { Foo.class }, handler);
		f.hello();
		f.bye();
	}
}
