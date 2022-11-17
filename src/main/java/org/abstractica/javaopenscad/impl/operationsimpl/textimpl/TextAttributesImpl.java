package org.abstractica.javaopenscad.impl.operationsimpl.textimpl;

import org.abstractica.javaopenscad.intf.text.OpenSCADTextAlignment;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAttributes;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextFont;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextSize;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.javaopenscad.impl.core.HasArguments;

public class TextAttributesImpl implements OpenSCADTextAttributes, HasArguments
{
	private final TextFontImpl font;
	private final TextSizeImpl size;
	private final TextAlignmentImpl alignment;

	public TextAttributesImpl(TextFontImpl font, TextSizeImpl size, TextAlignmentImpl alignment)
	{
		this.font = font;
		this.size = size;
		this.alignment = alignment;
	}

	@Override
	public OpenSCADTextFont font()
	{
		return font;
	}

	@Override
	public OpenSCADTextSize size()
	{
		return size;
	}

	@Override
	public OpenSCADTextAlignment alignment()
	{
		return alignment;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		collector.add(font);
		collector.add(size);
		collector.add(alignment);
	}
}
