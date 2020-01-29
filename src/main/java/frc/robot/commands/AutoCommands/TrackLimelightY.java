/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TrackLimelightY extends PIDCommand {
  Shooter shooter;
  DriveTrain drivetrain;
  static double d;

  /**
   * Creates a new TrackLimelightY.
   */
  public TrackLimelightY(Shooter shooter, DriveTrain drivetrain) {
    super(
        // The controller that the command will use
        new PIDController(Constants.PID_Constants.kHood.P, Constants.PID_Constants.kHood.I,
            Constants.PID_Constants.kHood.D),
        // This should return the measurement
        shooter::getYOffset,
        // This should return the setpoint (can also be a constant)
        0,
        // This uses the output
        output -> {
          d = output;
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public double usePIDOutput() {
    return d;
  }

}