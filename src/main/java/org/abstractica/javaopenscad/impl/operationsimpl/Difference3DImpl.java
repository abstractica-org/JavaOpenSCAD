package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.AGeometry3DFrom3D;
import org.abstractica.javaopenscad.impl.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class Difference3DImpl extends AGeometry3DFrom3D
{
	public Difference3DImpl() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("difference()");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{}
}
