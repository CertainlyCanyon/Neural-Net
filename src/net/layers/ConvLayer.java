package net.layers;

import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import net.Prism;

public class ConvLayer implements Layer{
    
    int padding;
    int stride;
    
    int layerNumber;
    Prism input;
    Prism output;
    Prism[] filters;
    
    public ConvLayer(int layerNumber, Prism[] filters, int padding, int stride)
    {
        this.padding = padding;
        this.stride = stride;
        this.filters = filters;
        this.layerNumber = layerNumber;
        
    }

	public Prism run(Prism input)
	{
		this.input = input;
		this.output = convolve();
		return output;
	}

	public Prism convolve() {
		
		if(filters[0].getDepth() != input.getDepth())
		{
			System.out.println("Error in Convlayer.java: depth mismatch between filter ("+filters[0].getDepth()+") and input (" + input.getDepth() + ")!");
			throw new IndexOutOfBoundsException();
		}
		
		/*int padding = Integer.parseInt(data.substring(0,2));
		int stride = Integer.parseInt(data.substring(3));
		
		System.out.println(padding + ":" + stride); //implement later*/
		
		Prism out = new Prism((input.getWidth()-filters[0].getWidth() + 2*padding)/stride + 1, (input.getHeight()-filters[0].getHeight() + 2*padding)/stride + 1, filters.length);
		
		System.out.println(out.getHeight() + ";" + out.getWidth());
		
		int totalPixels = filters[0].getHeight() * filters[0].getWidth() *filters[0].getDepth();//number of pixels in a filter, used in average
		
		for(int f = 0; f < filters.length; f++) //Depth of output is the number of filters
		{
			for(int h = 0; h < out.getHeight(); h++) //loop through output array to find the value that should go in that position
			{
				for(int w = 0; w < out.getWidth(); w++)
				{
					double match = 0;//the value that should go there
					for(int d = 0; d < input.getDepth(); d++)
					{
						for(int y = 0; y < filters[0].getWidth(); y++)
						{
							for(int x = 0; x < filters[0].getHeight(); x++)
							{
								match += input.get(x+w, y+h, d) * filters[f].get(x, y, d);//find match by multiplying each position in input with the position in the filter and adding them
								
							}
						}
 					}
					//match = match/(totalPixels); //then divide to get the average match
					out.set(w, h, f, match); //and set that in the output array
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
