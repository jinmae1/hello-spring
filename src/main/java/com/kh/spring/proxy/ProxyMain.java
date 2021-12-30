package com.kh.spring.proxy;

public class ProxyMain {

	public static void main(String[] args) {
//		Foo foo = new FooImpl();
//		Foo foo = new FooProxy(new FooImpl());
		// 위 두 문장은 결과가 동일

		Foo foo = new FooProxy(new FooImpl(), new Aspect()); // 우변은 Spring이 대신 해주는 부분
		System.out.println(foo.getName());
	}
}

class Aspect {
	public void before() {
		System.out.println("before");
	}
	public void after() {
		System.out.println("after");
	}
}

interface Foo {
	String getName();
}

class FooProxy implements Foo {
	
	Foo target;
	Aspect aspect;
	
	public FooProxy(Foo target, Aspect aspect) {
		this.target = target;
		this.aspect = aspect;
	}

	@Override
	public String getName() {
		aspect.before();
		String name = target.getName();
		aspect.after();
		return name;
	}
	
}

class FooImpl implements Foo {

	@Override
	public String getName() {
		System.out.println("getName 실행");
		return "Hong Gildong";
	}
}
