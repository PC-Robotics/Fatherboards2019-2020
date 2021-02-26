package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name="AutonomousDrive")
public class AutonomousFB extends LinearOpMode {

    MecanumHardware robot = new MecanumHardware();

    @Override
    public void runOpMode()
    {
        robot.init(hardwareMap);

        waitForStart();
        while(opModeIsActive())
        {
            //at 0.2 power, robot moves ~1.75 ft/sec
            //Shaurnav: tell Elliot to invest in something call encoders. Thay way you can do the math in the code and then say
            //move forward x number of inches.

            drive(0.2);
            long iLoveYouFiveThousand = 5000 + 1; //Shaurnav: this is hilarious
            sleep(4000);
            drive(0);
            STOP();
            sleep(200000000);
        }
    }

    public void drive(double power) {
        robot.leftFront.setPower(power);  //negative should stay because of the direction of the robot
        robot.rightFront.setPower(power);
        robot.rightBack.setPower(power);
        robot.leftBack.setPower(power);
    }

    public void STOP(){
        int power = 0;
        robot.leftFront.setPower(power);  //negative should stay because of the direction of the robot
        robot.rightFront.setPower(power);
        robot.rightBack.setPower(power);
        robot.leftBack.setPower(power);
//        robot.input.setPower(0);
//        robot.output.setPower(0);
//        robot.output2.setPower(0);
    }

}
