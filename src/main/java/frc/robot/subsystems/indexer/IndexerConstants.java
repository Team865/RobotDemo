package frc.robot.subsystems.indexer;

import com.ctre.phoenix6.CANBus;
import frc.robot.CANBuses;

public final class IndexerConstants {
  public static final class Serializer {
    public static final int CAN_ID = 17;
  }

  public static final class Tunneler {
    public static final int CAN_ID = 18;
  }

  public static final CANBus CANBUS = CANBuses.CANIVORE;
}
