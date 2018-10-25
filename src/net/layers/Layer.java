package net.layers;

//import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import net.Prism;

public interface Layer {

	static void init(Prism input, String name)
	{
		try{
		    PrintWriter writer = new PrintWriter("./res/weights/" +  name + ".txt", "UTF-8");
		    writer.print(input);
		    writer.close();
		    System.out.println("Init finnished");
		} catch (Exception e) {
			System.err.println("Caught Exception: " + e.getMessage());
		}
	}

	Prism run(Prism input);
	Prism getOutput();
}
