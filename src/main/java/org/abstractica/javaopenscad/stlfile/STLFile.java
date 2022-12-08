package org.abstractica.javaopenscad.stlfile;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class STLFile
{
	public static STLFile load(String fileName) throws IOException
	{
		Path path = Path.of(fileName);
		return load(path);
	}

	private static STLFile load(Path path) throws IOException
	{
		InputStream in = Files.newInputStream(path);
		DataInput di = new DataInputStream(in);
		//Skip 80 bytes header
		di.skipBytes(80);
		int nTriangles = di.readInt();
		List<STLTriangle> triangles = new ArrayList<>(nTriangles);
		for(int i = 0; i < nTriangles; i++)
		{
			STLTriangle triangle = new STLTriangle(di);
			triangles.add(triangle);
		}
		return new STLFile(Collections.unmodifiableList(triangles));
	}

	private final List<STLTriangle> triangles;

	public List<STLTriangle> getTriangles()
	{
		return triangles;
	}

	private STLFile(List<STLTriangle> triangles)
	{
		this.triangles = triangles;
	}

	private static class STLTriangle
	{
		private final STLVector3D normal;
		private final STLVector3D v1;
		private final STLVector3D v2;
		private final STLVector3D v3;

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

	}

	private static class STLVector3D
	{
		private final float x;
		private final float y;
		private final float z;

		public STLVector3D(float x, float y, float z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public STLVector3D(DataInput di) throws IOException
		{
			x = di.readFloat();
			y = di.readFloat();
			z = di.readFloat();
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

		public float x()
		{
			return x;
		}

		public float y()
		{
			return y;
		}

		public float z()
		{
			return z;
		}

	}
}