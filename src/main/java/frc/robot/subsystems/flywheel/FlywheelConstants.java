package frc.robot.subsystems.flywheel;

import com.ctre.phoenix6.CANBus;

import frc.robot.CANBuses;

public class FlywheelConstants {
    public static final int CAN_ID_MAIN = 19;
    public static final int CAN_ID_FOLLOWER = 20;

    public static final CANBus CANBUS = CANBuses.CANIVORE;
}
