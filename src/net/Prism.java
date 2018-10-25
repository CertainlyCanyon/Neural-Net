package net;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Prism {

	int height;
	int width;
	int depth;
	double[][][] prism;
	
	public Prism(int width, int height, int depth) {
		prism = new double[height][width][depth];
		this.height = height;
		this.width = width;
		this.depth = depth;
	}
	
	public Prism(int width, int height, int depth, int initValue) {
        prism = new double[height][width][depth];
        this.height = height;
        this.width = width;
        this.depth = depth;
        
        init(initValue);
    }
	
	public Prism(String filePath)
	{
		int tHeight = 0;
		int tWidth = 0;
		int tDepth = 0;
		
		try{
			Scanner scan;
			
			if(filePath.startsWith("$$"))
			{
				scan = new Scanner(filePath.substring(2));
			} else {
				File file = new File(filePath);
				scan = new Scanner(file);
			}
			
			tHeight += 1;
			String tempS = scan.nextLine();
			tWidth = Util.countOccurences(tempS, "[");
			tDepth = (Util.countOccurences(tempS, ",")/tWidth) + 1;
			
			while (scan.hasNextLine())
			{
				scan.nextLine();
				tHeight += 1;
			}
			
			System.out.println("Creating Prism from filepath: Height: " + tHeight + "  Width: " + tWidth + "  Depth: " + tDepth);
			
			if(filePath.startsWith("$$"))
			{
				scan = new Scanner(filePath.substring(2));
			} else {
				File file = new File(filePath);
				scan = new Scanner(file);
			}
			
			prism = new double[tHeight][tWidth][tDepth];
			this.height = tHeight;
			this.width = tWidth;
			this.depth = tDepth;
			
			for(int i = 0; i < tHeight; i++)
			{
				Scanner temp = new Scanner(scan.nextLine()).useDelimiter("\\]\\[");
				for(int j = 0; j < tWidth; j++)
				{
					Scanner temp2 = new Scanner(temp.next()).useDelimiter(", ");
					for(int k = 0; k < tDepth; k++)
					{
						prism[i][j][k] = Double.parseDouble((temp2.next().replace("[", "")).replace("]", ""));
					}
				}
			}
			
			
			scan.close();
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println("ERROR: in Prism.java - input file not found - " + filePath);
		} catch (Exception e)
		{
			System.out.println("ERROR: in Prism.java - could not init given filepath - " + filePath);
			e.printStackTrace();
		}
	}
	
	public void set(int x, int y, int z, double value)
	{
		prism[y][x][z] = value;
	}
	
	public void init(double value)
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				for(int k = 0; k < depth; k++)
				{
					prism[i][j][k] = value;
				}
			}
		}
	}
	
	
	public double get(int x, int y, int z)
	{
		return prism[y][x][z];
	}
	
	public double[] get(int x, int y)
	{
		return prism[x][y];
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getDepth()
	{
		return depth;
	}
	
	public String toString()
	{
		String out = "";
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				out += Arrays.toString(prism[i][j]);
			}
			out+="\n";
		}
		
		return out;
	}
	
	public void fileOut(String filePath)
	{
		try{
		    PrintWriter writer = new PrintWriter(filePath, "UTF-8");
		    writer.print(toString());
		    writer.close();
		    System.out.println("Init finnished");
		} catch (Exception e) {
			System.err.println("Caught Exception in Prism.java-fileOut: " + e.getMessage());
		}
	}
	
	public static Prism imageToPrism(BufferedImage image)
	{
		Prism out = new Prism(image.getHeight(), image.getWidth(), 3);
		
		for(int i = 0; i < image.getHeight(); i++)
		{
			for(int j = 0; j < image.getWidth(); j++)
			{
				Color c = new Color(image.getRGB(j, i));
				out.set(j, i, 0, c.getRed()/255);
				out.set(j, i, 1, c.getGreen()/255);
				out.set(j, i, 2, c.getBlue()/255);
			}
		}
		
		return out;
	}
	
	public static Prism imageToPrism(String filePath)
	{
		try {
			BufferedImage img = ImageIO.read(new File(filePath));
		    return Prism.imageToPrism(img);
		} catch (IOException e) {
			System.out.println("ERROR: in Main.java - image file not found");
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
}
