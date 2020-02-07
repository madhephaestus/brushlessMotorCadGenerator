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
	LengthParameter boltLength		= new LengthParameter("Bolt Length",10,[180,10])
	boltLength.setMM(5)
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,size.getStrValue())

	double diameterValue = measurments.diameter
	String boltSizeValue = measurments.boltSize
	double heightValue = measurments.height
	double statorNumberOfBolts=measurments.statorNumberOfBolts
	double rotorSpacingShortValue = measurments.rotorSpacingShort
	double rotorSpacingLongValue = measurments.rotorSpacingLong
	double statorBoltSpacingValue = measurments.statorBoltSpacing
	
	CSG vitamin_capScrew = Vitamins.get("capScrew", boltSizeValue)
									.roty(180)
									.toZMax()
	CSG boltSet =null;
	double degreesPer = 360/statorNumberOfBolts
	for(int i=0;i<statorNumberOfBolts;i++) {
		CSG moved = vitamin_capScrew.movex(statorBoltSpacingValue/2)
									.rotz(degreesPer*i)
		if(boltSet==null)
			boltSet=moved
		else
			boltSet=boltSet.union(moved)
	}
	CSG part = new Cylinder(diameterValue/2.0, heightValue).toCSG()
					.union(boltSet)
	
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate()