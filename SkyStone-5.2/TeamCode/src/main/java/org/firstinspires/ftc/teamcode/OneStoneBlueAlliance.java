package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "OneStoneBlueAlliance")
public class OneStoneBlueAlliance extends LinearOpMode {

    AutonomousObject robot = new AutonomousObject();

    @Override()
    public void runOpMode()
    {
        robot.init(hardwareMap);

        robot.extendForward();
        robot.clawUp();
        robot.forward();
        sleep(1300);

        robot.brake();
        robot.extendStop();
        robot.clawDown();
        sleep(1000);

        robot.backward();
        robot.clawDown();
        sleep(1000);

        robot.brake();
        robot.liftUp();
        sleep(1000);

        robot.strafeLeft();
        sleep(3700);

        robot.brake();
        sleep(1000);

        robot.clawUp();
        robot.backward();
        sleep(250);

        robot.brake();
        robot.liftDown();
        robot.clawDown();
        sleep(700);

        robot.strafeRight();
        sleep(1500);

        robot.brake();
        sleep(25000);
    }
}
