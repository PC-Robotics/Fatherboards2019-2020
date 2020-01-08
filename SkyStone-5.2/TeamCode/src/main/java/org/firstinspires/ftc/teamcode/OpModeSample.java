package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name="OpModeSample", group="Java OpMode")
public class OpModeSample extends LinearOpMode {
    public DcMotor leftMotor1;
    public DcMotor rightMotor1;
    public DcMotor leftMotor2;
    public DcMotor rightMotor2;
    public CRServo clawMotor;
    public DcMotor armMotor;

    @Override
    public void runOpMode(){
        leftMotor1 = hardwareMap.get(DcMotor.class, "leftMotor1");
        leftMotor2 = hardwareMap.get(DcMotor.class, "leftMotor2");
        rightMotor1 = hardwareMap.get(DcMotor.class, "rightMotor1");
        rightMotor2 = hardwareMap.get(DcMotor.class, "rightMotor2");
        clawMotor = hardwareMap.get(CRServo.class, "clawMotor");
        armMotor = hardwareMap.get(DcMotor.class, "arm_motor");

        leftMotor1.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        rightMotor1.setDirection(DcMotor.Direction.FORWARD);
        rightMotor2.setDirection(DcMotor.Direction.FORWARD);
        armMotor.setDirection(DcMotor.Direction.REVERSE);

        //USE THIS TO SHOW JUDGES!!!!
        //clawMotor.setPower(-0.7);

        waitForStart();
        /*while (opModeIsActive()){
            if (gamepad1.a)
                leftMotor1.setPower(1);
            else if (gamepad1.b)
                leftMotor2.setPower(1);
            else if (gamepad1.x)
                rightMotor1.setPower(1);
            else if (gamepad1.y)
                rightMotor2.setPower(1);
        */
        while (opModeIsActive()) {
            float motorPower = 0.5f;
            if ((Math.abs(gamepad1.left_stick_x) > 0.2 || (Math.abs(gamepad1.left_stick_y) > 0.2)) || (Math.abs(gamepad1.right_stick_x) > 0.2 || (Math.abs(gamepad1.right_stick_y) > 0.2))) {
                double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
                double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
                double rightX = -gamepad1.right_stick_x;
                //left motor
                final double v1 = r * Math.cos(robotAngle) + rightX * motorPower;
                //right motor
                final double v2 = r * Math.sin(robotAngle) - rightX * motorPower;
                //left motor2
                final double v3 = r * Math.sin(robotAngle) + rightX * motorPower;
                //right motor2
                final double v4 = r * Math.cos(robotAngle) - rightX * motorPower;

                leftMotor1.setPower(v1);
                rightMotor1.setPower(v2);
                leftMotor2.setPower(v3);
                rightMotor2.setPower(v4);
            } else {
                leftMotor1.setPower(0);
                leftMotor2.setPower(0);
                rightMotor1.setPower(0);
                rightMotor2.setPower(0);
            }

            telemetry.addData("Left Motor: ", leftMotor1.getPower());
            telemetry.addData("Left Motor 2: ", leftMotor2.getPower());

            telemetry.addData("Right Motor: ", rightMotor1.getPower());
            telemetry.addData("Right Motor 2: ", rightMotor2.getPower());

            telemetry.addData("Left Stick X: ", gamepad1.left_stick_x);
            telemetry.addData("Left Stick Y: ", gamepad1.left_stick_y);

            //.7f works for CRServo power
            while (gamepad2.a) {
                clawMotor.setPower(.9);
                if (gamepad2.dpad_up)
                    armMotor.setPower(-1);
                else if (gamepad2.dpad_down)
                    armMotor.setPower(0);
                else
                    armMotor.setPower(-.2);
            }
            while (gamepad2.b) {
                clawMotor.setPower(-.9);
                if (gamepad2.dpad_down)
                    armMotor.setPower(0);
                else if (gamepad2.dpad_up)
                    armMotor.setPower(-1);
                else
                    armMotor.setPower(-.2);
            }

            if (!gamepad2.a)
                clawMotor.setPower(0);

            if (!gamepad2.b)
                clawMotor.setPower(0);

            //telemetry.addData("clawMotor: ", clawMotor.getPower());
            telemetry.update();

            if (gamepad2.dpad_up)
                armMotor.setPower(-1);
            else if (gamepad2.dpad_down)
                armMotor.setPower(0);
            else
                armMotor.setPower(-.2);
        }
    }
}
