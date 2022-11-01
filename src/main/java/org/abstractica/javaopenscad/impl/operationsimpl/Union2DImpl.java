package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.AGeometry2DFrom2D;
import org.abstractica.javaopenscad.impl.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class Union2DImpl extends AGeometry2DFrom2D
{
	public Union2DImpl() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("union()");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{}
}
