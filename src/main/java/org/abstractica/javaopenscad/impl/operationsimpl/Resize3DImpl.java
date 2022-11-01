package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.AGeometry3DFrom3D;
import org.abstractica.javaopenscad.impl.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

public class Resize3DImpl extends AGeometry3DFrom3D
{
	private final double x;
	private final double y;
	private final double z;
	private final boolean autoX;
	private final boolean autoY;
	private final boolean autoZ;

	public Resize3DImpl(double x, double y, double z, boolean autoX, boolean autoY, boolean autoZ)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.autoX = autoX;
		this.autoY = autoY;
		this.autoZ = autoZ;
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		collector.add(x);
		collector.add(y);
		collector.add(z);
		collector.add(autoX);
		collector.add(autoY);
		collector.add(autoZ);
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		cb.print("resize(newsize=[");
		cb.print(Double.toString(x));
		cb.print(",");
		cb.print(Double.toString(y));
		cb.print(",");
		cb.print(Double.toString(z));
		cb.print("], auto=[");
		cb.print(Boolean.toString(autoX));
		cb.print(",");
		cb.print(Boolean.toString(autoY));
		cb.print(",");
		cb.print(Boolean.toString(autoZ));
		cb.print("])");
	}


}
