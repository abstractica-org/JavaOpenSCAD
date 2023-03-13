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

	public STLTriangle(STLVector3D v1, STLVector3D v2, STLVector3D v3)
	{
		this(getNormal(v1, v2, v3), v1, v2, v3);
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

	//Create vector cross product
	public static STLVector3D crossProduct(STLVector3D v1, STLVector3D v2)
	{
		float x = v1.y * v2.z - v1.z * v2.y;
		float y = v1.z * v2.x - v1.x * v2.z;
		float z = v1.x * v2.y - v1.y * v2.x;
		return new STLVector3D(x, y, z);
	}

	//Create vector length
	public static float length(STLVector3D v)
	{
		return (float) Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
	}

	//Create vector subtraction
	public static STLVector3D subtract(STLVector3D v1, STLVector3D v2)
	{
		return new STLVector3D(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
	}

	//Create vector normalization
	public static STLVector3D normalize(STLVector3D v)
	{
		float length = length(v);
		return new STLVector3D(v.x / length, v.y / length, v.z / length);
	}

	//Create triangle normal
	public static STLVector3D getNormal(STLVector3D v1, STLVector3D v2, STLVector3D v3)
	{
		STLVector3D v1v2 = subtract(v2, v1);
		STLVector3D v1v3 = subtract(v3, v1);
		STLVector3D normal = crossProduct(v1v2, v1v3);
		return normalize(normal);
	}

}
