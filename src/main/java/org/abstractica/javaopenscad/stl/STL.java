package org.abstractica.javaopenscad.stl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class STL
{
	public static STL load(String fileName) throws IOException
	{
		Path path = Path.of(fileName);
		return load(path);
	}

	public static STL load(Path path) throws IOException
	{
		InputStream in = Files.newInputStream(path);
		DataInput dataInput = new DataInputStream(in);
		//Skip 80 bytes header
		dataInput.skipBytes(80);
		int nTriangles = readLittleEndianInt(dataInput);
		STL res = new STL();
		for(int i = 0; i < nTriangles; i++)
		{
			STLTriangle triangle = new STLTriangle(dataInput);
			res.addTriangle(triangle);
		}
		return res;
	}


	private final List<STLTriangle> triangles;
	private STLVector3D min;
	private STLVector3D max;

	public STL()
	{
		min = null;
		max = null;
		this.triangles = new ArrayList<>();
	}

	public void addTriangle(STLTriangle triangle)
	{
		triangles.add(triangle);
	}

	public List<STLTriangle> getTriangles()
	{
		return triangles;
	}

	public STLVector3D getMin()
	{
		if(min == null)
		{
			calculateMinAndMax();
		}
		return min;
	}

	public STLVector3D getMax()
	{
		if(max == null)
		{
			calculateMinAndMax();
		}
		return max;
	}

	public void save(String fileName) throws IOException
	{
		Path path = Path.of(fileName);
		save(path);
	}

	public void save(Path path) throws IOException
	{
		OutputStream out = Files.newOutputStream(path);
		DataOutput dataOut = new DataOutputStream(out);
		//Write 80 bytes header
		for(int i = 0; i < 80; i++)
		{
			dataOut.writeByte(0);
		}
		//Write number of triangles
		writeLittleEndianInt(triangles.size(), dataOut);
		for(STLTriangle triangle : triangles)
		{
			triangle.writeTo(dataOut);
		}
	}

	@Override
	public String toString()
	{
		StringBuilder res = new StringBuilder();
		res.append("STLFile: number of triangles = ");
		res.append(triangles.size());
		res.append("\n");
		for(STLTriangle triangle : triangles)
		{
			res.append(triangle);
			res.append("\n");
		}
		return res.toString();
	}

	private static void writeLittleEndianInt(int value, DataOutput dataOutput) throws IOException
	{
		for(int i = 0; i < 4; i++)
		{
			dataOutput.writeByte(value >> (i * 8));
		}
	}

	private static int readLittleEndianInt(DataInput in) throws IOException
	{
		int res = 0;
		for(int i = 0; i < 4; i++)
		{
			res += (in.readUnsignedByte() << (i * 8));
		}
		return res;
	}

	private void calculateMinAndMax()
	{
		float minX = Float.MAX_VALUE;
		float minY = Float.MAX_VALUE;
		float minZ = Float.MAX_VALUE;
		float maxX = -Float.MAX_VALUE;
		float maxY = -Float.MAX_VALUE;
		float maxZ = -Float.MAX_VALUE;
		for(STLTriangle triangle : triangles)
		{
			if(triangle.v1.x < minX) minX = triangle.v1.x;
			if(triangle.v1.y < minY) minY = triangle.v1.y;
			if(triangle.v1.z < minZ) minZ = triangle.v1.z;
			if(triangle.v1.x > maxX) maxX = triangle.v1.x;
			if(triangle.v1.y > maxY) maxY = triangle.v1.y;
			if(triangle.v1.z > maxZ) maxZ = triangle.v1.z;
			if(triangle.v2.x < minX) minX = triangle.v2.x;
			if(triangle.v2.y < minY) minY = triangle.v2.y;
			if(triangle.v2.z < minZ) minZ = triangle.v2.z;
			if(triangle.v2.x > maxX) maxX = triangle.v2.x;
			if(triangle.v2.y > maxY) maxY = triangle.v2.y;
			if(triangle.v2.z > maxZ) maxZ = triangle.v2.z;
			if(triangle.v3.x < minX) minX = triangle.v3.x;
			if(triangle.v3.y < minY) minY = triangle.v3.y;
			if(triangle.v3.z < minZ) minZ = triangle.v3.z;
			if(triangle.v3.x > maxX) maxX = triangle.v3.x;
			if(triangle.v3.y > maxY) maxY = triangle.v3.y;
			if(triangle.v3.z > maxZ) maxZ = triangle.v3.z;
		}
		min = new STLVector3D(minX, minY, minZ);
		max = new STLVector3D(maxX, maxY, maxZ);
	}
}
