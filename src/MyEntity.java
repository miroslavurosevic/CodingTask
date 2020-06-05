import java.util.TreeMap;
import java.util.TreeSet;

public class MyEntity extends AbstractEntity {
	private TreeSet<MyEntity> subEntities;
	private TreeMap<String,Integer> data;
	
	public MyEntity() {
		super();
	}
	
	public MyEntity(String id){
		super(id);
		this.subEntities = new TreeSet<MyEntity>();
		this.data = new TreeMap<String,Integer>();
	}


	@Override
	public TreeSet<MyEntity> getSubEntities() {
		return subEntities;
	}

	@Override
	public TreeMap<String,Integer> getData() {
		return data;
	}


	public void setSubEntities(TreeSet<MyEntity> subEntities) {
		this.subEntities = subEntities;
	}


	public void setData(TreeMap<String, Integer> data) {
		this.data = data;
	}

	
	
	
	
}
