import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.ArrayList;

import org.apache.jena.rdf.model.Model ;
import org.apache.jena.rdf.model.ModelFactory ;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.RDFDataMgr ;
import org.apache.jena.riot.RDFLanguages ;
import org.apache.commons.io.IOUtils;
import org.apache.jena.query.* ;

public class Test {

    public static void main(String[] args)
    {    	
    	
    	Boolean FASTRDF = false;  
    	Boolean tableTest = false;
    	Boolean EncDecTest = false;
    	
    	/* Test to run queries on published and locally stored models */
    	if (FASTRDF)
    	{
	    	Model m = ModelFactory.createDefaultModel() ;
	    	try {
				m.read(new FileInputStream("published.rdf"),null,"TTL");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        	        
	    	/*Debug*/
	        m.write(System.out);
	        
	        /*
	         * READ Query
	         * */
	        
	        BufferedReader br = null;
	        String stringQuery = null;
			
	        try {
	        	/*query2.txt for getting whole rdf document */
	        	/*query3.txt for UID's*/
				br = new BufferedReader(new FileReader("query3.txt"));				
				try {
					stringQuery = IOUtils.toString(br);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
	        
	        /*query*/	        
	        System.out.println("Query : " + stringQuery);
	        
	        String queryString = stringQuery;
	        Query query = QueryFactory.create(queryString) ;
	        
	        try (QueryExecution qexec = QueryExecutionFactory.create(query, m)) {	          
	        ResultSet results = qexec.execSelect() ;            
	        String string_Result = ResultSetFormatter.asText(results);
	        System.out.println(string_Result);
	        }
    	}
    	
    	//Test for Encoder and Decoder
    	if(EncDecTest)
    	{
    		VariableLengthEnc_Dec.Encode(10);
    		VariableLengthEnc_Dec.Encode(128);
    		VariableLengthEnc_Dec.Encode(256);
    		
    	}
    	
    	if(tableTest){
    		BinaryTranslationTable table = new BinaryTranslationTable();
    		TableRow row1 = new TableRow();
    		
    		row1.createRow(123, (short)0, (short)3);
    		
    		TableRow row2 = new TableRow();    		
    		row2.createRow(456, (short)3, (short)4);
    		
    		table.addElement(row1);
    		table.addElement(row2);
    		
    		
    		byte[] serializedTable = null;
			try {
				serializedTable = SerDes.serialize(table);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		/*Debug*/
    		try {
    			BinaryTranslationTable table_des = (BinaryTranslationTable) SerDes.deserialize(serializedTable);
    			
    			for(TableRow x : table_des.table){
    				System.out.println(" UID " + x.getUID());
    				System.out.print(" offset " + x.getOffset());
    				System.out.print(" bytes " + x.getSize());
    			}
    			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}	
    } 
}

