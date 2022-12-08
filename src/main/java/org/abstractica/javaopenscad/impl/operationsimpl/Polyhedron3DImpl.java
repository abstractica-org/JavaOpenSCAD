package org.abstractica.javaopenscad.impl.operationsimpl;

import org.abstractica.code.codebuilder.CodeBuilder;
import org.abstractica.javaopenscad.impl.core.*;
import org.abstractica.javaopenscad.intf.OpenSCADVector3D;

import java.util.List;

public class Polyhedron3DImpl extends AGeometry3D
{
	private final List<OpenSCADVector3D> vertices;
	private final List<List<Integer>> faces;

	public Polyhedron3DImpl(List<OpenSCADVector3D> vertices, List<List<Integer>> faces)
	{
		if(vertices == null) throw new IllegalArgumentException("vertices == null");
		if(faces == null) throw new IllegalArgumentException("faces == null");
		this.vertices = vertices;
		this.faces = faces;
	}

	@Override
	protected void getArguments(ArgumentCollector collector)
	{
		for(OpenSCADVector3D v: vertices)
		{
			collector.add(v.x());
			collector.add(v.y());
			collector.add(v.z());
		}

		for (List<Integer> face : faces)
		{
			for(Integer i : face)
			{
				collector.add(i);
			}
		}
	}

	@Override
	protected void getCallHeader(CodeBuilder cb)
	{
		//Vertices
		cb.println("polyhedron");
		cb.println("(");
		cb.indent();
		// ->
		// Points
		cb.println("points =");
		cb.println("[");
		cb.indent();
		// -->
		for(int i = 0; i < vertices.size(); i += 3)
		{
			OpenSCADVector3D v = vertices.get(i);
			if(i != 0) cb.print(", ");
			cb.print("[");
			cb.print(Double.toString(v.x()));
			cb.print(", ");
			cb.print(Double.toString(v.y()));
			cb.print(", ");
			cb.print(Double.toString(v.z()));
			cb.print("]");
		}
		cb.println();
		cb.undent();
		// <-
		cb.println("]");

		cb.println(",");

		//Faces
		cb.println("faces =");
		cb.println("[");
		cb.indent();
		// -->
		boolean first1 = true;
		for(List<Integer> face : faces)
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
}
