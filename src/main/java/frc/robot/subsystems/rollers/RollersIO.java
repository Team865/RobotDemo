package frc.robot.subsystems.rollers;

import org.littletonrobotics.junction.AutoLog;

public interface RollersIO {
  @AutoLog
  public static class RollersIOInputs {
    public boolean connected = false;
    public double appliedVoltage = 0.0;
  }

  public default void setVoltage(double voltage) {}

  public default void updateInputs(RollersIOInputsAutoLogged inputs) {}
}
