package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry3D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class LoadSTL3DImpl extends AGeometry3D
{
	private final String fileName;

	public LoadSTL3DImpl(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFileName()
	{
		return fileName;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("import(\"" + fileName + "\", convexity=30)");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(fileName);
	}
}
