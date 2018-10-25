package net.layers;

import net.Prism;

public class DropoutLayer implements Layer {

    int layerNumber;
    Prism input;
    Prism output;

    double dropPercent;

    public DropoutLayer(int layerNumber, double dropPercent)
    {
        this.layerNumber = layerNumber;
        this.dropPercent = dropPercent;
    }

    public Prism run(Prism input)
    {
        this.input = input;
        this.output = dropout();
        return output;
    }

    public Prism dropout()
    {
        Prism out = new Prism(input.getWidth(), input.getHeight(), input.getDepth());
        int[] drop = new int[(int)((out.getWidth() * out.getHeight() * out.getDepth()) * (dropPercent / 100.0))];
        
        System.out.println(drop.length);
        
        for(int i = 0; i < drop.length; i++)
        {
            int randX = (int)(Math.random() * input.getWidth());
            int randY = (int)(Math.random() * input.getHeight());
            int randZ = (int)(Math.random() * input.getDepth());
            
            int timeout = 0;
            
            while(out.get(randX, randY, randZ) == Integer.MIN_VALUE && timeout < (out.getWidth() * out.getHeight() * out.getDepth()))
            {
                timeout++;
                randX = (int)(Math.random() * input.getWidth());
                randY = (int)(Math.random() * input.getHeight());
                randZ = (int)(Math.random() * input.getDepth());
            }
            
            if ((timeout > (out.getWidth() * out.getHeight() * out.getDepth())))
            {
                System.out.print("WARNING: DropoutLayer position search taking too long, exited loop.");
            } else {
                
                out.set(randX, randY, randZ, Integer.MIN_VALUE);
            }
        }
        
        for(int z = 0; z < out.getDepth(); z++)
        {
            for(int x = 0; x < out.getWidth(); x++)
            {
                for(int y = 0; y < out.getHeight(); y++)
                {
                    if(out.get(x, y, z) == Integer.MIN_VALUE)
                    {
                        out.set(x, y, z, 0);
                    } else {
                        out.set(x, y, z, input.get(x, y, z));
                    }
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
