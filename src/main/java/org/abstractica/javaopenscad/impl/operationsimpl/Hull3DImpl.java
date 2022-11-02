package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry3DFrom3D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class Hull3DImpl extends AGeometry3DFrom3D
{
	public Hull3DImpl() {}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("hull()");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{}
}
