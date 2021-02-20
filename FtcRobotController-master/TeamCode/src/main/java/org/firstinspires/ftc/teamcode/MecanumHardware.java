package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MecanumHardware {
    //Control Hub Assignments
    //C: Control hub
    //E: Expansion hub

    /* Public OpMode members. */
    public DcMotor leftFront   = null; //C0
    public DcMotor  rightFront  = null; //C1
    public DcMotor leftBack   = null; //C2
    public DcMotor  rightBack  = null; //C3
    public DcMotor intake = null;
    public DcMotor fly1 = null;
    public DcMotor fly2 = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    public ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public MecanumHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        //cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());

        // Define and Initialize Motors
        leftFront  = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftBack  = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        intake = hwMap.get(DcMotor.class, "intake");
        fly1 = hwMap.get(DcMotor.class, "fly1");
        fly2 = hwMap.get(DcMotor.class, "fly2");
        //grabber = hwMap.get(Servo.class, "grabber");
        //lift = hwMap.get(DcMotor.class, "lift");
        //webcamName = hwMap.get(WebcamName.class, "Webcam 1");
        //imu = hwMap.get(BNO055IMU.class, "imu");

        //imu = hwMap.get(BNO055IMU.class, "imu");
        leftFront.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        rightFront.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        leftBack.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        rightBack.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        intake.setDirection(DcMotor.Direction.FORWARD);
        fly1.setDirection(DcMotor.Direction.FORWARD);
        fly2.setDirection(DcMotor.Direction.FORWARD);
//        input.setDirection(DcMotor.Direction.REVERSE);
//        output.setDirection(DcMotorSimple.Direction.REVERSE);
//        output2.setDirection(DcMotorSimple.Direction.REVERSE);

        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fly1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fly2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        input.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        output.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        output2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set all motors to zero power
//        input.setPower(0);
//        output.setPower(0);
//        output2.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        intake.setPower(0);
        fly1.setPower(0);
        fly2.setPower(0);
        //lift.setPower(0);

        //grabber.setPosition(MID_SERVO);

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fly1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fly2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        input.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        output.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Set all motors to run without encoders.

        // May want to use RUN_USING_ENCODERS if encoders are installed.
      /*  leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER); */
        //lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }

}