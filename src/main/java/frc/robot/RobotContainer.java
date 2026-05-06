// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.flywheel.Flywheel;
import frc.robot.subsystems.flywheel.FlywheelIO;
import frc.robot.subsystems.flywheel.FlywheelIOSim;
import frc.robot.subsystems.flywheel.FlywheelIOTalonFX;
import frc.robot.subsystems.rollers.Rollers;
import frc.robot.subsystems.rollers.RollersIO;
import frc.robot.subsystems.rollers.RollersIOSim;
import frc.robot.subsystems.rollers.RollersIOTalonFX;

public class RobotContainer {
  private final CommandXboxController driverController = new CommandXboxController(0);
  private final Flywheel flywheel;
  private final Rollers serializer;
  private final Rollers tunneler;
  private final Rollers intakeRollers;

  public RobotContainer() {
    switch (Constants.currentMode) {
      case REAL:
        // Real implementations of IOs
        flywheel = new Flywheel(new FlywheelIOTalonFX());
        serializer = new Rollers("Serializer", new RollersIOTalonFX(17, "CANivore"));
        tunneler = new Rollers("Tunneler", new RollersIOTalonFX(18, "CANivore"));
        intakeRollers = new Rollers("IntakeRollers", new RollersIOTalonFX(14, "rio"));
        break;

      case SIM:
        // Simulation implementations of IOs
        flywheel = new Flywheel(new FlywheelIOSim());
        serializer = new Rollers("Serializer", new RollersIOSim());
        tunneler = new Rollers("Tunneler", new RollersIOSim());
        intakeRollers = new Rollers("IntakeRollers", new RollersIOSim());
        break;

      default:
        // Empty implementations of IOs
        flywheel = new Flywheel(new FlywheelIO() {});
        serializer = new Rollers("Serializer", new RollersIO() {});
        tunneler = new Rollers("Tunneler", new RollersIO() {});
        intakeRollers = new Rollers("IntakeRollers", new RollersIO() {});
        break;
    }

    configureBindings();
  }

  private void configureBindings() {
    driverController.y().whileTrue(serializer.runVoltage(3.5));
    driverController.y().whileTrue(tunneler.runVoltage(12.0));
    driverController.y().whileTrue(flywheel.runVoltage(6.0));
    driverController.x().whileTrue(intakeRollers.runVoltage(12.0));
    driverController.a().whileTrue(serializer.runVoltage(-3.5));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
