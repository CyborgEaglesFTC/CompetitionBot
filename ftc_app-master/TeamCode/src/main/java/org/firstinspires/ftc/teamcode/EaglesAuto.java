package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Admin on 10/16/2016.
 */
@Autonomous(name="Eagles Auto", group="Auto")
public class EaglesAuto extends LinearOpMode
{
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor grabber;
    DcMotor throwingArm;

    private ElapsedTime runtime = new ElapsedTime();

    // drives forward for 3 seconds.
    public void runOpMode() throws InterruptedException
    {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        grabber = hardwareMap.dcMotor.get("grabber");
        throwingArm = hardwareMap.dcMotor.get("throwingArm");

        waitForStart();

        //todo This probably does not work
        // Launch -> Load -> Launch

        throwingArm.setPower(0.3);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.0) {} // allow arm to move for two seconds
        throwingArm.setPower(0);

        grabber.setPower(-0.3);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.5) {} // allow arm to move for half a second
        grabber.setPower(0);

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2) {} // allow time for the ball to fall into the cradle

        throwingArm.setPower(0.3);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.0) {} // allow arm to move for two seconds
        throwingArm.setPower(0);

        moveForward(1);
        runtime.reset();

        while(opModeIsActive() && runtime.seconds() < 3.0)
        {

            //telemetry.addData("Raw", ods.getRawLightDetected());
            //telemetry.update();

        }
        stopMoving();

        /*moveForward(-1);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.0)
        {
            //TODO: Add telemetry
        }
        stop();
        */
        idle();
    }

    private void moveForward(double power)
    {
        frontLeft.setPower(power);
        frontRight.setPower(-power);

        backLeft.setPower(power);
        backRight.setPower(-power);
    }

    private void Turn(double power)
    {
        frontLeft.setPower(power);
        frontRight.setPower(power);

        backLeft.setPower(power);
        backRight.setPower(power);
    }
    private void stopMoving() {moveForward(0);}

}
