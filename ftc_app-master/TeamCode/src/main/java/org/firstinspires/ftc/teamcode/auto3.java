package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Admin on 1/14/2017.
 */

@Autonomous(name="Eagles Auto v3", group="Auto")
public class auto3 extends LinearOpMode{

    Servo protestSign;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor grabber;
    DcMotor throwingArm;
    ColorSensor colorSensor;
    LightSensor legoColor;

    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode()throws InterruptedException{
        //config
        protestSign = hardwareMap.servo.get("protestSign");
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        legoColor = hardwareMap.lightSensor.get("legoColor");

        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        grabber = hardwareMap.dcMotor.get("grabber");
        throwingArm = hardwareMap.dcMotor.get("throwingArm");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        throwingArm.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        legoColor.enableLed(true);
        //end config

        waitForStart();

        //drive forward
        runtoposition(2400, 1, 1);

        //launch 2 times
        launch();
        runtime.reset();
        while(runtime.seconds() < .5);
        launch();

        //turn so back is facing the far beacon

        runtoposition(500, 1, -1);

        //drive to far beacon until light sensor sees white line
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setPower(1);
        frontLeft.setPower(1);
        backRight.setPower(1);
        backLeft.setPower(1);
        while(legoColor.getRawLightDetected() < 2.5);
        //mthod: ram that bitch
        //
        //line follow until distance sensor is 3 inches from wall
        //read color sensor and move servo accordingly
        //ram that bitch aka: smash and dash
        //face mountain thing

        //turn towards other beacon

        //drive to other beacon until light sensor sees the line


        //turn to face beacon


        //ram that bitch

        //back off and turn towards mountain

        //drive onto mountain
    }

    private void launch()
    {
        throwingArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        throwingArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        throwingArm.setTargetPosition(2240);
        throwingArm.setPower(.2);
        while(throwingArm.isBusy());
    }

    private void runtoposition(int target, int powerright, int powerleft)
    {
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if(Math.abs(powerright + powerleft) < Math.abs(powerright))
        {
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            powerleft = -powerleft;
        }

        frontRight.setTargetPosition(target);
        frontLeft.setTargetPosition(target);
        backRight.setTargetPosition(-target);
        backLeft.setTargetPosition(target);

        frontRight.setPower(powerright);
        frontLeft.setPower(powerleft);
        backRight.setPower(powerright);
        backLeft.setPower(powerleft);

        while(frontRight.isBusy() || frontLeft.isBusy() || backRight.isBusy() || backLeft.isBusy());
    }
}
