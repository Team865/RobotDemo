package frc.robot.subsystems.flywheel;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.units.measure.AngularVelocity;
import org.littletonrobotics.junction.AutoLog;

public interface FlywheelIO {
  @AutoLog
  public static class FlywheelIOInputs {
    public boolean masterConnected = false;
    public boolean followerConntected = false;

    public double appliedVoltage = 0.0;
    public AngularVelocity velocity = RotationsPerSecond.zero();
    public AngularVelocity targetVelocity = RotationsPerSecond.zero();
  }

  public default void setVoltage(double voltage) {}

  public default void setVelocity(AngularVelocity velocity) {}

  public default void updateInputs(FlywheelIOInputsAutoLogged inputs) {}
}
