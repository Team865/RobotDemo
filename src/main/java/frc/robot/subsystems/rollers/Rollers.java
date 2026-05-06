package frc.robot.subsystems.rollers;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Rollers extends SubsystemBase {
  private final String name;
  private final RollersIO io;
  private final RollersIOInputsAutoLogged inputs = new RollersIOInputsAutoLogged();

  public Rollers(String name, RollersIO io) {
    this.name = name;
    this.io = io;
  }

  public Command setVoltage(double voltage) {
    return runOnce(
        () -> {
          io.setVoltage(voltage);
        });
  }

  public Command runVoltage(double voltage) {
    return runEnd(
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

    Logger.processInputs(name, inputs);
  }
}
