// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.RadiansPerSecond;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.flywheel.Flywheel;
import frc.robot.subsystems.flywheel.FlywheelIO;
import frc.robot.subsystems.flywheel.FlywheelIOSim;
import frc.robot.subsystems.flywheel.FlywheelIOTalonFX;
import frc.robot.subsystems.indexer.IndexerConstants;
import frc.robot.subsystems.indexer.Serializer;
import frc.robot.subsystems.intake.IntakeConstants;
import frc.robot.subsystems.intake.IntakeExtension;
import frc.robot.subsystems.intake.IntakeExtensionIO;
import frc.robot.subsystems.intake.IntakeExtensionIOTalonFX;
import frc.robot.subsystems.rollers.Rollers;
import frc.robot.subsystems.rollers.RollersIO;
import frc.robot.subsystems.rollers.RollersIOSim;
import frc.robot.subsystems.rollers.RollersIOTalonFX;

public class RobotContainer {
  private final CommandXboxController driverController = new CommandXboxController(0);
  private final Flywheel flywheel;
  private final Serializer serializer;
  private final Rollers tunneler;
  private final Rollers intakeRollers;
  private final IntakeExtension intakeExtension;

  public RobotContainer() {
    switch (Constants.currentMode) {
      case REAL:
        // Real implementations of IOs
        flywheel = new Flywheel(new FlywheelIOTalonFX());
        serializer =
            new Serializer(
                new RollersIOTalonFX(IndexerConstants.Serializer.CAN_ID, IndexerConstants.CANBUS));
        tunneler =
            new Rollers(
                "Tunneler",
                new RollersIOTalonFX(IndexerConstants.Tunneler.CAN_ID, IndexerConstants.CANBUS));
        intakeRollers =
            new Rollers(
                "IntakeRollers",
                new RollersIOTalonFX(
                    IntakeConstants.Rollers.CAN_ID, IntakeConstants.Rollers.CANBUS));
        intakeExtension = new IntakeExtension(new IntakeExtensionIOTalonFX());
        break;

      case SIM:
        // Simulation implementations of IOs
        flywheel = new Flywheel(new FlywheelIOSim());
        serializer = new Serializer(new RollersIOSim());
        tunneler = new Rollers("Tunneler", new RollersIOSim());
        intakeRollers = new Rollers("IntakeRollers", new RollersIOSim());
        intakeExtension = new IntakeExtension(new IntakeExtensionIO() {}); // TODO
        break;

      default:
        // Empty implementations of IOs
        flywheel = new Flywheel(new FlywheelIO() {});
        serializer = new Serializer(new RollersIO() {});
        tunneler = new Rollers("Tunneler", new RollersIO() {});
        intakeRollers = new Rollers("IntakeRollers", new RollersIO() {});
        intakeExtension = new IntakeExtension(new IntakeExtensionIO() {});
        break;
    }

    configureBindings();
  }

  private void configureBindings() {
    driverController.rightTrigger().whileTrue(serializer.runVoltage(3.5));
    driverController.rightTrigger().whileTrue(tunneler.runVoltage(12.0));
    driverController.y().whileTrue(flywheel.runVelocity(RadiansPerSecond.of(300)));
    driverController.x().whileTrue(intakeExtension.runVoltage(6.5));
    driverController.b().whileTrue(intakeExtension.runVoltage(-3.0));

    // driverController.x().whileTrue(flywheel.runVelocity(RadiansPerSecond.of(200)));
    // driverController.b().whileTrue(flywheel.runVelocity(RadiansPerSecond.of(400)));
    // driverController.a().whileTrue(flywheel.runVelocity(RadiansPerSecond.of(500)));
    // driverController.x().whileTrue(intakeRollers.runVoltage(12.0));
    // driverController.a().whileTrue(serializer.runVoltage(-3.5));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
