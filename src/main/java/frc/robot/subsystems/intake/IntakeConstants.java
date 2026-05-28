package frc.robot.subsystems.intake;

import static edu.wpi.first.units.Units.Inches;

import com.ctre.phoenix6.CANBus;
import edu.wpi.first.units.measure.Distance;
import frc.robot.CANBuses;

public class IntakeConstants {
  public static class Rollers {
    public static final int CAN_ID = 14;
    public static final CANBus CANBUS = CANBuses.RIO;
  }

  public static class Extension {
    public static final int CAN_ID = 15;
    public static final CANBus BANBUS = CANBuses.CANIVORE;
    public static final Distance radius = Inches.of(1.14);
    public static final double gearRatio = 27.0 / 1.0;
  }
}
