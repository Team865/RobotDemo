package frc.robot.subsystems.flywheel;

public class FlywheelIOSim implements FlywheelIO {
  private double voltage = 0.0;

  @Override
  public void setVoltage(double voltage) {
    System.out.println("THIS IS RUNNING IN A SIMULATION");
    this.voltage = voltage;
  }

  @Override
  public void updateInputs(FlywheelIOInputsAutoLogged inputs) {
    inputs.connected = true;
    inputs.appliedVoltage = voltage;
  }
}
