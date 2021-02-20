package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="TankDriveFatherboards")
public class TankDrive extends LinearOpMode {

    MecanumHardware robot = new MecanumHardware();   // Use a Pushbot's hardware

    @Override
    public void runOpMode() {
        boolean directionToggle = true;
        boolean motorToggle = true;
        boolean inputDirectionToggle = true;
        boolean shooterPowerToggle = true;
        double motorCoefficient = 1.0;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Good Luck Elliot and Other people!");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.intake.setPower(1.0);
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //Driving
            //if (gamepad1.left_stick_y > .2f || gamepad1.left_stick_x > .2f || gamepad2.right_stick_x > .2f || gamepad2.left_stick_y > .2f) {
                double motorCoeff = 1.2;
                double magnitude = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
                double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
                double rightX = -gamepad1.right_stick_x;
                double fld = (magnitude * Math.cos(robotAngle) + rightX) * motorCoeff; //cos +
                double frd = (magnitude * Math.sin(robotAngle) - rightX) * motorCoeff; //sin -
                double bld = (magnitude * Math.sin(robotAngle) + rightX) * motorCoeff; //sin
                double brd = (magnitude * Math.cos(robotAngle) - rightX) * motorCoeff; //cos

                if (gamepad1.right_bumper) {
                    fld = fld * 0.4;
                    frd = frd * 0.4;
                    bld = bld * 0.4;
                    brd = brd * 0.4;
                }

                robot.leftFront.setPower(fld);
                robot.rightFront.setPower(frd);
                robot.leftBack.setPower(bld);
                robot.rightBack.setPower(brd);

            //Strafing
            if ( (gamepad1.left_trigger > 0) && (!(gamepad1.right_trigger > 0)) ) {
                robot.leftFront.setPower(gamepad1.left_trigger );
                robot.rightFront.setPower(gamepad1.left_trigger);
                robot.leftBack.setPower(gamepad1.left_trigger * -1);
                robot.rightBack.setPower(gamepad1.left_trigger * -1);
            }
            else if ( (gamepad1.right_trigger > 0) && (!(gamepad1.left_trigger > 0)) ) {
                robot.leftFront.setPower(gamepad1.right_trigger * -1);
                robot.rightFront.setPower(gamepad1.right_trigger * -1);
                robot.leftBack.setPower(gamepad1.right_trigger);
                robot.rightBack.setPower(gamepad1.right_trigger);
            }

                telemetry.addData("leftFront", "%.2f", fld);
                telemetry.addData("rightFront", "%.2f", frd);
                telemetry.addData("leftBack", "%.2f", bld);
                telemetry.addData("rightBack", "%.2f", brd);
                
            //Reverse intake direction (Player 2)
            if (gamepad2.a)
                robot.intake.setPower(robot.intake.getPower() * -1);
            //Motor Coefficients
            if (gamepad1.x)
                motorCoefficient = 0.85;
            else if (gamepad1.y)
                motorCoefficient = 1.0;
            if ( (gamepad2.right_trigger > 0) && (!(gamepad2.left_trigger > 0)) ) {
                robot.fly1.setPower(1.0);
                robot.fly2.setPower(1.0);
            } else if ( (gamepad2.left_trigger > 0) && (!(gamepad2.right_trigger > 0)) ) {
                robot.fly1.setPower(-1.0);
                robot.fly2.setPower(-1.0);
            } else {
                robot.fly1.setPower(0);
                robot.fly2.setPower(0);
            }





//            //Driving Direction Toggle
//            if(gamepad1.a && directionToggle){
//                robot.leftFront.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
//                robot.rightFront.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
//                robot.leftBack.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
//                robot.rightBack.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
//                directionToggle = false;
//            }
//            else if(gamepad1.a && !directionToggle){
//                robot.leftFront.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
//                robot.rightFront.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
//                robot.leftBack.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
//                robot.rightBack.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
//                directionToggle = true;
//            }


            }

//            //Motor Coefficients
//            if (gamepad2.a)
//                motorCoefficient = 0.85;
//            else if (gamepad2.b)
//                motorCoefficient = 1.0;

//            if (gamepad2.left_bumper)
//                robot.input.setPower(-1);


//            robot.input.setPower(gamepad2.right_trigger);
//            robot.output.setPower(gamepad2.left_trigger * motorCoefficient);
//            robot.output2.setPower(gamepad2.left_trigger * motorCoefficient);
            //reverse motor direction
            //Three things in autonomous
            //1 Grabbing the wobble goal -> blue target zone (15 points)
            //2 Navigation -> 5 points (just moving)
            //3 Power Shot -> 15 points per thing.... 12 points per top...
            //Autonomous: give it 3 seconds to spin up and then advance the first r

            //check if we have to reverse the direction for this...



            //Input Direction Toggle
//            if (gamepad2.right_bumper && inputDirectionToggle){
//                robot.input.setDirection(DcMotorSimple.Direction.FORWARD);
//                inputDirectionToggle = false;
//            }
//            else if (gamepad2.right_bumper && !inputDirectionToggle){
//                robot.input.setDirection(DcMotor.Direction.REVERSE);
//                inputDirectionToggle = true;
//            }
            
            //Shooter Power Toggle
            /*
            if (gamepad1.y && shooterPowerToggle){
                robot.output.setPower(1);
                shooterPowerToggle = false;
            }
            else if (gamepad1.y && shooterPowerToggle){
                robot.output.setPower(0);
                shooterPowerToggle = true;
            }
             */

            telemetry.addData("Driving motor direction toggle: ", directionToggle);
            telemetry.addData("Shooting Motor Direction (shouldn't need to use): ", motorToggle);
            telemetry.addData("Shooter Motor Power: ", motorCoefficient);
            telemetry.update();
            telemetry.addData("pls work","pls?");
            telemetry.update();
        }
    }
//}