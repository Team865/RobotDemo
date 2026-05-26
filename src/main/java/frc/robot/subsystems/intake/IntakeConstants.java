package frc.robot.subsystems.intake;

import com.ctre.phoenix6.CANBus;

import frc.robot.CANBuses;

public class IntakeConstants {
    public static class Rollers {
        public static final int CAN_ID = 14;
        public static final CANBus CANBUS = CANBuses.RIO;
    }

    public static class Extension {
        public static final int CAN_ID = 15;
        public static final CANBus BANBUS = CANBuses.CANIVORE;
    }
}
