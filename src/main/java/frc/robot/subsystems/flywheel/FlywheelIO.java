package frc.robot.subsystems.flywheel;

import org.littletonrobotics.junction.AutoLog;

public interface FlywheelIO {
  @AutoLog
  public static class FlywheelIOInputs {
    public boolean connected = false;
    public double appliedVoltage = 0.0;
  }

  public default void setVoltage(double voltage) {}

  public default void updateInputs(FlywheelIOInputsAutoLogged inputs) {}
}
