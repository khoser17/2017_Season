package org.usfirst.frc.team2374.robot.subsystems;

import org.usfirst.frc.team2374.robot.RobotMap;
import org.usfirst.frc.team2374.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	
	CANTalon masterLeft, masterRight, fLeft, fRight, bLeft, bRight;
	RobotDrive robotDrive;
	AHRS navX;

	public Drivetrain() {
		masterLeft = new CANTalon(RobotMap.talonMasterLeft);
		masterRight = new CANTalon(RobotMap.talonMasterRight);
		fLeft = new CANTalon(RobotMap.talonFrontLeft);
		fRight = new CANTalon(RobotMap.talonFrontRight);
		bLeft = new CANTalon(RobotMap.talonBackLeft);
		bRight = new CANTalon(RobotMap.talonBackRight);		

		fLeft.changeControlMode(TalonControlMode.Follower);
		fRight.changeControlMode(TalonControlMode.Follower);
		bLeft.changeControlMode(TalonControlMode.Follower);
		bRight.changeControlMode(TalonControlMode.Follower);
		fLeft.set(0);
		fRight.set(0);
		bLeft.set(1);
		bRight.set(1);
		
		masterLeft.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		masterRight.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		robotDrive = new RobotDrive(masterLeft, masterRight);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	public void tankDrive(double left, double right) {
		robotDrive.tankDrive(left, right);
	}

}
