package org.abstractica.javaopenscad.impl.core;

public interface ArgumentCollector
{
	void add(Class c);
	void add(boolean b);
	void add(int i);
	void add(double d);
	void add(String str);
	void add(HasArguments element);
}
