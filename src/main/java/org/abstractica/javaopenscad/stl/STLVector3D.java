package org.abstractica.javaopenscad.stl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class STLVector3D
{
	public final float x;
	public final float y;
	public final float z;

	public STLVector3D(float x, float y, float z)
	{
		if(x == -0.0f) x = 0.0f;
		if(y == -0.0f) y = 0.0f;
		if(z == -0.0f) z = 0.0f;
		if(x == Float.NaN) throw new IllegalArgumentException("x is NaN");
		if(y == Float.NaN) throw new IllegalArgumentException("y is NaN");
		if(z == Float.NaN) throw new IllegalArgumentException("z is NaN");
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public STLVector3D(DataInput dataInput) throws IOException
	{
		x = readLittleEndianFloat(dataInput);
		y = readLittleEndianFloat(dataInput);
		z = readLittleEndianFloat(dataInput);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (!(o instanceof STLVector3D)) return false;

		STLVector3D that = (STLVector3D) o;
		if (Float.compare(that.x, x) != 0) return false;
		if (Float.compare(that.y, y) != 0) return false;
		return Float.compare(that.z, z) == 0;
	}

	@Override
	public int hashCode()
	{
		int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
		result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
		result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
		return result;
	}

	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ", " + z + ")";
	}

	public void writeTo(DataOutput out) throws IOException
	{
		writeLittleEndianFloat(x, out);
		writeLittleEndianFloat(y, out);
		writeLittleEndianFloat(z, out);
	}

	private static float readLittleEndianFloat(DataInput dataInput) throws IOException
	{
		byte[] bytes = new byte[4];
		dataInput.readFully(bytes);
		float res = Float.intBitsToFloat
				(
					(bytes[0] & 0xFF) |
					((bytes[1] & 0xFF) << 8) |
					((bytes[2] & 0xFF) << 16) |
					((bytes[3] & 0xFF) << 24)
				);
		return res;
	}

	private static void writeLittleEndianFloat(float f, DataOutput dataOutput) throws IOException
	{
		int i = Float.floatToIntBits(f);
		dataOutput.writeByte(i & 0xFF);
		dataOutput.writeByte((i >> 8) & 0xFF);
		dataOutput.writeByte((i >> 16) & 0xFF);
		dataOutput.writeByte((i >> 24) & 0xFF);
	}

}
