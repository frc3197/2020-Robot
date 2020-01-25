/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.PID_Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TrackLimelightX extends PIDCommand {
  Shooter shooter;
  DriveTrain driveTrain;

  /**
   * Creates a new TrackLimelight.
   */
  public TrackLimelightX(Shooter shooter, DriveTrain driveTrain) {
    super(
        // The controller that the command will use
        new PIDController(PID_Constants.kTurn.P, PID_Constants.kTurn.I, PID_Constants.kTurn.D),
        // This should return the measurement
        shooter::getXOffset,
        // This should return the setpoint (can also be a constant)
        0,
        // This uses the output
        output -> System.out.println("Output: " + output));
    addRequirements();
    this.driveTrain = driveTrain;
  }

  public void end() {
    driveTrain.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}