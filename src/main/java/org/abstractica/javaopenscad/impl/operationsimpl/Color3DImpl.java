package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.code.codebuilder.CodeBuilder;
import org.abstractica.javaopenscad.impl.core.AGeometry3DFrom3D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;

public class Color3DImpl extends AGeometry3DFrom3D
{
	private final double r;
	private final double g;
	private final double b;
	private final double a;

	public Color3DImpl(double r, double g, double b, double a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("color(c = [" + r + ", " + g + ", " + b + ", " + a +"])");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(r);
		collector.add(g);
		collector.add(b);
		collector.add(a);
	}
}
