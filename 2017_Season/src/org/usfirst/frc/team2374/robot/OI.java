package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick driver;
	
	public OI() {
		driver = new Joystick(RobotMap.driverJoy);
	}
	
	public double getDriverLeftY() {
		return driver.getRawAxis(RobotMap.rsLeftAxisY);
	}
	
	public double getDriverRightY() {
		return driver.getRawAxis(RobotMap.rsRightAxisY);
	}
	
	public double deadBand(double axisValue, double deadValue) {
		if (Math.abs(axisValue) < deadValue)
			return 0;
		else
			return axisValue;
	}
}
