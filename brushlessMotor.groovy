import eu.mihosoft.vrl.v3d.parametrics.*;
CSG generate(){
	String type= "brushlessMotor"
	if(args==null)
		args=["TL2955"]
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,size.getStrValue())

	def diameterValue = measurments.diameter
	def boltSizeValue = measurments.boltSize
	def heightValue = measurments.height
	def rotorSpacingShortValue = measurments.rotorSpacingShort
	def rotorSpacingLongValue = measurments.rotorSpacingLong
	def statorBoltSpacingValue = measurments.statorBoltSpacing
	println measurments
	// Stub of a CAD object
	CSG part = new Cube().toCSG()
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate() 