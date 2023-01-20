package org.abstractica.javaopenscad.stl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class STLTriangle
{
	public final STLVector3D normal;
	public final STLVector3D v1;
	public final STLVector3D v2;
	public final STLVector3D v3;

	public STLTriangle(STLVector3D normal, STLVector3D v1, STLVector3D v2, STLVector3D v3)
	{
		this.normal = normal;
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	public STLTriangle(DataInput di) throws IOException
	{
		normal = new STLVector3D(di);
		v1 = new STLVector3D(di);
		v2 = new STLVector3D(di);
		v3 = new STLVector3D(di);
		di.skipBytes(2);
	}

	@Override
	public String toString()
	{
		return "Triangle: \n    norm = " + normal + "\n    v1 = " + v1 + "\n    v2 = " + v2 + "\n    v3 = " + v3 + "\n";
	}

	public void writeTo(DataOutput out) throws IOException
	{
		normal.writeTo(out);
		v1.writeTo(out);
		v2.writeTo(out);
		v3.writeTo(out);
		out.writeShort(0);
	}

}
