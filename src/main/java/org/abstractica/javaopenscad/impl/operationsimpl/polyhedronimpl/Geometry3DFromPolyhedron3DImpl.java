package org.abstractica.javaopenscad.impl.operationsimpl.polyhedronimpl;

import org.abstractica.code.codebuilder.CodeBuilder;
import org.abstractica.javaopenscad.impl.core.AGeometry3D;
import org.abstractica.javaopenscad.impl.core.ArgumentCollector;
import org.abstractica.javaopenscad.intf.*;

import java.util.List;

public class Geometry3DFromPolyhedron3DImpl extends AGeometry3D
{
	private final Polyhedron3DImpl polyhedron;

	public Geometry3DFromPolyhedron3DImpl(Polyhedron3D polyhedron)
	{
		this.polyhedron = (Polyhedron3DImpl) polyhedron;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		List<Vector3D> vertices = polyhedron.vertices();
		List<Path> paths = polyhedron.faces();
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
		for(int i = 0; i < vertices.size(); ++i)
		{
			Vector3D v = vertices.get(i);
			if(i > 0)
			{
				cb.print(", ");
			}
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
		boolean first2 = true;
		for(Path path : paths)
		{
			if(first2)
			{
				first2 = false;
			}
			else
			{
				cb.println(",");
			}
			cb.print("[");
			boolean first3 = true;
			for(Integer i : path.indexes())
			{
				if(first3)
				{
					first3 = false;
				}
				else
				{
					cb.print(",");
				}
				cb.print(Integer.toString(i));
			}
			cb.print("]");
			cb.println();
		}
		cb.undent();
		// <-
		cb.println("]");
		cb.print(", convexity = ");
		cb.println(Integer.toString(polyhedron.convexity()));
		cb.undent();
		// <
		cb.print(")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		polyhedron.collectArguments(collector);
	}
}
