

public abstract class AbstractEntity implements Entity, Comparable<AbstractEntity> {

	private String id;
	
	public AbstractEntity() {
		
	}
	
	public AbstractEntity(String id) {
		this.id = id;
	}

	@Override
	public String getID() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int compareTo(AbstractEntity e) {
	
		return this.getID().compareTo(e.getID());
	}
	
	
}
