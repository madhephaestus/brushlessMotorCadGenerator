import com.neuronrobotics.bowlerstudio.vitamins.Vitamins

import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Cylinder
import eu.mihosoft.vrl.v3d.parametrics.*;
CSG generate(){
	String type= "brushlessMotor"
	if(args==null)
		args=["TL2955"]
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,size.getStrValue())

	double diameterValue = measurments.diameter
	String boltSizeValue = measurments.boltSize
	double heightValue = measurments.height
	double rotorSpacingShortValue = measurments.rotorSpacingShort
	double rotorSpacingLongValue = measurments.rotorSpacingLong
	double statorBoltSpacingValue = measurments.statorBoltSpacing
	println rotorSpacingShortValue
	println rotorSpacingLongValue
	println statorBoltSpacingValue
	CSG vitamin_capScrew = Vitamins.get("capScrew", boltSizeValue)
					
	
	//println measurments
	// Stub of a CAD object
	CSG part = new Cylinder(diameterValue/2.0, heightValue).toCSG()
	
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate()