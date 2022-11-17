package org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl;

import org.abstractica.javaopenscad.intf.OpenSCADPath;
import org.abstractica.javaopenscad.intf.OpenSCADPolygon2D;
import org.abstractica.javaopenscad.intf.OpenSCADVector2D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.javaopenscad.impl.core.HasArguments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polygon2DImpl implements OpenSCADPolygon2D, HasArguments
{
	private final List<OpenSCADVector2D> vertices;
	private final List<OpenSCADPath> paths;
	private final int convexity;

	public Polygon2DImpl(Iterable<OpenSCADVector2D> vertices, Iterable<OpenSCADPath> paths, int convexity)
	{
		this.convexity = convexity;
		List<OpenSCADVector2D> tmpVertices = new ArrayList<>();
		for(OpenSCADVector2D v : vertices)
		{
			if(!(v instanceof Vector2DImpl))
			{
				throw new IllegalArgumentException("Vector2D must be of internal type Vector2DImpl");
			}
			tmpVertices.add(v);
		}
		this.vertices = Collections.unmodifiableList(tmpVertices);

		if(paths == null)
		{
			this.paths = null;
		}
		else
		{
			List<OpenSCADPath> tmpPaths = new ArrayList<>();
			for (OpenSCADPath path : paths)
			{
				if (!(path instanceof PathImpl))
				{
					throw new IllegalArgumentException("Path must be of internal type PathImpl");
				}
				tmpPaths.add(path);
			}
			this.paths = Collections.unmodifiableList(tmpPaths);
		}
	}

	@Override
	public List<OpenSCADVector2D> vertices()
	{
		return vertices;
	}

	@Override
	public List<OpenSCADPath> paths()
	{
		return paths;
	}

	@Override
	public int convexity()
	{
		return convexity;
	}

	@Override
	public void collectArguments(ArgumentCollector collector)
	{
		for(OpenSCADVector2D v: vertices)
		{
			collector.add((Vector2DImpl) v);
		}
		if(paths != null)
		{
			for (OpenSCADPath path : paths)
			{
				collector.add((PathImpl) path);
			}
		}
	}
}
