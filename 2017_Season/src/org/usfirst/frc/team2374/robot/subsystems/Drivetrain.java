package org.usfirst.frc.team2374.robot.subsystems;

import org.usfirst.frc.team2374.robot.RobotMap;
import org.usfirst.frc.team2374.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	
	CANTalon masterLeft, masterRight, fLeft, fRight, bLeft, bRight;
	TalonControlMode mode;
	AHRS navX;

	public Drivetrain() {
		masterLeft = new CANTalon(RobotMap.talonDriveMasterLeft);
		masterRight = new CANTalon(RobotMap.talonDriveMasterRight);
		fLeft = new CANTalon(RobotMap.talonDriveFrontLeft);
		fRight = new CANTalon(RobotMap.talonDriveFrontRight);
		bLeft = new CANTalon(RobotMap.talonDriveBackLeft);
		bRight = new CANTalon(RobotMap.talonDriveBackRight);		

		fLeft.changeControlMode(TalonControlMode.Follower);
		fRight.changeControlMode(TalonControlMode.Follower);
		bLeft.changeControlMode(TalonControlMode.Follower);
		bRight.changeControlMode(TalonControlMode.Follower);
		fLeft.set(RobotMap.talonDriveMasterLeft);
		fRight.set(RobotMap.talonDriveMasterRight);
		bLeft.set(RobotMap.talonDriveMasterLeft);
		bRight.set(RobotMap.talonDriveMasterRight);
		
		masterLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		masterRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		mode = TalonControlMode.PercentVbus;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	private void changeTalonMode(TalonControlMode m) {
		masterLeft.changeControlMode(m);
		masterRight.changeControlMode(m);
	}
	
	public void tankDrive(double left, double right) {
		if (!mode.equals(TalonControlMode.PercentVbus))
			changeTalonMode(TalonControlMode.PercentVbus);
		masterLeft.set(left);
		masterRight.set(right);
	}

}
