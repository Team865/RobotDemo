package frc.robot.subsystems.flywheel;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.units.measure.Voltage;

public class FlywheelIOTalonFX implements FlywheelIO {
  private final TalonFX mainMotor;
  private final TalonFX followerMotor;
  private final VoltageOut voltageRequest = new VoltageOut(0.0);

  private final StatusSignal<Voltage> voltageSignal;

  public FlywheelIOTalonFX() {
    mainMotor = new TalonFX(19, "rio");
    followerMotor = new TalonFX(20, "rio");
    Follower followerRequest = new Follower(mainMotor.getDeviceID(), MotorAlignmentValue.Opposed);
    followerMotor.setControl(followerRequest);

    voltageSignal = mainMotor.getMotorVoltage();
  }

  @Override
  public void setVoltage(double voltage) {
    System.out.println("THIS IS RUNNING ON REAL HARDWARE");
    mainMotor.setControl(voltageRequest.withOutput(voltage));
  }

  @Override
  public void updateInputs(FlywheelIOInputsAutoLogged inputs) {
    inputs.connected = BaseStatusSignal.refreshAll(voltageSignal).isOK();

    inputs.appliedVoltage = voltageSignal.getValueAsDouble();
  }
}
