package org.abstractica.javaopenscad.impl.core.identifier.test;

import org.abstractica.javaopenscad.impl.core.identifier.AllStrings;

import java.io.IOException;

public class TestAllStrings
{
	public static void main(String[] args) throws IOException
	{
		String fileName = "C:/Abstractica/Libraries/Java/JavaOpenSCAD/OpenSCAD/AllStrings/allStrings.txt";
		AllStrings.readFromFile(fileName);
		System.out.println("Loaded strings: ");
		System.out.println(AllStrings.listAllStrings());
		int id1 = AllStrings.id("Test1");
		int id2 = AllStrings.id("Test2");
		int id3 = AllStrings.id("Test3");
		int id4 = AllStrings.id("Test4");
		AllStrings.writeToFile(fileName);

	}
}
