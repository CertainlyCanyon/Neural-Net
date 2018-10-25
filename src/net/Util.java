package net;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Util {

	public Util() {
		// TODO Auto-generated constructor stub
	}
	
	public static int countOccurences(String s, String r)
	{
		return (s.length() - s.replace(r, "").length());
	}
	
	public static Prism[] readFilterArray(String filePath)
	{
	    //Filter aray comes in form: number of filters =[filter1]=[Filter2]=...=[FilterN]
	    
		try
		{
			File file = new File(filePath);
			Scanner scan = new Scanner(file).useDelimiter("=");
			
			Prism[] out = new Prism[Integer.parseInt(scan.next())];
			int i = 0;
			while(scan.hasNext())
			{
				out[i] = new Prism("$$"+scan.next());
				i++;
			}
			
			return out;
			
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("ERROR: in Util.java - input file not found - " + filePath);
		} catch (Exception e)
		{
			System.out.println("ERROR: in Util.java - could not init array given filepath - " + filePath);
			e.printStackTrace();
		}
		return null;
	}
	
	public static void writeFilterArray(Prism[] in, String filePath)
	{
		try{
		    PrintWriter writer = new PrintWriter(filePath, "UTF-8");
		    writer.print(in.length);
		    for(int i = 0; i < in.length; i++)
		    {
		    	 writer.print("=" + in[i].toString());
		    }
		    
		    writer.close();
		} catch (Exception e) {
			System.err.println("Caught Exception in Util.java-writeFilterArray: " + e.getMessage());
		}
	}

}
