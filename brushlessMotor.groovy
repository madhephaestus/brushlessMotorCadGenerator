import eu.mihosoft.vrl.v3d.parametrics.*;
CSG generate(){
	String type= "brushlessMotor"
	if(args==null)
		args=["TL2955 "]
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,size.getStrValue())

	def diameterValue = measurments.diameter
	def heightValue = measurments.height
	println "Loaded from vitamins measurments diameterValue:  "+diameterValue"+ value is = "+diameterValue
	println "Loaded from vitamins measurments heightValue:  "+heightValue"+ value is = "+heightValue
	// Stub of a CAD object
	CSG part = new Cube().toCSG()
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate() 