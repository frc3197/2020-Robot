/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Hood extends SubsystemBase {
  public static WPI_TalonFX hoodMotor = new WPI_TalonFX(Constants.TalonID.kHood.id);
  DigitalInput hoodLSFront = new DigitalInput(4);
  DigitalInput hoodLSBack = new DigitalInput(5);
  /**
   * Creates a new Hood.
   */
  public Hood() {
    hoodMotor.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void moveHood(double speed){
    hoodMotor.set(speed);
  }

  public void encoderCalibrate(){
    if(getForwardLimitSwitch() && getBackwardLimitSwitch()){
      moveHood(0);
    }else if(getBackwardLimitSwitch()){
      moveHood(0);
    }else if(getForwardLimitSwitch()){
      while(!getBackwardLimitSwitch()){
        moveHood(-0.2);
      }
    }else{
      while(!getBackwardLimitSwitch()){
        moveHood(-0.2);
      }
    }
    hoodMotor.set(0);
    hoodMotor.setSelectedSensorPosition(0);
  }

  public void moveHoodtoAngle(){
    /*
    d = distance from the Power Port
    theta = angle of the hood from the back horizontal
    thetaL = angle of launch (approximately). Perpendicular to theta
    currentTicks = the current encoder position
    ticks = the ticks that the encoder should be at to be at the correct angle. (Calculated using FLM below)
      theta degrees(1 rotation/0.2 degrees)(2048 ticks/1 rotation) = 10240 * theta
    Command runs the hood forward until ticks = currentTicks (or when the difference between them is 0)
    */
    double d = RobotContainer.getDistanceFromTarget();
    double theta = Math.atan(80.25/d);
    double thetaL = 90 - theta;
    double currentTicks = getEncoderPosition();
    double ticks = 10240 * thetaL;

    while(ticks - currentTicks > 0){ //POTENTIAL PROBLEM: ticks is based on thetaL and currentTicks is based on theta. May be wrong.
      moveHood(0.2);
      currentTicks = getEncoderPosition();
    }
    //TODO: Test physics, implement, etc.
  }

  public double getYOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
  }

  public double getEncoderPosition(){
    return hoodMotor.getSelectedSensorPosition();
  }

  public boolean getForwardLimitSwitch(){
    return hoodLSFront.get();
  }

  public boolean getBackwardLimitSwitch(){
    return hoodLSBack.get();
  }
}
