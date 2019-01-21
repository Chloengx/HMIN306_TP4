package hmin306.tp4.structure;

public class Triplet
{
	String classFromName;
	
	String classToName;
	
	int referencesCounter;
	
	public Triplet(String classFromName, String classToName)
	{
		this.classFromName = classFromName;
		
		this.classToName = classToName;
		
		referencesCounter = 1;
	}
	
	public void incrementReferences()
	{
		referencesCounter++;
	}

	public String getClassFromName()
	{
		return classFromName;
	}

	public String getClassToName()
	{
		return classToName;
	}

	public int getReferencesCounter()
	{
		return referencesCounter;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(!(object instanceof Triplet))
			return false;
		
		Triplet triplet = (Triplet) object;
		
		return triplet.classFromName.equals(classFromName) &&
				triplet.classToName.equals(classToName) &&
				triplet.referencesCounter == referencesCounter;
	}
	
	@Override
	public String toString()
	{
		StringBuilder toString = new StringBuilder();
		
		toString.append(classFromName);
		toString.append(" -> ");
		toString.append(classToName);
		toString.append(" (");
		toString.append(referencesCounter);
		toString.append(")");
		toString.append(System.getProperty("line.separator"));
		
		return toString.toString();
	}
}
