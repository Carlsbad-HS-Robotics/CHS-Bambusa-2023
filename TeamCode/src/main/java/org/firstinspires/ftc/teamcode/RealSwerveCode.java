package org.firstinspires.ftc.teamcode;

import static java.lang.Math.asin;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class RealSwerveCode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("motor1");
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("motor2");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("motor3");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("motor4");

        double conversionFactor = ((2 * Math.PI) / 3.3); //the conversion factor of analog encoder input to servo position

        //declare axons
        CRServo axon1 = hardwareMap.crservo.get("axon1");
        CRServo axon2 = hardwareMap.crservo.get("axon2");
        CRServo axon3 = hardwareMap.crservo.get("axon3");
        CRServo axon4 = hardwareMap.crservo.get("axon4");

        //Declare our AXON drive servos (in cont. rotation mode)

        //Declare the analog input from the encoders on these fantastic servos. 0v = 0 degrees, 3.3v = 360.
        AnalogInput axon1enc = hardwareMap.get(AnalogInput.class, "axon1enc");
        AnalogInput axon2enc = hardwareMap.get(AnalogInput.class, "axon2enc");
        AnalogInput axon3enc = hardwareMap.get(AnalogInput.class, "axon3enc");
        AnalogInput axon4enc = hardwareMap.get(AnalogInput.class, "axon4enc");


        //Defensive play needs the "no power mode" to still use power to hold its pos.
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //set the OG servo home positions


        double leftsidepower = 0;
        double rightsidepower = 0;

        double Axon1pos = axon1enc.getVoltage();
        double Axon2pos = axon2enc.getVoltage();
        double Axon3pos = axon3enc.getVoltage();
        double Axon4pos = axon4enc.getVoltage();
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

           rightsidepower = gamepad1.right_stick_y;
           leftsidepower = gamepad1.left_stick_y;

           axon1.setPower(0);
           axon2.setPower(0);
           axon3.setPower(0);
           axon4.setPower(0);

           motorFrontRight.setPower(rightsidepower);
           motorBackRight.setPower(rightsidepower);
           motorFrontLeft.setPower(leftsidepower);
           motorBackLeft.setPower(leftsidepower);

        }
    }
}