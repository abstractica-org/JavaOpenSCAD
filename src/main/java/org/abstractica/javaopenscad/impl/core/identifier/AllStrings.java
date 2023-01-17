package org.abstractica.javaopenscad.impl.core.identifier;

import org.abstractica.code.codebuilder.impl.CodeBuilderImpl;
import org.abstractica.code.codebuilder.textoutput.TextOutput;
import org.abstractica.code.codebuilder.textoutput.impl.StringBuilderTextOutput;
import org.abstractica.javaopenscad.impl.core.AGeometry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AllStrings
{
	private final static Map<String, Integer> allStrings = new HashMap<>();
	private static int stringCount = 0;

	public static int id(String str)
	{
		Integer id = allStrings.get(str);
		if(id == null)
		{
			allStrings.put(str, ++stringCount);
			id = stringCount;
		}
		return id;
	}

	public static boolean exists(String str)
	{
		return allStrings.containsKey(str);
	}

	public static void writeToFile(String fileName) throws IOException
	{
		java.nio.file.Path path = Paths.get(fileName);
		TextOutput out = new StringBuilderTextOutput();
		java.nio.file.Path parentDir = path.getParent();

		if (parentDir != null && !Files.exists(parentDir))
		{
			Files.createDirectories(parentDir);
		}
		Files.writeString(path, listAllStrings());
	}

	public static void readFromFile(String fileName)
	{
		java.nio.file.Path path = Paths.get(fileName);
		if(!Files.exists(path))
		{
			return;
		}
		String content = null;
		try
		{
			content = Files.readString(path);
		} catch (IOException e)
		{
			throw new RuntimeException("Could not read from file: " + fileName);
		}
		String[] lines = content.split("\\R");
		for(String line : lines)
		{
			String[] parts = line.split("\\$");
			if(parts.length == 2)
			{
				String nStr = parts[0];
				Integer n = Integer.parseInt(nStr);
				String str = parts[1];
				int id = id(str);
				if(id != n)
				{
					throw new RuntimeException("Error in the input file!");
				}
			}
		}

	}


	///////////////////////////////////////////////////////////////////////////////////////////
	// Debugging tools:
	//////////////////////////////////////////////////////////////////////////////////////////

	public static String listAllStrings()
	{
		StringBuilder res = new StringBuilder();
		List<UniqueString> uniqueStrings = new ArrayList<>();
		for(Map.Entry<String, Integer> entry : allStrings.entrySet())
		{
			UniqueString ustr = new UniqueString(entry.getKey(), entry.getValue());
			uniqueStrings.add(ustr);
		}
		Collections.sort(uniqueStrings);
		for(UniqueString uniqueStr : uniqueStrings)
		{
			res.append(uniqueStr.id);
			res.append("$");
			res.append(uniqueStr.str);
			res.append(System.lineSeparator());
		}
		return res.toString();
	}

	private static class UniqueString implements Comparable<UniqueString>
	{
		public final String str;
		public final int id;

		private UniqueString(String str, int id)
		{
			this.str = str;
			this.id = id;
		}

		@Override
		public int compareTo(UniqueString o)
		{
			return id - o.id;
		}
	}
}
