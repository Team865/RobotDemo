package frc.robot.subsystems.flywheel;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.LoggedTunableNumber;
import org.littletonrobotics.junction.Logger;

public class Flywheel extends SubsystemBase {
  private final FlywheelIO io;
  private final FlywheelIOInputsAutoLogged inputs = new FlywheelIOInputsAutoLogged();

  private final LoggedTunableNumber kP = new LoggedTunableNumber("Flywheel/kP", 0.3);
  private final LoggedTunableNumber kD = new LoggedTunableNumber("Flywheel/kD", 0.0);

  public Flywheel(FlywheelIO io) {
    this.io = io;
    io.setGains(kP.get(), kD.get());
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

  public Command runVelocity(AngularVelocity velocity) {
    return startEnd(() -> io.setVelocity(velocity), () -> io.setVoltage(0));
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);

    Logger.processInputs("Flywheel", inputs);

    LoggedTunableNumber.ifChanged(
        hashCode(), constants -> io.setGains(constants[0], constants[1]), kP, kD);
  }
}
