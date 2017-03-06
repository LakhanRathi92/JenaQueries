import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.jena.rdf.model.Model ;
import org.apache.jena.rdf.model.ModelFactory ;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.RDFDataMgr ;
import org.apache.jena.riot.RDFLanguages ;
import org.apache.commons.io.IOUtils;
import org.apache.jena.query.* ;

public class fastRDFCheck {

    public static void main(String[] args)
    {
    	
    	Boolean FASTRDF = true;
    	
    	if (FASTRDF)
    	{
	    	Model m = ModelFactory.createDefaultModel() ;
	        // read into the model.
	    	
	    	
	    	try {
				m.read(new FileInputStream("published.rdf"),null,"TTL");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // Alternatively, use the RDFDataMgr, which reads from the web,
	        // with content negotiation.  Plain names are assumed to be 
	        // local files where file extension indicates the syntax.  
	        
	    	
	        m.write(System.out);
	        
	        /*
	         * READ Query
	         * */
	        
	        BufferedReader br = null;
	        String everything = null;
			
	        try {
	        	/* query2.txt for whole rdf document */
	        	/*query3.txt for UID's*/
				br = new BufferedReader(new FileReader("query3.txt"));
				
				try {
					everything = IOUtils.toString(br);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
	        
	        /*query*/
	        
	        System.out.println("Query : " + everything);
	        
	        String queryString = everything;
	        Query query = QueryFactory.create(queryString) ;
	        
	        try (QueryExecution qexec = QueryExecutionFactory.create(query, m)) {
	          
	        ResultSet results = qexec.execSelect() ;        
	        
	        /*fastRDFUID*/
	        
	        String string_Result = ResultSetFormatter.asText(results);
	        System.out.println(string_Result);
	        }
    	}
    	//Encoder
    	else
    	{
    		VariableLengthEnc_Dec.Encode(10);
    		VariableLengthEnc_Dec.Encode(128);
    		VariableLengthEnc_Dec.Encode(256);
    		
    	}
    } 
}

