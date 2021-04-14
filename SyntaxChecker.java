import java.io.*;
import java.util.*;

public class SyntaxChecker {

	public static void main(String[] args)
	{

		Scanner kb = new Scanner(System.in);
		Scanner file = null;
		while(file == null)
			try
			{
				System.out.print("Enter a file name <or press Enter to quit>: ");
				String fileName = kb.nextLine();
				if(fileName.length() == 0)
				{
					System.out.println("Exiting");
					System.exit(0);
				}
				file = new Scanner(new File(fileName));
			}
			catch(Exception e)
			{
				System.out.println("File not Found - try again");
			}

	
		Stack<Bracket> s = new Stack<>();
		int lineNumber = 1;
		boolean hasFailed = false;
		while(file.hasNext())
		{
			String lineOfCode = file.nextLine();
			
			{
				String str = lineOfCode.charAt(i)+"";
				if(OPEN.contains(str))
				{
					s.add(new Bracket(str,lineNumber));
				}
				if(CLOSE.contains(str))
				{
					int closeSpot = CLOSE.indexOf(str);
					if(s.size() != 0)
					{
						Bracket b = s.pop();
						int openSpot = OPEN.indexOf(b.getBracketType());
						if(closeSpot != openSpot)
						{
							System.out.println("Type Mismatch. Found " + b.getBracketType() + " expected " + OPEN.charAt(closeSpot) + " at line: " + b.getLineNumber());
							hasFailed = true;
						}
					}
					else
					{
						System.out.println("Too many " + str + " without " + OPEN.charAt(closeSpot) + " at line: "+lineNumber);
						hasFailed = true;
					}

				}
			}
			lineNumber++;
		}
	
		{
			Bracket b = s.pop();
			System.out.println("Too many " + b.getBracketType() + " at line: " + b.getLineNumber());
		}
		if(!hasFailed)
			System.out.println("Good File");

	}


}