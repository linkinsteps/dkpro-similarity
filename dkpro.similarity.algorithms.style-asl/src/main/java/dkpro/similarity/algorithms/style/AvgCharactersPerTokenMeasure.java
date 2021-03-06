package dkpro.similarity.algorithms.style;

import java.util.List;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import dkpro.similarity.algorithms.api.JCasTextSimilarityMeasureBase;
import dkpro.similarity.algorithms.api.SimilarityException;


/**
 * Computes the average number of characters per token.
 */
public class AvgCharactersPerTokenMeasure
	extends JCasTextSimilarityMeasureBase
{
    @Override
    public double getSimilarity(JCas jcas1, JCas jcas2,
    		Annotation coveringAnnotation1, Annotation coveringAnnotation2)
        throws SimilarityException
    {
		List<Token> t1 = JCasUtil.selectCovered(jcas1, Token.class, coveringAnnotation1);
		List<Token> t2 = JCasUtil.selectCovered(jcas2, Token.class, coveringAnnotation2);
		
		int noOfCharacters = 0;
		
		for (Token token : t1) {
            noOfCharacters += token.getCoveredText().length();
        }
		for (Token token : t2) {
            noOfCharacters += token.getCoveredText().length();
        }
		
		return (double) noOfCharacters / (t1.size() + t2.size());
    }
}