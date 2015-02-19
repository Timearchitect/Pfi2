
public class Arrow {
	private float angle;
	public int x, y;
	public int thickness,length;
	
	public Arrow( int _thickness,int _length,float _angle){
		this.thickness=_thickness;
		this.length=_length;
		this.angle=_angle;
	}
	
	public void setAngle(float _angle){
		this.angle=_angle;
	}
	public float getAngle(){
		return this.angle;
	}
	
	
	
	

}
