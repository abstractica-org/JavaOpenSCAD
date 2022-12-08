package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.javaopenscad.impl.core.AGeometry2D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;
import org.abstractica.javaopenscad.intf.OpenSCADVector2D;

import java.util.List;

public class Polygon2DImpl extends AGeometry2D
{
	private final List<OpenSCADVector2D> vertices;
	private final List<List<Integer>> paths;

	public Polygon2DImpl(List<OpenSCADVector2D> vertices, List<List<Integer>> paths)
	{
		if(paths == null) throw new IllegalArgumentException("paths == null");
		if(vertices == null) throw new IllegalArgumentException("vertices == null");
		this.vertices = vertices;
		this.paths = paths;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		//Vertices
		cb.println("polygon");
		cb.println("(");
		cb.indent();
		//-->>
		cb.println("points =");
		cb.println("[");
		cb.indent();
		//-->>
		for(int i = 0; i < vertices.size(); ++i)
		{
			OpenSCADVector2D v = vertices.get(i);
			cb.print("[" + v.x() + ", " + v.y() + "]");
			if(i < vertices.size() - 1)
			{
				cb.println(", ");
			}
		}
		cb.println();
		cb.undent();
		cb.println("],");

		//Paths
		cb.println("paths =");
		cb.println("[");
		cb.indent();
		// -->
		boolean first1 = true;
		for(List<Integer> face : paths)
		{
			if(first1)
			{
				first1 = false;
			}
			else
			{
				cb.println(", ");
			}
			cb.print("[");
			boolean first2 = true;
			for(Integer i : face)
			{
				if(first2)
				{
					first2 = false;
				}
				else
				{
					cb.print(", ");
				}
				cb.print(Integer.toString(i));
			}
			cb.print("]");
			cb.println();
		}
		cb.undent();
		// <-
		cb.println("]");
		cb.undent();
		// <
		cb.print(")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		for(OpenSCADVector2D v: vertices)
		{
			collector.add(v.x());
			collector.add(v.y());
		}

		for (List<Integer> path : paths)
		{
			for(Integer i : path)
			{
				collector.add(i);
			}
		}
	}
}
