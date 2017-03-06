import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.jena.ext.com.google.common.collect.Table;

/*
 * Creates and maintains binary translation table for publisher
 * converts into byteArray to be sent over the network.
 * 
 * */

public class BinaryTranslationTable implements Serializable{
	private ArrayList<TableRow> Table = new ArrayList<TableRow>();
	private ByteArrayOutputStream bos = new ByteArrayOutputStream();
	
	public void addElement(TableRow rowAdd){
		Table.add(rowAdd);				
	}
	public byte[] serializeTable(){
		ObjectOutput out = null;
		byte[] tableInByteArr = null;
	    try {
			out = new ObjectOutputStream(bos);
			out.writeObject(Table);
			out.flush();
			tableInByteArr = bos.toByteArray();
        	bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			  
		return tableInByteArr;
	}	
}




