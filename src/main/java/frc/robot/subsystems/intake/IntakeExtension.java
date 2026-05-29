// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class IntakeExtension extends SubsystemBase {
  public final IntakeExtensionIO io;
  public final IntakeExtensionIOInputsAutoLogged inputs = new IntakeExtensionIOInputsAutoLogged();

  public IntakeExtension(IntakeExtensionIO io) {
    this.io = io;
  }

  public Command setVoltage(double voltage) {
    return runOnce(() -> io.setVoltage(voltage));
  }

  public Command runVoltage(double voltage) {
    return runEnd(() -> io.setVoltage(voltage), () -> io.setVoltage(0.0));
  }

  public Command setPosition(Distance position) {
    return runOnce(() -> io.setPosition(position));
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("IntakeExtension", inputs);
  }
}
