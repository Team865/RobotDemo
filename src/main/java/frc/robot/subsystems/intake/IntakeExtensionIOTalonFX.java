package frc.robot.subsystems.intake;

import static edu.wpi.first.units.Units.Inches;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.util.AngleToDistance;

public class IntakeExtensionIOTalonFX implements IntakeExtensionIO {
  private final TalonFX motor;

  private Distance targetDistance = Inches.zero();

  private final VoltageOut voltageRequest = new VoltageOut(0.0);
  private final MotionMagicVoltage positionRequest = new MotionMagicVoltage(0.0);

  private final StatusSignal<Voltage> voltageSignal;
  private final StatusSignal<Angle> anglularPositionSignal;
  private final StatusSignal<Current> statorCurrentSignal;
  private final StatusSignal<Current> supplyCurrentSignal;
  private final StatusSignal<AngularVelocity> angularVelocitySignal;

  private final TalonFXConfiguration config = new TalonFXConfiguration();

  public IntakeExtensionIOTalonFX() {

    motor = new TalonFX(IntakeConstants.Extension.CAN_ID);

    voltageSignal = motor.getMotorVoltage();
    anglularPositionSignal = motor.getPosition();
    statorCurrentSignal = motor.getStatorCurrent();
    supplyCurrentSignal = motor.getSupplyCurrent();
    angularVelocitySignal = motor.getVelocity();
  }

  @Override
  public void setVoltage(double voltage) {
    motor.setControl(voltageRequest.withOutput(voltage));
  }

  @Override
  public void setPosition(Distance position) {
    targetDistance = position;
    motor.setControl(
        positionRequest.withPosition(
            AngleToDistance.toAngle(position, IntakeConstants.Extension.radius)));
  }

  @Override
  public void updateInputs(IntakeExtensionIOInputsAutoLogged inputs) {
    inputs.connected = BaseStatusSignal.refreshAll(voltageSignal, anglularPositionSignal).isOK();

    inputs.appliedVoltage = voltageSignal.getValueAsDouble();
    inputs.distance =
        AngleToDistance.toDistance(
            anglularPositionSignal.getValue(), IntakeConstants.Extension.radius);
    inputs.angle = anglularPositionSignal.getValue();
    inputs.statorCurrent = statorCurrentSignal.getValueAsDouble();
    inputs.supplyCurrent = supplyCurrentSignal.getValueAsDouble();
    inputs.angularVelocity = angularVelocitySignal.getValue();
  }
}
