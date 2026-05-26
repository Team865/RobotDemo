package frc.robot.subsystems.rollers;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.Voltage;

public class RollersIOTalonFX implements RollersIO {
  private final TalonFX motor;

  private final VoltageOut voltageRequest = new VoltageOut(0.0);

  private final StatusSignal<Voltage> voltageSignal;

  public RollersIOTalonFX(int canId, CANBus canbus) {
    motor = new TalonFX(canId, canbus);

    voltageSignal = motor.getMotorVoltage();
  }

  @Override
  public void setVoltage(double voltage) {
    motor.setControl(voltageRequest.withOutput(voltage));
  }

  @Override
  public void updateInputs(RollersIOInputsAutoLogged inputs) {
    inputs.connected = BaseStatusSignal.refreshAll(voltageSignal).isOK();
    inputs.appliedVoltage = voltageSignal.getValueAsDouble();
  }
}
