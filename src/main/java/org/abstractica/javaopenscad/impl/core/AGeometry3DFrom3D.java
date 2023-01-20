package org.abstractica.javaopenscad.impl.core;


import org.abstractica.javaopenscad.intf.OpenSCADGeometry3D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry3DFrom3D;

public abstract class AGeometry3DFrom3D extends AGeometryNode implements OpenSCADGeometry3DFrom3D
{
	protected AGeometry3DFrom3D() {}

	@Override
	public OpenSCADGeometry3DFrom3D add(OpenSCADGeometry3D child)
	{
		addChild((AGeometry) child);
		return this;
	}
}
