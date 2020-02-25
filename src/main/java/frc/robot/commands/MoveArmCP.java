/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ControlPanel;

public class MoveArmCP extends CommandBase {
  ControlPanel cp;
  private double val;
  /**
   * Creates a new MoveArms.
   */
  public MoveArmCP(ControlPanel cp) {
    this.cp = cp;
    addRequirements(cp);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    val = RobotContainer.getArmCP();
    cp.moveCPArm(val);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cp.moveCPArm(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
