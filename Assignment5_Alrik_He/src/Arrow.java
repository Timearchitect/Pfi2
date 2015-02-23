
public class Arrow {
	private float angle;
	public int x, y,unit;
	public int thickness,length,step;
	public String name;
	
	public Arrow(String _name, int _thickness,int _length,float _angle,int _step){
		this.name= _name;
		this.thickness=_thickness;
		this.length=_length;
		this.angle=_angle;
		this.step= _step;
	}
	


	public void setAngle(float _angle){
		this.angle=_angle;
	}
	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public float getAngle(){
		return this.angle;
	}
	
	
	
	

}
