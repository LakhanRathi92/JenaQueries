public class TableRow {
	private int uid;
	private short offset;
	private short size;
	
	public void createRow(int id, short off, short siz){
		uid = id;
		offset = off;
		size = siz;
	}	
}
