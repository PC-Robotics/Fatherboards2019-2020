package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class MecanumDriveCR extends LinearOpMode{

    private float stickSensitivity = 0.25f; //> than this gets registered as input

    public DcMotor leftMotor;
    public DcMotor leftMotor2;
    public DcMotor rightMotor;
    public DcMotor rightMotor2;
    public float servoPower = .7f;

    public float motorPower = 1f;

    public DcMotor liftPivotMotor;
    public float liftPivotPower = 0.3f;

    public float servoSensitivity = 0.1f;

    public CRServo intakeExtensionServo;
    public CRServo intakeClawServo;

    @Override
    public void runOpMode()
    {
        //Connects motors to hub & phone- use name in quotes for config

        leftMotor = hardwareMap.get(DcMotor.class, "left_Motor"); //0
        leftMotor2 = hardwareMap.get(DcMotor.class, "left_Motor2"); //1

        rightMotor = hardwareMap.get(DcMotor.class, "right_Motor"); //2
        rightMotor2 = hardwareMap.get(DcMotor.class, "right_Motor2"); //3
        leftMotor = hardwareMap.get(DcMotor.class, "left_Motor");
        leftMotor2 = hardwareMap.get(DcMotor.class, "left_Motor2");

        rightMotor = hardwareMap.get(DcMotor.class, "right_Motor");
        rightMotor2 = hardwareMap.get(DcMotor.class, "right_Motor2");

        liftPivotMotor= hardwareMap.get(DcMotor.class, "liftPivotMotor");

        intakeClawServo = hardwareMap.get(CRServo.class, "intakeClawServo");
        intakeExtensionServo = hardwareMap.get(CRServo.class, "intakeExtensionServo");

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor2.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);

        liftPivotMotor.setDirection(DcMotor.Direction.FORWARD);
        liftPivotMotor.setZeroPowerBehavior((DcMotor.ZeroPowerBehavior.BRAKE));

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftPivotMotor.setDirection(DcMotor.Direction.FORWARD);
        liftPivotMotor.setZeroPowerBehavior((DcMotor.ZeroPowerBehavior.BRAKE));

        waitForStart(); //press play button, actives opMode
        while (opModeIsActive())
        {
            drive();
            intake();
            pivotLift();

            telemetry.update();
        }//opModeIsActive

    }//runOpMode

    public void drive()
    {
        /*
        deadzone. If result of setPower() is small. Telemetry was giving values for setPower so idk xy cords.
        Note: we forgot to include the rightstick in the if statement so
        the robot wasn't accounting for it at all (lol)
         */

        if((Math.abs(gamepad1.left_stick_x) > 0.2 || (Math.abs(gamepad1.left_stick_y) > 0.2 )) || (Math.abs(gamepad1.right_stick_x) > 0.2 || (Math.abs(gamepad1.right_stick_y) > 0.2 )))
        {
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;

            double rightX = gamepad1.right_stick_x;

            final double v1 = r * Math.cos(robotAngle) + rightX * motorPower;
            final double v2 = r * Math.sin(robotAngle) - rightX * motorPower;
            final double v3 = r * Math.sin(robotAngle) + rightX * motorPower;
            final double v4 = r * Math.cos(robotAngle) - rightX * motorPower;

            if (v1 > 0)
                leftMotor.setPower(v1);
            if (v2 > 0)
                rightMotor.setPower(v2);
            if (v3 > 0)
                leftMotor2.setPower(v3);
            if (v4 > 0)
                rightMotor2.setPower(v4);
        }
        else
        {
            leftMotor.setPower(0);
            leftMotor2.setPower(0);

            rightMotor.setPower(0);
            rightMotor2.setPower(0);
        }

        if(gamepad1.left_bumper)
            motorPower = 1f;
        else if(gamepad1.right_bumper)
            motorPower = 0.5f;

        telemetry.addData("Left Motor: ", leftMotor.getPower());
        telemetry.addData("Left Motor 2: ", leftMotor2.getPower());

        telemetry.addData("Right Motor: ", rightMotor.getPower());
        telemetry.addData("Right Motor 2: ", rightMotor2.getPower());

        telemetry.addData("Left Stick X: ", gamepad1.left_stick_x);
        telemetry.addData("Left Stick Y: ", gamepad1.left_stick_y);
    }

    public void intake()
    {
        //Back extension servo
        if(gamepad2.dpad_up) {
            intakeClawServo.setPower(-.3f); //goes forward
            sleep(1000);
        }
        else if(gamepad2.dpad_down) {
            intakeClawServo.setPower(-.1f);
            sleep(1000);
        }
        //change the variable name to do some fixing up...
        if(gamepad2.y)
            intakeExtensionServo.setPower(.9f); //goes forward
        else if(gamepad2.a)
            intakeExtensionServo.setPower(-.9f); //goes backward... do we know if higher numbers work?
        else
            intakeExtensionServo.setPower(0);

        telemetry.addData("Claw: ", intakeClawServo.getPower());
    }

   public void pivotLift()
    {
        stickSensitivity = .2f;
        if(-gamepad2.left_stick_y > stickSensitivity)
            liftPivotMotor.setPower(.87);
        else if(gamepad2.left_stick_y > stickSensitivity)
            liftPivotMotor.setPower(.1);
        else
            liftPivotMotor.setPower(0);
        telemetry.addData("Lift Pivot Motor" ,liftPivotMotor.getCurrentPosition());
        telemetry.addData("Left Joystick", -gamepad2.left_stick_y);
    }


}
