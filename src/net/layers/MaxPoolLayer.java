package net.layers;

import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import net.Prism;

public class MaxPoolLayer implements Layer{

	//Pass size through data string in order Width, Height
    
    //FIXME wont work when input.width is not divisible by width

	int layerNumber;
	Prism input;
	Prism output;

	int height;
	int width;

	public MaxPoolLayer(int layerNumber, int width, int height)
	{
		this.layerNumber = layerNumber;
		this.height = height;
		this.width= width;

	}

	public Prism run(Prism input)
	{
		this.input = input;
		this.output = pool();
		return output;
	}

	public Prism pool() {
		
		Prism out = new Prism(input.getWidth()/width, input.getHeight()/height, input.getDepth());
		
		for(int d = 0; d < out.getDepth(); d++)
		{
    		for(int h = 0; h < out.getHeight(); h++)
    		{
    		    for(int w = 0; w < out.getWidth(); w++)
    		    {
    		        double value = input.get(w*width, h*height, d);
    		        for(int x = 0; x < width; x++)
    		        {
    		            for(int y = 0; y < width; y++)
    	                {
    		                if(input.get(w*width + x, h*height + y, d) > value)
    		                {
    		                    value = input.get(w*width + x, h*height + y, d);
    		                }
    	                    //value += input.get(w*width + x, h*height + y, d);
    	                }
    		        }
    		        out.set(w, h, d, value);
    		    }
    		}
		}
		
		return out;
	}
	
	@Override
    public Prism getOutput() {
        return output;
    }

}
