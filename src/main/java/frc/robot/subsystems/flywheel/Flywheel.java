package frc.robot.subsystems.flywheel;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Flywheel extends SubsystemBase {
  private final FlywheelIO io;
  private final FlywheelIOInputsAutoLogged inputs = new FlywheelIOInputsAutoLogged();

  public Flywheel(FlywheelIO io) {
    this.io = io;
  }

  public Command setVoltage(double voltage) {
    return runOnce(
        () -> {
          io.setVoltage(voltage);
        });
  }

  public Command runVoltage(double voltage) {
    return startEnd(
        () -> {
          io.setVoltage(voltage);
        },
        () -> {
          io.setVoltage(0.0);
        });
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    Logger.processInputs("Flywheel", inputs);
  }
}
