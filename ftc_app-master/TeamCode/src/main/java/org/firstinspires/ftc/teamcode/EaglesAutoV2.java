package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Admin on 10/16/2016.
 */
@Autonomous(name="Eagles Auto V2", group="Auto")
public class EaglesAutoV2 extends LinearOpMode
{
    Servo protestSign;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor grabber;
    DcMotor throwingArm;
    ColorSensor colorSensor;

    private ElapsedTime runtime = new ElapsedTime();

    // drives forward for 3 seconds.
    public void runOpMode() throws InterruptedException
    {
        protestSign = hardwareMap.servo.get("protestSign");
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        grabber = hardwareMap.dcMotor.get("grabber");
        throwingArm = hardwareMap.dcMotor.get("throwingArm");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        throwingArm.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setPower(1);
        backRight.setPower(1);
        frontLeft.setPower(1);
        frontRight.setPower(1);
        while(backRight.getCurrentPosition() > -3600)
        {
            telemetry.addData("Pos", backRight.getCurrentPosition());
            telemetry.update();
        }
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        launch();

        runtime.reset();
        grabber.setPower(-.4);
        while(opModeIsActive() && runtime.seconds() < .5);
        grabber.setPower(0);

        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < .5);

        launch();

        runtime.reset();
        grabber.setPower(.3);
        while(opModeIsActive() && runtime.seconds() < .7);
        grabber.setPower(0);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setPower(1);
        backRight.setPower(1);
        frontLeft.setPower(1);
        frontRight.setPower(1);
        while(backRight.getCurrentPosition() > -2712)
        {
            telemetry.addData("Pos", backRight.getCurrentPosition());
            telemetry.update();
        }
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }



    private void launch()
    {
        throwingArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        throwingArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        throwingArm.setTargetPosition(2240);
        throwingArm.setPower(.2);
        while(throwingArm.isBusy());
    }
}
