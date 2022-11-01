package org.abstractica.javaopenscad.impl.operationsimpl.polygonimpl;

import org.abstractica.javaopenscad.intf.polygon.Path;
import org.abstractica.javaopenscad.intf.polygon.Polygon2D;
import org.abstractica.javaopenscad.intf.polygon.Vector2D;
import org.abstractica.javaopenscad.impl.AGeometry2D;
import org.abstractica.javaopenscad.impl.ArgumentCollector;
import org.abstractica.code.codebuilder.CodeBuilder;

import java.util.List;

public class Geometry2DFromPolygon2DImpl extends AGeometry2D
{
	private final Polygon2DImpl polygon;

	public Geometry2DFromPolygon2DImpl(Polygon2D polygon)
	{
		this.polygon = (Polygon2DImpl) polygon;
	}

	@Override
	public void getCallHeader(CodeBuilder cb)
	{
		List<Vector2D> vertices = polygon.vertices();
		List<Path> paths = polygon.paths();
		//Vertices
		cb.println("polygon");
		cb.println("(");
		cb.indent();
		//-->>
		cb.println("points =");
		cb.println("[");
		cb.indent();
		for(int i = 0; i < vertices.size(); ++i)
		{
			Vector2D v = vertices.get(i);
			if(i > 0)
			{
				cb.print(", ");
			}
			cb.print("[");
			cb.print(Double.toString(v.x()));
			cb.print(", ");
			cb.print(Double.toString(v.y()));
			cb.print("]");
		}
		cb.println();
		cb.undent();
		cb.println("]");


		//Paths
		if(paths != null && paths.size() > 0)
		{
			cb.println(",");
			cb.println("paths =");
			cb.println("[");
			cb.indent();
			//-->>
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
			}
			cb.println();
			cb.undent();
			//<<--
			cb.println("]");
		}

		cb.undent();
		//<<--
		cb.print(")");
	}

	@Override
	public void getArguments(ArgumentCollector collector)
	{
		polygon.collectArguments(collector);
	}
}
