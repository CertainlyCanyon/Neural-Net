package net.layers;

import net.Prism;

public class ReluLayer implements Layer{
    
    int layerNumber;
    Prism input;
    Prism output;
    
    public ReluLayer(int layerNumber) {
        this.layerNumber = layerNumber;
    }

    public Prism run(Prism input)
    {
        this.input = input;
        this.output = relu();
        return output;
    }
    
     public Prism relu()
     {
        output = new Prism(input.getWidth(), input.getHeight(), input.getDepth());
        for(int d = 0; d < output.getDepth(); d++) //loop through output array to find the value that should go in that position
        {
            for(int w = 0; w < output.getWidth(); w++)
            {
                for(int h = 0; h < output.getHeight(); h++)
                {
                    if(input.get(w, h, d) < 0)
                    {
                        output.set(w, h, d, 0);
                    } else {
                        output.set(w, h, d, input.get(w, h, d));
                    }
                }
            }
        }
        
        return output;

    }
     
     @Override
     public Prism getOutput() {
         return output;
     }

}
