package frc.robot.subsystems.rollers;

public class RollersIOSim implements RollersIO {
  private double appliedVoltage = 0.0;

  @Override
  public void setVoltage(double voltage) {
    appliedVoltage = voltage;
  }

  @Override
  public void updateInputs(RollersIOInputsAutoLogged inputs) {
    inputs.connected = true;
    inputs.appliedVoltage = appliedVoltage;
  }
}
