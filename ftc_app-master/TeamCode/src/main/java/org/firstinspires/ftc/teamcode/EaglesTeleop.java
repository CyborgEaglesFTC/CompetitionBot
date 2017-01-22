package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//@TeleOp(name = "Eagles Teleop", group="TeleOp")

/**
 * Created by Admin on 10/16/2016.
 */
public class EaglesTeleop extends OpMode
{

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor grabber;
    DcMotor throwingArm;
    int runningFlipper;
    double servoPosition;
    double pastTime = 0;
    public void init()
    {
        servoPosition = 0;
        runningFlipper = 0;
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        grabber = hardwareMap.dcMotor.get("grabber");
        throwingArm = hardwareMap.dcMotor.get("throwingArm");
    }
    public void loop()
    {
        frontLeft.setPower(gamepad1.left_stick_y);
        backLeft.setPower(gamepad1.left_stick_y);
        frontRight.setPower(gamepad1.right_stick_y);
        backRight.setPower(gamepad1.right_stick_y);
        grabber.setPower(gamepad2.left_stick_y);
        throwingArm.setPower(gamepad2.a ? 0.3 : 0);
        float rightY = Math.abs(gamepad2.right_stick_y);
        rightY = rightY >= 0.5 ? rightY - 0.5f : 0.0f; // if the joystick isn't at least pushed half of the way,
        throwingArm.setPower(rightY);
        //*
        /*/

        //*/
    }


}
