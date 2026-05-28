package frc.robot.subsystems.flywheel;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Voltage;

public class FlywheelIOTalonFX implements FlywheelIO {
  private final TalonFX mainMotor;
  private final TalonFX followerMotor;

  private AngularVelocity targetVelocity = RotationsPerSecond.zero();

  private final VoltageOut voltageRequest = new VoltageOut(0.0);
  private final VelocityVoltage velocityRequest = new VelocityVoltage(0.0);

  private final StatusSignal<Voltage> voltageSignal;
  private final StatusSignal<AngularVelocity> velocitySignal;

  private final TalonFXConfiguration config = new TalonFXConfiguration();

  public FlywheelIOTalonFX() {
    config.CurrentLimits.SupplyCurrentLimit = 200.0;
    config.CurrentLimits.SupplyCurrentLimitEnable = true;

    config.Slot0.kS = 0.2;
    config.Slot0.kV = 5.8 / 49.5;

    mainMotor = new TalonFX(FlywheelConstants.CAN_ID_MAIN, FlywheelConstants.CANBUS);
    followerMotor = new TalonFX(FlywheelConstants.CAN_ID_FOLLOWER, FlywheelConstants.CANBUS);
    Follower followerRequest = new Follower(mainMotor.getDeviceID(), MotorAlignmentValue.Opposed);
    followerMotor.setControl(followerRequest);

    mainMotor.getConfigurator().apply(config);
    followerMotor.getConfigurator().apply(config);

    voltageSignal = mainMotor.getMotorVoltage();
    velocitySignal = mainMotor.getVelocity();
  }

  @Override
  public void setVoltage(double voltage) {
    mainMotor.setControl(voltageRequest.withOutput(voltage));
  }

  @Override
  public void setVelocity(AngularVelocity velocity) {
    targetVelocity = velocity;
    mainMotor.setControl(velocityRequest.withVelocity(velocity));
  }

  @Override
  public void updateInputs(FlywheelIOInputsAutoLogged inputs) {
    inputs.masterConnected = BaseStatusSignal.refreshAll(voltageSignal, velocitySignal).isOK();

    inputs.appliedVoltage = voltageSignal.getValueAsDouble();
    inputs.velocity = velocitySignal.getValue();
    inputs.targetVelocity = targetVelocity;
  }

  @Override
  public void setGains(double kP, double kD) {
    config.Slot0.kP = kP;
    config.Slot0.kD = kD;

    mainMotor.getConfigurator().apply(config);
    followerMotor.getConfigurator().apply(config);
  }
}
