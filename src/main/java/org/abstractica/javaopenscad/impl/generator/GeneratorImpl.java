package org.abstractica.javaopenscad.impl.generator;

import org.abstractica.javaopenscad.impl.AGeometry;
import org.abstractica.javaopenscad.impl.AModule;
import org.abstractica.code.codebuilder.CodeBuilder;

import java.util.HashMap;
import java.util.Map;


public class GeneratorImpl
{
	public void generate(CodeBuilder cb, AGeometry module)
	{
		Map<Integer, AModule> usedModules = new HashMap<>();
		Map<Integer, AModule> createdModules = new HashMap<>();
		module.generateCall(cb, usedModules);
		while(!usedModules.isEmpty())
		{
			Map<Integer, AModule> newUsedModules = new HashMap<>();
			for(AModule mod : usedModules.values())
			{
				mod.generateModule(cb, newUsedModules);
				createdModules.put(mod.id(), mod);
			}
			usedModules = new HashMap<>();
			for(AModule mod : newUsedModules.values())
			{
				if(!createdModules.containsKey(mod.id()))
				{
					usedModules.put(mod.id(), mod);
				}
			}
		}
	}
}
