package org.usfirst.frc.team2374.robot.subsystems;

import org.usfirst.frc.team2374.robot.RobotMap;
import org.usfirst.frc.team2374.robot.SimplePIDOutput;
import org.usfirst.frc.team2374.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	
	CANTalon masterLeft, masterRight, fLeft, fRight, bLeft, bRight;
	RobotDrive robotDrive;
	AHRS navX;
	
	PIDController turnPID, drivePID;
	SimplePIDOutput turnOut, driveOut;

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
		
		robotDrive = new RobotDrive(masterLeft, masterRight);
		robotDrive.setSafetyEnabled(true);
		robotDrive.setExpiration(0.2);
		
		navX = new AHRS(SPI.Port.kMXP);
		navX.setPIDSourceType(PIDSourceType.kDisplacement);
		
		turnPID = new PIDController(0, 0, 0 , 0, navX, turnOut);
		turnPID.setInputRange(-180, 180);
		turnPID.setOutputRange(-0.75, 0.75);
		
		masterLeft.setPIDSourceType(PIDSourceType.kDisplacement);
		drivePID = new PIDController(0, 0, 0, 0, masterLeft, driveOut);
		drivePID.setOutputRange(-0.75, 0.75);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	public void tankDrive(double left, double right) {
		robotDrive.tankDrive(left, right);
	}
	
	public void arcadeDrive(double move, double rotate) {
		robotDrive.arcadeDrive(move, rotate);
	}
	
	public void resetEncoders() {
		masterLeft.setPosition(0);
		masterRight.setPosition(0);
	}
	
	public void resetGyro() {
		navX.reset();
	}

}
