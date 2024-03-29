package org.abstractica.javaopenscad.impl.operationsimpl.textimpl;

import org.abstractica.javaopenscad.intf.text.OpenSCADTextSize;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.javaopenscad.impl.core.HasArguments;

public class TextSizeImpl implements OpenSCADTextSize, HasArguments
{
	private final double size;
	private final double spacing;

	public TextSizeImpl(double size, double spacing)
	{
		this.size = size;
		this.spacing = spacing;
	}

	@Override
	public double size()
	{
		return size;
	}

	@Override
	public double spacing()
	{
		return spacing;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		collector.add(size);
		collector.add(spacing);
	}
}
