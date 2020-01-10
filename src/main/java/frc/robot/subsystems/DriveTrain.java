
package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class DriveTrain extends SubsystemBase {
  private Talon l1Talon = new Talon(Constants.TalonID.kLeft1.id);
  private Talon l2Talon = new Talon(Constants.TalonID.kLeft2.id);
  private Talon r1Talon = new Talon(Constants.TalonID.kRight1.id);
  private Talon r2Talon = new Talon(Constants.TalonID.kRight2.id);
  // private CANSparkMax l1SparkMax = new CANSparkMax(Constants.CANSparkMaxID.kLeft1.id, MotorType.kBrushless);
  // private CANSparkMax r1SparkMax = new CANSparkMax(Constants.CANSparkMaxID.kRight1.id, MotorType.kBrushless);
  // private CANSparkMax r2SparkMax = new CANSparkMax(Constants.CANSparkMaxID.kRight2.id, MotorType.kBrushless);
  // private CANSparkMax l2SparkMax = new CANSparkMax(Constants.CANSparkMaxID.kLeft2.id, MotorType.kBrushless);

  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(l1Talon, l2Talon);
  private SpeedControllerGroup rightMotors = new SpeedControllerGroup(r1Talon, r2Talon);

  private DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);


  /**
   * Creates a new ExampleSubsystem.
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    // l1SparkMax.setIdleMode(IdleMode.kBrake);
    // l2SparkMax.setIdleMode(IdleMode.kBrake);
    // r1SparkMax.setIdleMode(IdleMode.kBrake);
    // r2SparkMax.setIdleMode(IdleMode.kBrake);
    }
  

  

  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
  public void tankDrive(double r, double l)
  {
    drive.tankDrive(l, r ,true);
  }
}