package dkpro.similarity.algorithms.structure.uima;

import java.util.Map;

import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;

import dkpro.similarity.algorithms.structure.PosNGramJaccardMeasure;
import dkpro.similarity.uima.resource.JCasTextSimilarityResourceBase;


public class PosNGramJaccardResource
	extends JCasTextSimilarityResourceBase
{
    public static final String PARAM_N = "N";
    @ConfigurationParameter(name=PARAM_N, mandatory=true)
    private String nString;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public boolean initialize(ResourceSpecifier aSpecifier, Map aAdditionalParams)
        throws ResourceInitializationException
    {
        if (!super.initialize(aSpecifier, aAdditionalParams)) {
            return false;
        }

        this.mode = TextSimilarityResourceMode.jcas;
        
        try {
            int n = Integer.parseInt(nString); 
            measure = new PosNGramJaccardMeasure(n);
        }
        catch (NumberFormatException e) {
            throw new ResourceInitializationException(e);
        }
        
        return true;
    }
}
