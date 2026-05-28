package frc.robot.subsystems.intake;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import org.littletonrobotics.junction.AutoLog;

public interface IntakeExtensionIO {
  @AutoLog
  public static class IntakeExtensionIOInputs {
    public boolean connected = false;

    public double appliedVoltage = 0.0;
    public Distance distance = Inches.zero();
    public Distance targetDistance = Inches.zero();
    public Angle angle = Radians.zero();

    public AngularVelocity angularVelocity = RadiansPerSecond.zero();

    public double statorCurrent = 0.0;
    public double supplyCurrent = 0.0;
  }

  public default void setVoltage(double voltage) {}

  public default void setPosition(Distance position) {}

  public default void updateInputs(IntakeExtensionIOInputsAutoLogged inputs) {}
}
