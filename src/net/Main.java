package net;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import net.exceptions.LayerNotFoundException;
import net.layers.*;

public class Main {
	
	public static final int elementWidth = 11;
	
	public static void tester()
	{
        
	}

	public static void main(String[] args) {
	    
	    Prism currentPrism;

		//tester();
	    
		/*(System.out.println(Prism.imageToPrism("./res/input/input1.png"));
		Prism test = new Prism(1,1,3);
		test.init(1);
		Prism[] test2 = new Prism[1];
		
		test2[0] = test;
		test2[1] = test;*/
		
		/*Prism finalOut = l.run(01, Prism.imageToPrism("./res/input/input2.png"), "00,01", test2);
		
		//System.out.println(finalOut.toString());
		System.out.println(finalOut.toString().replace("0.0", "  ").replace("1.0", "=="));
		
		Util.writeFilterArray(test2, "./res/filters/test.txt");
		
		System.out.println(test.toString());
		
		test.fileOut("./res/weights/01.txt");*/
		
		System.out.print("Arch file name: ");
		Scanner s = new Scanner(System.in);
		String archName = s.nextLine(); //name of file containing architecture
		ArrayList<Layer> arch = new ArrayList<>();
		

		try{
			
			File archFile = new File("./res/arch/"+archName+".txt");
			Scanner archScan = new Scanner(archFile);
			int layerNum = 0;
			while(archScan.hasNextLine())
			{
				parseLayer(archScan.nextLine(), layerNum);
				layerNum++;
			}

			archScan.close();
		} catch(Exception e) {
			System.out.println("ERROR: in Main.java - arch file not found");
			e.printStackTrace();
		}
		
		
		//init based on image
		System.out.print("Image file name: ");
		String fileName = s.nextLine();
		
		currentPrism = Prism.imageToPrism("./res/input/" + fileName);
		
		
		//main loop
		for(int i = 0; i < arch.size(); i++)
		{
			//TODO: run the layers
		}
		
		
		s.close();
	}

	public static Layer parseLayer(String line, int layerNumber)
	{
		Layer layer;

		String type = line.substring(0, 4);
		String[] argsStrings = line.substring(4, line.length()-1).split(",");
		double[] args = new double[argsStrings.length];
		for(int i = 0; i < argsStrings.length; i++)
		{
			args[i] = Double.parseDouble(argsStrings[i]);
		}

		switch(type.toLowerCase()) {
			case("conv"):
			
				layer = new ConvLayer(layerNumber, filters, args[0], args[1]);
				break;
			case("relu"):
				layer = new ReluLayer(layerNumber);
				break;
			case("maxp"):
				layer = new MaxPoolLayer(layerNumber, (int)args[0], (int)args[1]);
				break;
			case("drop"):
				layer = new DropoutLayer(layerNumber, args[0]);
				break;
			default: 
				throw new LayerNotFoundException("ERROR in Main - parseLayer() :: Layer: " + type + " not found while parsing architecture.");
				break;
		}
		
		return layer;

	}
}
