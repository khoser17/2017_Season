package org.usfirst.frc.team2374.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	
	SpeedController fLeft, fRight, mLeft, mRight, bLeft, bRight;
	RobotDrive robotDrive;

	
	public Drivetrain() {
		
		fLeft = new TalonSRX(0);
		fRight = new TalonSRX(1);
		mLeft = new TalonSRX(2);
		mRight = new TalonSRX(3);
		bLeft = new TalonSRX(4);
		bRight = new TalonSRX(5);
		
		
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
