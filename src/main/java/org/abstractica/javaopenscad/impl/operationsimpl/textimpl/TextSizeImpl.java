package org.abstractica.javaopenscad.impl.operationsimpl.textimpl;

import org.abstractica.javaopenscad.intf.text.TextSize;
import org.abstractica.javaopenscad.impl.ArgumentCollector;
import org.abstractica.javaopenscad.impl.HasArguments;

public class TextSizeImpl implements TextSize, HasArguments
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
