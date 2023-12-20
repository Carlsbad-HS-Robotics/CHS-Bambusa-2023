package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class SwerveTeleop extends LinearOpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;

    AnalogInput axon1enc;
    AnalogInput axon2enc;
    AnalogInput axon3enc;
    AnalogInput axon4enc;

    CRServo axon1;
    CRServo axon2;
    CRServo axon3;
    CRServo axon4;

    private Wheel backRight;
    private Wheel backLeft;
    private Wheel frontRight;
    private Wheel frontLeft;

    private SwerveDrive swerveDrive;

    private void initialize() {
        motorFrontRight = hardwareMap.dcMotor.get("motor1");
        motorFrontLeft = hardwareMap.dcMotor.get("motor2");
        motorBackLeft = hardwareMap.dcMotor.get("motor3");
        motorBackRight = hardwareMap.dcMotor.get("motor4");

        axon1 = hardwareMap.crservo.get("axon1");
        axon2 = hardwareMap.crservo.get("axon2");
        axon3 = hardwareMap.crservo.get("axon3");
        axon4 = hardwareMap.crservo.get("axon4");

        axon1enc = hardwareMap.get(AnalogInput.class, "axon1enc");
        axon2enc = hardwareMap.get(AnalogInput.class, "axon2enc");
        axon3enc = hardwareMap.get(AnalogInput.class, "axon3enc");
        axon4enc = hardwareMap.get(AnalogInput.class, "axon4enc");

        //We don't know which axon assigns to which (I'm guessing for now)

        backRight = new Wheel(motorBackRight, axon4, axon4enc);
        backLeft = new Wheel(motorBackLeft, axon3, axon3enc);
        frontRight = new Wheel(motorFrontRight, axon1, axon1enc);
        frontLeft = new Wheel(motorFrontLeft, axon2, axon2enc);

        swerveDrive = new SwerveDrive(backRight, backLeft, frontRight, frontLeft);
    }
    @Override
    public void runOpMode() throws InterruptedException {
        //Initialization
        initialize();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            swerveDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, this);
            telemetry.update();
        }
    }
}
