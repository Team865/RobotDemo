package frc.robot.util;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Radians;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;

public class AngleToDistance {
  public static Distance toDistance(Angle angle, Distance radius) {

    return Inches.of(angle.in(Radians) * radius.in(Inches));
  }

  public static Angle toAngle(Distance distance, Distance radius) {

    return Radians.of(distance.in(Inches) / radius.in(Inches));
  }
}
