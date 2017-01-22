package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Eagles Teleop 2", group="TeleOp")

/**
 * Created by Admin on 10/16/2016.
 */

public class TeleOp2 extends OpMode
{
    //double distance;
    Servo protestSign;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor grabber;
    DcMotor throwingArm;
    ColorSensor colorSensor;
    LightSensor legoColor;
    DeviceInterfaceModule dim;

    public void init()
    {
        protestSign = hardwareMap.servo.get("protestSign");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        grabber = hardwareMap.dcMotor.get("grabber");
        throwingArm = hardwareMap.dcMotor.get("throwingArm");
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        legoColor = hardwareMap.lightSensor.get("legoColor");
        dim = hardwareMap.deviceInterfaceModule.get("Device Interface Module");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        grabber.setDirection(DcMotorSimple.Direction.REVERSE);
        throwingArm.setDirection(DcMotorSimple.Direction.REVERSE);

        legoColor.enableLed(true);
    }



    public void loop()
    {
        //distance = dim.getAnalogInputVoltage(0);
        frontLeft.setPower(gamepad1.left_stick_y);
        backLeft.setPower(gamepad1.left_stick_y);
        frontRight.setPower(gamepad1.right_stick_y);
        backRight.setPower(gamepad1.right_stick_y);

        grabber.setPower(gamepad2.left_stick_y / 2.5);

        if(gamepad1.b)
            protestSign.setPosition(.5);
        if(gamepad1.x)
            protestSign.setPosition(.3);

        if(gamepad2.a){
            throwingArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            throwingArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            throwingArm.setTargetPosition(2240);
            throwingArm.setPower(.2);
        }

        if(!throwingArm.isBusy() || gamepad2.y) {
            throwingArm.setPower(0);
        }

        telemetry.addData("MRcolor-s:", colorSensor.red());
        telemetry.addData("LEGOcolor-s", legoColor.getRawLightDetected());
        telemetry.addData("VoltageBaybee", dim.getAnalogInputVoltage(0));
        telemetry.update();
    }
}
