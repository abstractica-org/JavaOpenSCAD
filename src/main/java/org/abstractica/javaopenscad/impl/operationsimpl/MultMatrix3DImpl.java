package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.code.codebuilder.CodeBuilder;
import org.abstractica.javaopenscad.impl.core.AGeometry3DFrom3D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;

public class MultMatrix3DImpl extends AGeometry3DFrom3D
{
	private final double m00, m01, m02, m03;
	private final double m10, m11, m12, m13;
	private final double m20, m21, m22, m23;

	public MultMatrix3DImpl(
			double m00, double m01, double m02, double m03,
			double m10, double m11, double m12, double m13,
			double m20, double m21, double m22, double m23  )
	{
		this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
		this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
		this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
	}


	@Override
	protected void getArguments(ArgumentCollector collector)
	{
		collector.add(m00); collector.add(m01); collector.add(m02); collector.add(m03);
		collector.add(m10); collector.add(m11); collector.add(m12); collector.add(m13);
		collector.add(m20); collector.add(m21); collector.add(m22); collector.add(m23);
	}

	@Override
	protected void getCallHeader(CodeBuilder cb)
	{
		cb.println("multmatrix([");
		cb.println("    [" + m00 + ", " + m01 + ", " + m02 + ", " + m03 + "],");
		cb.println("    [" + m10 + ", " + m11 + ", " + m12 + ", " + m13 + "],");
		cb.println("    [" + m20 + ", " + m21 + ", " + m22 + ", " + m23 + "],");
		cb.println("    [0, 0, 0, 1]");
		cb.print("])");
	}
}
