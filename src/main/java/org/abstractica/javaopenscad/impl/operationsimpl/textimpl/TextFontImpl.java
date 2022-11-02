package org.abstractica.javaopenscad.impl.operationsimpl.textimpl;

import org.abstractica.javaopenscad.intf.text.TextFont;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.javaopenscad.impl.core.HasArguments;

public class TextFontImpl implements TextFont, HasArguments
{
	private final String fontName;
	private final String fontStyle;
	private final String language;
	private final String script;

	public TextFontImpl(String fontName, String fontStyle, String language, String script)
	{
		this.fontName = fontName;
		this.fontStyle = fontStyle;
		this.language = language;
		this.script = script;
	}

	@Override
	public String fontName()
	{
		return fontName;
	}

	@Override
	public String fontStyle()
	{
		return fontStyle;
	}

	@Override
	public String language()
	{
		return language;
	}

	@Override
	public String script()
	{
		return script;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		collector.add(fontName);
		collector.add(fontStyle);
		collector.add(language);
		collector.add(script);
	}
}
