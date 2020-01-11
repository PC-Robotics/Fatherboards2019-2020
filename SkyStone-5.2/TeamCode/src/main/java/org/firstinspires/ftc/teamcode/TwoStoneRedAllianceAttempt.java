package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "TwoStoneRedAllianceAttempt")
public class TwoStoneRedAllianceAttempt extends LinearOpMode {

    AutonomousObject robot = new AutonomousObject();

    @Override
    public void runOpMode()
    {
        robot.init(hardwareMap);
    }
}
