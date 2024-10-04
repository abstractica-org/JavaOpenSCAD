package org.abstractica.javaopenscad.tests;

import org.abstractica.javaopenscad.JavaOpenSCAD;
import org.abstractica.javaopenscad.impl.JavaOpenSCADImpl;
import org.abstractica.javaopenscad.impl.core.identifier.AllStrings;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry2D;
import org.abstractica.javaopenscad.intf.OpenSCADGeometry3D;
import org.abstractica.javaopenscad.intf.OpenSCADVector3D;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAlignment;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextAttributes;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextFont;
import org.abstractica.javaopenscad.intf.text.OpenSCADTextSize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestSaveStl
{
    public static void main(String[] args) throws IOException
    {
        JavaOpenSCAD os = new JavaOpenSCADImpl(true);

        List<OpenSCADVector3D> vertices = new ArrayList<>();
        vertices.add(os.vector3D(-1.0, -1.0, 0));
        vertices.add(os.vector3D(1.0, -1.0, 0));
        vertices.add(os.vector3D(1.0, 1.0, 0));
        vertices.add(os.vector3D(-1.0, 1.0, 0));
        vertices.add(os.vector3D(0, 0, 1));

        List<Iterable<Integer>> faces = new ArrayList<>();
        List<Integer> bottom = new ArrayList<>();
        bottom.add(0);
        bottom.add(1);
        bottom.add(2);
        bottom.add(3);
        faces.add(bottom);
        List<Integer> side1 = new ArrayList<>();
        side1.add(0);
        side1.add(1);
        side1.add(4);
        faces.add(side1);
        List<Integer> side2 = new ArrayList<>();
        side2.add(1);
        side2.add(2);
        side2.add(4);
        faces.add(side2);
        List<Integer> side3 = new ArrayList<>();
        side3.add(2);
        side3.add(3);
        side3.add(4);
        faces.add(side3);
        List<Integer> side4 = new ArrayList<>();
        side4.add(3);
        side4.add(0);
        side4.add(4);
        faces.add(side4);

        OpenSCADGeometry3D res = os.polyhedron3D(vertices, faces);
        os.generateOpenSCADFile("OpenSCAD/output.scad", res);
        os.saveSTL("OpenSCAD/output.stl", res);
    }
}
